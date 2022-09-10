package com.example.nttdata.repository.interfaces;

import com.example.nttdata.models.Client;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface ClientCrudRepository extends  PersonCrudRepository<Client> {

    Optional<Client> findByDni(String dni);
    @Transactional
    void deleteByDni(String dni);
}
