package com.example.nttdata.repository.interfaces;

import com.example.nttdata.models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovementCrudRepository extends JpaRepository<Movement, Integer> {

    Optional<List<Movement>> findByAccountClientDniAndMovementDateIsBetween(String dni, Date firstDate, Date secDate);
    Optional<List<Movement>> findByAccountNumberAccount(String numberAccount);
    Optional<List<Movement>> findByAccountClientDni(String dni);
}
