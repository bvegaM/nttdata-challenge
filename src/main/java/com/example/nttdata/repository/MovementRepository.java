package com.example.nttdata.repository;


import com.example.nttdata.dto.MovementDTO;
import com.example.nttdata.models.Movement;
import com.example.nttdata.repository.interfaces.MovementCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public class MovementRepository {

    @Autowired
    private MovementCrudRepository movementCrudRepository;

    public Optional<List<Movement>> findByAccountNumberAccount(String numberAccount){
        return movementCrudRepository.findByAccountNumberAccount(numberAccount);
    }

    public Optional<List<Movement>> findByAccountClientDni(String dni){
        return movementCrudRepository.findByAccountClientDni(dni);
    }

    public Optional<List<Movement>> findByAccountClientDniAndMovementDateIsBetween(String dni, Date initDate, Date endDate){
        return  movementCrudRepository.findByAccountClientDniAndMovementDateIsBetween(dni,initDate,endDate);
    }

    public Movement save(Movement movement){
        return movementCrudRepository.save(movement);
    }

}

