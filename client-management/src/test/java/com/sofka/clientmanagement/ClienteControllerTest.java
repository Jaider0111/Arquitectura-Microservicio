package com.sofka.clientmanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofka.clientmanagement.application.service.ClienteService;
import com.sofka.clientmanagement.domain.dto.ClienteDto;
import com.sofka.clientmanagement.infrastructure.adapter.controller.ClienteController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    private final String jsonClient = """
                                {
                                  "clienteId": 1,
                                  "contrasena": "password123",
                                  "estado": true,
                                  "nombre": "John Marlez",
                                  "genero": "Masculino",
                                  "edad": 25,
                                  "identificacion": "123456789",
                                  "direccion": "Calle 123",
                                  "telefono": "1234567890"
                                }""";

    private final ClienteDto clienteDto = new ObjectMapper().readValue(jsonClient, ClienteDto.class);

    public ClienteControllerTest() throws JsonProcessingException {
    }

    @Test
    void testCreateCliente() throws Exception {
        Mockito.when(clienteService.createCliente(Mockito.any(ClienteDto.class))).thenReturn(1L);

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonClient))
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
    }

    @Test
    void testGetAllClientes() throws Exception {
        Mockito.when(clienteService.readAllCliente()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testGetClienteById() throws Exception {

        Mockito.when(clienteService.readCliente(1L)).thenReturn(clienteDto);

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void testUpdateCliente() throws Exception {

        Mockito.when(clienteService.updateCliente(Mockito.eq(1L), Mockito.any(ClienteDto.class))).thenReturn(clienteDto);

        mockMvc.perform(put("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonClient))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonClient));
    }

    @Test
    void testDeleteCliente() throws Exception {
        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isNoContent());
    }
}
