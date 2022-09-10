package com.example.nttdata.service.interfaces;

import com.example.nttdata.dto.AccountDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface AccountService {

    List<AccountDTO> getByDni(String dni);
    AccountDTO getByNumberAccount(String numberAccount);
    AccountDTO save(AccountDTO accountDTO);
    AccountDTO update(String numberAccount, AccountDTO accountDTO) throws InvocationTargetException, IllegalAccessException;
    Boolean deleteByNumberAccount(String numberAccount);
}
