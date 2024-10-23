package com.sofka.transactions.application.service;

import com.sofka.transactions.application.ports.CreateMovimientoUseCase;
import com.sofka.transactions.application.ports.DeleteMovimientoUseCase;
import com.sofka.transactions.application.ports.ReadMovimientoUseCase;
import com.sofka.transactions.application.ports.UpdateMovimientoUseCase;
import com.sofka.transactions.domain.dto.MovimientoDto;
import com.sofka.transactions.domain.exception.BusinessException;
import com.sofka.transactions.domain.model.Cuenta;
import com.sofka.transactions.domain.model.Movimiento;
import com.sofka.transactions.infrastructure.adapter.mapper.MovimientoMapper;
import com.sofka.transactions.infrastructure.adapter.repository.CuentaRepository;
import com.sofka.transactions.infrastructure.adapter.repository.MovimientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class MovimientoService implements CreateMovimientoUseCase, DeleteMovimientoUseCase, ReadMovimientoUseCase, UpdateMovimientoUseCase {
    public static final String MOVIMIENTO_NO_ENCONTRADO = "Movimiento no encontrado";
    private final MovimientoMapper movimientoMapper;
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(
            MovimientoMapper movimientoMapper,
            MovimientoRepository movimientoRepository,
            CuentaRepository cuentaRepository
    ) {
        this.movimientoMapper = movimientoMapper;
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    @Transactional
    public Long createMovimiento(MovimientoDto movimientoDto) {
        Cuenta cuenta = cuentaRepository.findById(movimientoDto.getCuentaId()).orElseThrow(
                () -> new BusinessException("Cuenta no encontrada")
        );
        Movimiento movimiento = movimientoMapper.toEntity(movimientoDto);
        movimiento.setSaldo(cuenta.getSaldoInicial());
        switch (movimientoDto.getTipoMovimiento()) {
            case "Deposito" -> cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimientoDto.getValor());
            case "Retiro" -> {
                if(cuenta.getSaldoInicial() < movimientoDto.getValor()) {
                    throw new BusinessException("Saldo no disponible");
                }
                cuenta.setSaldoInicial(cuenta.getSaldoInicial() - movimientoDto.getValor());
            }
            default -> throw new BusinessException("Tipo de movimiento no válido");
        }
        cuenta = cuentaRepository.save(cuenta);
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(LocalDateTime.now());
        return movimientoRepository.save(movimiento).getId();
    }

    @Override
    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public MovimientoDto updateMovimiento(Long id, MovimientoDto movimientoDto) {
        Movimiento ultimoMovimiento = movimientoRepository.findLastMovimientoByCuentaId(movimientoDto.getCuentaId());
        if(!ultimoMovimiento.getId().equals(id)) {
            throw new BusinessException("No se puede modificar un movimiento diferente al último");
        }
        if(!movimientoRepository.existsById(id)) {
            throw new BusinessException(MOVIMIENTO_NO_ENCONTRADO);
        }
        Movimiento movimiento = movimientoMapper.toEntity(movimientoDto);
        movimiento.setId(id);
        return movimientoMapper.toDto(movimientoRepository.save(movimiento));
    }

    @Override
    public MovimientoDto updateMovimientoPartial(Long id, MovimientoDto movimientoDto) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(
                () -> new BusinessException(MOVIMIENTO_NO_ENCONTRADO)
        );
        movimiento.setFecha(movimientoDto.getFecha());
        return movimientoMapper.toDto(movimientoRepository.save(movimiento));
    }

    @Override
    public MovimientoDto readMovimiento(Long id) {
        return movimientoMapper.toDto(movimientoRepository.findById(id).orElseThrow(
                () -> new BusinessException(MOVIMIENTO_NO_ENCONTRADO)
        ));
    }

    @Override
    public List<MovimientoDto> readAllMovimientos() {
        return movimientoRepository.findAll().stream().map(movimientoMapper::toDto).toList();
    }
}
