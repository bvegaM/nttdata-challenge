package com.example.nttdata.controller;




import com.example.nttdata.dto.ClientDTO;
import com.example.nttdata.service.ClientServiceImpl;
import com.example.nttdata.util.Constant;
import com.example.nttdata.util.ResponseHandler;
import com.example.nttdata.util.enums.GenreEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientServiceImpl clientService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testGetClientsReturnListClientDTO() throws Exception {
        //@Given
        List<ClientDTO> clientDTOS = new ArrayList<>();
        ClientDTO clientDTO = ClientDTO.builder().cedula("0150749059").nombre("bryam vega").edad(23)
                .genero(GenreEnum.M).direccion("Cuenca").telefono("0985164142").usuario("bvega")
                .contrasenia("admin.123").estado(true).build();
        clientDTOS.add(clientDTO);

        //@When
        when(this.clientService.getAll()).thenReturn(clientDTOS);
        this.mockMvc.perform(get("/clients/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Data recovered!"))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.length()").value(1));

        //@Then
        verify(clientService).getAll();

    }

}
