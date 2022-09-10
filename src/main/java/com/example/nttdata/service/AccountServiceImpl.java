package com.example.nttdata.service;

import com.example.nttdata.dto.AccountDTO;
import com.example.nttdata.dto.mapper.AccountMapper;
import com.example.nttdata.models.Account;
import com.example.nttdata.models.Client;
import com.example.nttdata.repository.AccountRepository;
import com.example.nttdata.repository.ClientRepository;
import com.example.nttdata.service.interfaces.AccountService;
import com.example.nttdata.util.NonNullBeanProperties;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountMapper mapper;

    @Override
    public List<AccountDTO> getByDni(String dni) {
        try{
            Optional<List<Account>> accounts = accountRepository.getAccountsByDni(dni);
            if(accounts.isPresent()){
                if(accounts.get().isEmpty()){
                    throw new Exception("Not Accounts found with dni".concat(dni));
                }else {
                    return mapper.toAccountDtos(accounts.get());
                }
            }else{
                throw new Exception("Not Accounts found with dni".concat(dni));
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public AccountDTO getByNumberAccount(String numberAccount) {
        try{
            Optional<Account> account = accountRepository.getOneByNumberAccount(numberAccount);
            if(account.isPresent()){
                return mapper.toAccountDto(account.get());
            }else{
                throw new Exception("Account Not found with numberAccount".concat(numberAccount));
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        try{
            Optional<Client> client = clientRepository.getOneByDni(accountDTO.getClienteCedula());
            if(client.isPresent()){
                Account account = mapper.toAccount(accountDTO);
                account.setClient(client.get());
                return mapper.toAccountDto(accountRepository.save(account));
            }else{
                throw new Exception("Client With DNI: ".concat(accountDTO.getClienteCedula()).concat(" not found"));
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public AccountDTO update(String numberAccount, AccountDTO accountDTO) {
        try{
            Optional<Account> accountToUpdate = accountRepository.getOneByNumberAccount(numberAccount);

            if(accountToUpdate.isPresent()){
                Account accountUpdate = accountToUpdate.get();
                Account account = mapper.toAccount(accountDTO);
                Optional<Client> client;
                if(!accountUpdate.getClient().getDni().equals(accountDTO.getClienteCedula())){
                    client = clientRepository.getOneByDni(accountDTO.getClienteCedula());
                    if(client.isEmpty()){
                        throw new Exception("Client With DNI: ".concat(accountDTO.getClienteCedula()).concat(" not found"));
                    }else{
                        accountUpdate.setClient(client.get());
                    }
                }
                BeanUtilsBean beanUtils = new NonNullBeanProperties();
                beanUtils.copyProperties(accountUpdate,account);
                return mapper.toAccountDto(accountRepository.save(accountUpdate));
            }else {
                throw new Exception("Account with number: ".concat(numberAccount).concat(" not found"));
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }

    }

    @Override
    public Boolean deleteByNumberAccount(String numberAccount) {
        try{
            Optional<Account> accountToDelete = accountRepository.getOneByNumberAccount(numberAccount);
            if(accountToDelete.isPresent()){
                accountRepository.deleteByNumberAccount(numberAccount);
                return Boolean.TRUE;
            }else{
                throw new Exception("Account With number: ".concat(numberAccount).concat(" not found"));
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return Boolean.FALSE;
        }

    }
}
