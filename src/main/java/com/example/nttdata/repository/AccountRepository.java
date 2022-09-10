package com.example.nttdata.repository;

import com.example.nttdata.models.Account;
import com.example.nttdata.repository.interfaces.AccountCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    @Autowired
    private AccountCrudRepository accountCrudRepository;

    public Optional<Account> getOneByNumberAccount(String numberAccount){
        return accountCrudRepository.findByNumberAccount(numberAccount);
    }

    public Optional<List<Account>> getAccountsByDni(String dni){
        return Optional.of(accountCrudRepository.findByClientDni(dni));
    }

    public Account save(Account account){
        return  accountCrudRepository.save(account);
    }

    public void deleteByNumberAccount(String numberAccount){
        accountCrudRepository.deleteByNumberAccount(numberAccount);
    }

}
