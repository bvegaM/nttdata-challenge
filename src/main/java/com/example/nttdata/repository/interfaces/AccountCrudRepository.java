package com.example.nttdata.repository.interfaces;

import com.example.nttdata.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface AccountCrudRepository extends JpaRepository<Account,Integer> {

    List<Account> findByClientDni(String dni);
    Optional<Account> findByNumberAccount(String numberAccount);
    @Transactional
    void deleteByNumberAccount(String numberAccount);
}
