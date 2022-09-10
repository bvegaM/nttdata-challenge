package com.example.nttdata.service;

import com.example.nttdata.dto.MovementDTO;
import com.example.nttdata.dto.MovementInsertDTO;
import com.example.nttdata.dto.ReportDTO;
import com.example.nttdata.dto.mapper.MovementMapper;
import com.example.nttdata.dto.mapper.ReportMapper;
import com.example.nttdata.models.Account;
import com.example.nttdata.models.Movement;
import com.example.nttdata.repository.AccountRepository;
import com.example.nttdata.repository.MovementRepository;
import com.example.nttdata.service.interfaces.MovementService;
import com.example.nttdata.util.BankUtil;
import com.example.nttdata.util.enums.TypeMovementEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovementServiceImpl implements MovementService {

    private static final Logger log = LoggerFactory.getLogger(MovementServiceImpl.class);
    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MovementMapper mapper;

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<MovementDTO> getByAccountNumberAccount(String numberAccount) {
        try{
            Optional<List<Movement>> movements = movementRepository.findByAccountNumberAccount(numberAccount);
            if(movements.isPresent()){
                if(movements.get().isEmpty()){
                    throw new Exception("Not movements found with numberAccount: ".concat(numberAccount));
                }else{
                    return mapper.toMovementDtos(movements.get());
                }
            }else{
                throw new Exception("Not movements found with numberAccount".concat(numberAccount));
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<MovementDTO> getByAccountClientDni(String dni) {
        try{
            Optional<List<Movement>> movements = movementRepository.findByAccountClientDni(dni);
            if(movements.isPresent()){
                if(movements.get().isEmpty()){
                    throw new Exception("Not movements found with dni: ".concat(dni));
                }else{
                    return mapper.toMovementDtos(movements.get());
                }
            }else{
                throw new Exception("Not movements found with dni".concat(dni));
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<ReportDTO> getByAccountClientDniAndMovementDateIsBetween(String dni, String initDate, String endDate){
        try{
            Date firstDate = initDate!=null ? new SimpleDateFormat("yyyy-MM-dd").parse(initDate):new Date();
            Date secondDate = endDate!=null ? new SimpleDateFormat("yyyy-MM-dd").parse(endDate):new Date();
            Optional<List<Movement>> movements = movementRepository.findByAccountClientDniAndMovementDateIsBetween(dni,firstDate,secondDate);
            if(movements.isPresent()){
                if(movements.get().isEmpty()){
                    throw new Exception("Not movements found");
                }else{
                    return reportMapper.toReportDtos(movements.get());
                }
            }else{
                throw new Exception("Not movements found");
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public MovementDTO save(MovementInsertDTO movementInsertDTO) {
        try{
            Optional<Account> account = accountRepository.getOneByNumberAccount(movementInsertDTO.getNumeroCuenta());
            if(account.isPresent()){
                Account accountGet = account.get();
                if (movementInsertDTO.getTipoMovimiento().getTypeMovement().equals(TypeMovementEnum.RETIRO.getTypeMovement())){
                    if(accountGet.getBalance()<movementInsertDTO.getValor())
                        throw new Exception("Current balance is less than the value to be withdrawn");

                }
                Movement movement =
                        Movement.builder()
                                .movementBalance(accountGet.getBalance()
                                        +(BankUtil.verifyValueTypeMovement(movementInsertDTO.getTipoMovimiento(),movementInsertDTO.getValor())))
                                .movementValue(movementInsertDTO.getValor())
                                .movementDate(new Date())
                                .typeMovement(movementInsertDTO.getTipoMovimiento())
                                .account(accountGet)
                                .state(true)
                                .build();

                Movement response = movementRepository.save(movement);

                accountGet.setBalance(movement.getMovementBalance());
                accountRepository.save(accountGet);
                return mapper.toMovementDto(response);
            }else {
                throw new Exception("Account Not found with numberAccount".concat(movementInsertDTO.getNumeroCuenta()));
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }
    }
}
