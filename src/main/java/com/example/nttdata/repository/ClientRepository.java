package com.example.nttdata.repository;

import com.example.nttdata.models.Client;
import com.example.nttdata.repository.interfaces.ClientCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepository {
    @Autowired
    private ClientCrudRepository clientCrudRepository;

    public Optional<List<Client>> getAll(){
        return Optional.of(clientCrudRepository.findAll());
    }

    public Optional<Client> getOneByDni(String dni){
        return clientCrudRepository.findByDni(dni);
    }

    public Client save(Client client){
        return clientCrudRepository.save(client);
    }

    public void deleteByDni(String dni){
        clientCrudRepository.deleteByDni(dni);
    }

}
