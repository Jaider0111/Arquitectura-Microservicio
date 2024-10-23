package com.sofka.transactions.application.service;

import com.sofka.transactions.application.ports.*;
import com.sofka.transactions.domain.dto.CuentaDto;
import com.sofka.transactions.domain.exception.BusinessException;
import com.sofka.transactions.domain.model.Cuenta;
import com.sofka.transactions.infrastructure.adapter.mapper.CuentaMapper;
import com.sofka.transactions.infrastructure.adapter.producer.KafkaProducer;
import com.sofka.transactions.infrastructure.adapter.repository.CuentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService implements CreateCuentaUseCase, DeleteCuentaUseCase, UpdateCuentaUseCase, ReadCuentaUseCase {
    public static final String CUENTA_NO_ENCONTRADA = "Cuenta no encontrada";
    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;
    private final KafkaProducer kafkaProducer;

    public CuentaService(
            CuentaRepository cuentaRepository,
            CuentaMapper cuentaMapper,
            KafkaProducer kafkaProducer
    ) {
        this.cuentaRepository = cuentaRepository;
        this.cuentaMapper = cuentaMapper;
        this.kafkaProducer = kafkaProducer;
    }


    @Override
    @Transactional
    public Long createCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDto);
        cuenta = cuentaRepository.save(cuenta);
        try {
            kafkaProducer.senCuentaCreated(cuenta.getNumeroCuenta(), cuenta.getCliente().getClienteId());
        } catch (Exception e) {
            throw new BusinessException("Error al enviar mensaje a kafka");
        }
        return cuentaRepository.save(cuenta).getId();
    }

    @Override
    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    @Override
    public CuentaDto updateCuenta(Long id, CuentaDto cuentaDto) {
        if (!cuentaRepository.existsById(id)) {
            throw new BusinessException(CUENTA_NO_ENCONTRADA);
        }
        Cuenta cuenta = cuentaMapper.toEntity(cuentaDto);
        cuenta.setId(id);
        return cuentaMapper.toDto(cuentaRepository.save(cuenta));
    }

    @Override
    public CuentaDto updateCuentaPartial(Long id, CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(
                () -> new BusinessException(CUENTA_NO_ENCONTRADA)
        );
        cuenta = cuentaMapper.partialUpdate(cuentaDto, cuenta);
        return cuentaMapper.toDto(cuentaRepository.save(cuenta));
    }

    @Override
    public CuentaDto readCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(
                () -> new BusinessException(CUENTA_NO_ENCONTRADA)
        );
        return cuentaMapper.toDto(cuenta);
    }

    @Override
    public List<CuentaDto> readAllCuenta() {
        return cuentaRepository.findAll().stream().map(cuentaMapper::toDto).toList();
    }
}
