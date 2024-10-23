package com.sofka.clientmanagement;

import com.sofka.clientmanagement.domain.dto.ClienteDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateCliente() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setContrasena("password123");
        clienteDto.setEstado(true);
        clienteDto.setNombre("John Marlez");
        clienteDto.setGenero("Masculino");
        clienteDto.setEdad(25);
        clienteDto.setIdentificacion("123456789");
        clienteDto.setDireccion("Calle 123");
        clienteDto.setTelefono("1234567890");

        // Create a new cliente
        ResponseEntity<Long> response = restTemplate.postForEntity("/clientes", clienteDto, Long.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(1L);

        // Get all clientes
        ResponseEntity<List> response1 = restTemplate.getForEntity("/clientes", List.class);

        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response1.getBody()).isNotNull();
        assertThat(response1.getBody().size()).isEqualTo(1);

        // Get cliente by id
        ResponseEntity<ClienteDto> response2 = restTemplate.getForEntity("/clientes/1", ClienteDto.class);

        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response2.getBody()).isNotNull();
        assertThat(response2.getBody().getNombre()).isEqualTo("John Marlez");

        // Update cliente

        clienteDto.setContrasena("newpassword123");
        clienteDto.setDireccion("Calle 123 nueva");
        clienteDto.setId(response2.getBody().getId());

        ResponseEntity<ClienteDto> response3 = restTemplate.exchange("/clientes/1", HttpMethod.PUT, new HttpEntity<>(clienteDto), ClienteDto.class);
        assertThat(response3.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response3.getBody()).isNotNull();
        assertThat(response3.getBody().getContrasena()).isEqualTo("newpassword123");

        // Get cliente by id after update
        ResponseEntity<ClienteDto> response4 = restTemplate.getForEntity("/clientes/1", ClienteDto.class);

        assertThat(response4.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response4.getBody()).isNotNull();
        assertThat(response4.getBody().getNombre()).isEqualTo("John Marlez");
        assertThat(response4.getBody().getDireccion()).isEqualTo("Calle 123 nueva");

        // Delete cliente
        restTemplate.delete("/clientes/1");

        // Get list of clientes after delete

        ResponseEntity<List> response6 = restTemplate.getForEntity("/clientes", List.class);

        assertThat(response6.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response6.getBody()).isNotNull();
        assertThat(response6.getBody().size()).isEqualTo(0);
    }
}