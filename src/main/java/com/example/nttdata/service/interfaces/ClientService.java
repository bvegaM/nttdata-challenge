package com.example.nttdata.service.interfaces;

import com.example.nttdata.dto.ClientDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ClientService {

    List<ClientDTO> getAll();
    ClientDTO getOneByDni(String dni);
    ClientDTO save(ClientDTO clientDTO);
    ClientDTO update(String dni, ClientDTO clientDTO) throws InvocationTargetException, IllegalAccessException;
    Boolean deleteByDni(String dni) throws Exception;

}
