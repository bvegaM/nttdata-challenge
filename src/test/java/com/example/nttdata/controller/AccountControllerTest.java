package com.example.nttdata.controller;

import com.example.nttdata.dto.AccountDTO;
import com.example.nttdata.service.AccountServiceImpl;
import com.example.nttdata.util.enums.TypeAccountEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountServiceImpl accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAccountReturnsAccountDTO() throws Exception {
        //@Given

        String numberAccount = "00001";

        AccountDTO accountDTO = AccountDTO.builder()
                .estado(true)
                .clienteCedula("0150749059")
                .saldo(1200.00)
                .tipoCuenta(TypeAccountEnum.AHORRO)
                .numeroCuenta(numberAccount)
                .build();

        //@When

        when(this.accountService.getByNumberAccount(numberAccount)).thenReturn(accountDTO);

        //@Then
        this.mockMvc.perform(get("/accounts/".concat(numberAccount)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Data recovered!"))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.clienteCedula").value("0150749059"));

        verify(accountService).getByNumberAccount(numberAccount);

    }
}
