package com.sofka.clientmanagement.application.service;

import com.sofka.clientmanagement.application.ports.*;
import com.sofka.clientmanagement.domain.dto.ClienteDto;
import com.sofka.clientmanagement.domain.exception.BusinessException;
import com.sofka.clientmanagement.domain.model.Cliente;
import com.sofka.clientmanagement.domain.model.Persona;
import com.sofka.clientmanagement.infrastructure.adapter.mapper.ClienteMapper;
import com.sofka.clientmanagement.infrastructure.adapter.repository.ClienteRepository;
import com.sofka.clientmanagement.infrastructure.adapter.repository.PersonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ClienteService implements CreateClienteUseCase, ReadClienteUseCase, UpdateClienteUseCase, DeleteClienteUseCase, AddCuentaClienteUseCase {
    public static final String CLIENTE_NO_ENCONTRADO = "Cliente no encontrado";
    private static final Logger logger = Logger.getLogger(ClienteService.class.getName());
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final PersonaRepository personaRepository;

    public ClienteService(
        ClienteRepository clienteRepository,
        ClienteMapper clienteMapper,
        PersonaRepository personaRepository
    ) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.personaRepository = personaRepository;
    }

    @Override
    public Long createCliente(ClienteDto clienteDto) {
        Cliente cliente = clienteMapper.toEntity(clienteDto);
        Persona persona = personaRepository.save(cliente.getPersona());
        cliente.setPersona(persona);
        return clienteRepository.save(cliente).getClienteId();
    }

    @Override
    public ClienteDto readCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
            () -> new BusinessException(CLIENTE_NO_ENCONTRADO)
        );
        return clienteMapper.toDto(cliente);
    }

    @Override
    public ClienteDto updateCliente(Long id, ClienteDto clienteDto) {
        if (!clienteRepository.existsById(id)) {
            throw new BusinessException(CLIENTE_NO_ENCONTRADO);
        }
        Cliente cliente = clienteMapper.toEntity(clienteDto);
        cliente.setClienteId(id);
        Persona persona = personaRepository.save(cliente.getPersona());
        cliente.setPersona(persona);
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDto updateClientePartial(Long id, ClienteDto clienteDto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
            () -> new BusinessException(CLIENTE_NO_ENCONTRADO)
        );
        cliente = clienteMapper.partialUpdate(clienteDto, cliente);
        Persona persona = personaRepository.save(cliente.getPersona());
        cliente.setPersona(persona);
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<ClienteDto> readAllCliente() {
        return clienteRepository.findAll().stream().map(clienteMapper::toDto).toList();
    }

    @Override
    public void addCuentaCliente(String numeroCuenta, Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(
            () -> new BusinessException(CLIENTE_NO_ENCONTRADO)
        );
        logger.info("Se agregó la cuenta " + numeroCuenta + " al cliente " + cliente.getPersona().getNombre());
        // Some logic business depending
    }
}
