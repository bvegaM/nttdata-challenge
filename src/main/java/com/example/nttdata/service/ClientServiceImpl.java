package com.example.nttdata.service;

import com.example.nttdata.dto.ClientDTO;
import com.example.nttdata.dto.mapper.ClientMapper;
import com.example.nttdata.models.Client;
import com.example.nttdata.repository.ClientRepository;
import com.example.nttdata.service.interfaces.ClientService;
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
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper mapper;

    @Override
    public List<ClientDTO> getAll() {
        try{
            Optional<List<Client>> clients = clientRepository.getAll();
            if(clients.isPresent()){
                if(clients.get().isEmpty()){
                    throw new Exception("Not clients found");
                }else{
                   return mapper.toClientDtos(clients.get());
                }
            }else{
                throw new Exception("Not clients found");
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return new ArrayList<>();
        }

    }

    @Override
    public ClientDTO getOneByDni(String dni) {
        try{
            Optional<Client> client = clientRepository.getOneByDni(dni);
            if(client.isPresent()){
                return mapper.toClientDto(client.get());
            }else{
                throw new Exception("Client Not found");
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        try{
            return mapper.toClientDto(clientRepository.save(mapper.toClient(clientDTO)));
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }

    }

    @Override
    public ClientDTO update(String dni, ClientDTO clientDTO){
        try{
            Optional<Client> clientToUpdate = clientRepository.getOneByDni(dni);
            if(clientToUpdate.isPresent()){
                Client clientGet = clientToUpdate.get();
                Client client = mapper.toClient(clientDTO);
                BeanUtilsBean beanUtils = new NonNullBeanProperties();
                beanUtils.copyProperties(clientGet,client);
                return mapper.toClientDto(clientRepository.save(clientGet));
            }else {
                throw new Exception("Client With DNI: ".concat(dni).concat(" not found"));
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return null;
        }

    }

    @Override
    public Boolean deleteByDni(String dni){
        try {
            Optional<Client> clientToDelete = clientRepository.getOneByDni(dni);
            if (clientToDelete.isPresent()) {
                clientRepository.deleteByDni(dni);
                return Boolean.TRUE;
            }else{
                throw new Exception("Client With DNI: ".concat(dni).concat(" not found"));
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            return Boolean.FALSE;
        }
    }
}
