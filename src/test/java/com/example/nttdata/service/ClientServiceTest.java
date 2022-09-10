package com.example.nttdata.service;


import com.example.nttdata.dto.ClientDTO;
import com.example.nttdata.dto.mapper.ClientMapper;
import com.example.nttdata.models.Client;
import com.example.nttdata.repository.ClientRepository;
import com.example.nttdata.util.enums.GenreEnum;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ClientMapper mapper;

    @Test
    public void testSaveClientReturnClientDTO(){
        //@Given
        ClientDTO clientDTO = ClientDTO.builder().cedula("0101737674").nombre("bryam vega").edad(23)
                .genero(GenreEnum.M).direccion("Cuenca").telefono("0985164142").usuario("bvega")
                .contrasenia("admin.123").estado(true).build();

        //@When
        when(this.clientRepository.save(any(Client.class))).thenReturn(mapper.toClient(clientDTO));
        ClientDTO clientSave = this.clientService.save(clientDTO);

        //@Then
        assertThat(clientSave.getCedula()).isSameAs(clientDTO.getCedula());
    }
}
