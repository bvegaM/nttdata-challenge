package com.example.nttdata.service;


import com.example.nttdata.dto.AccountDTO;
import com.example.nttdata.dto.ClientDTO;
import com.example.nttdata.dto.mapper.AccountMapper;
import com.example.nttdata.dto.mapper.ClientMapper;
import com.example.nttdata.models.Account;
import com.example.nttdata.repository.AccountRepository;
import com.example.nttdata.repository.ClientRepository;
import com.example.nttdata.util.enums.GenreEnum;
import com.example.nttdata.util.enums.TypeAccountEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @MockBean
    private AccountRepository repository;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private AccountMapper mapper;

    @Autowired
    private ClientMapper clientMapper;

    @Test
    public void testSaveAccountReturnAccountDTO(){
        //@Given
        String numberAccount = "00001";
        String clientDni = "0150749059";

        ClientDTO clientDTO = ClientDTO.builder().cedula(clientDni).nombre("bryam vega").edad(23)
                .genero(GenreEnum.M).direccion("Cuenca").telefono("0985164142").usuario("bvega")
                .contrasenia("admin.123").estado(true).build();

        Account account = Account.builder()
                .typeAccount(TypeAccountEnum.AHORRO)
                .numberAccount(numberAccount)
                .client(clientMapper.toClient(clientDTO))
                .balance(1200.00)
                .state(true)
                .build();

        //@When
        when(this.clientRepository.getOneByDni(clientDni)).thenReturn(Optional.of(clientMapper.toClient(clientDTO)));
        when(this.repository.save(any(Account.class))).thenReturn(account);
        AccountDTO accountSave = this.accountService.save(mapper.toAccountDto(account));

        //@Then
        assertThat(accountSave.getClienteCedula()).isSameAs(account.getClient().getDni());
    }

    @Test
    public void testSaveAccountReturnNullCLientNotExist(){
        //@Given
        String numberAccount = "00003";
        String clientDni = "0150749059";
        Account account = Account.builder()
                .typeAccount(TypeAccountEnum.AHORRO)
                .numberAccount(numberAccount)
                .client(null)
                .balance(1200.00)
                .state(true)
                .build();

        //@When
        when(this.clientRepository.getOneByDni(clientDni)).thenReturn(null);
        when(this.repository.save(any(Account.class))).thenReturn(null);

        AccountDTO accountSave = this.accountService.save(mapper.toAccountDto(account));

        //@Then
        assertThat(accountSave).isSameAs(null);
    }
}
