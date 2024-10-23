package com.sofka.clientmanagement.infrastructure.adapter.controller;

import com.sofka.clientmanagement.application.service.ClienteService;
import com.sofka.clientmanagement.domain.dto.ClienteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ClienteDto clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.createCliente(clienteDTO));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAll() {
        return ResponseEntity.ok(clienteService.readAllCliente());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.readCliente(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Long id, @RequestBody ClienteDto clienteDTO) {
        return ResponseEntity.ok(clienteService.updateCliente(id, clienteDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClienteDto> updatePartial(@PathVariable Long id, @RequestBody ClienteDto clienteDTO) {
        return ResponseEntity.ok(clienteService.updateClientePartial(id, clienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
