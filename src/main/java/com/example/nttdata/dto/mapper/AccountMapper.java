package com.example.nttdata.dto.mapper;


import com.example.nttdata.dto.AccountDTO;
import com.example.nttdata.models.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mappings({
            @Mapping(source = "numberAccount", target = "numeroCuenta"),
            @Mapping(source = "typeAccount", target = "tipoCuenta"),
            @Mapping(source = "balance", target = "saldo"),
            @Mapping(source = "state", target = "estado"),
            @Mapping(source = "client.dni", target = "clienteCedula"),
    })
    AccountDTO toAccountDto(Account account);
    List<AccountDTO> toAccountDtos(List<Account> accounts);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "movements", ignore = true)
    Account toAccount(AccountDTO accountDTO);
}
