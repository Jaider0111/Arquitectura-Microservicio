package com.sofka.transactions.application.service;

import com.sofka.transactions.application.ports.ReportMovimientoUseCase;
import com.sofka.transactions.domain.dto.MovimientoDto;
import com.sofka.transactions.domain.dto.MovimientoFilterDto;
import com.sofka.transactions.domain.dto.ReporteMovimientoDto;
import com.sofka.transactions.domain.exception.BusinessException;
import com.sofka.transactions.infrastructure.adapter.mapper.MovimientoMapper;
import com.sofka.transactions.infrastructure.adapter.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService implements ReportMovimientoUseCase {
    private final MovimientoMapper movimientoMapper;
    private final MovimientoRepository movimientoRepository;

    public ReportService(MovimientoMapper movimientoMapper, MovimientoRepository movimientoRepository) {
        this.movimientoMapper = movimientoMapper;
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public List<ReporteMovimientoDto> reportMovimientos(MovimientoFilterDto movimientoFilterDto) {
        LocalDateTime fechaInicio = movimientoFilterDto.getFechaInicio().atTime(0, 0, 0);
        LocalDateTime fechaFin = movimientoFilterDto.getFechaFin().atTime(23, 59, 59);
        return movimientoRepository.findByClienteAndFechas(
                movimientoFilterDto.getIdCliente(),
                fechaInicio,
                fechaFin
        ).stream().map(mov -> {
            ReporteMovimientoDto rm = movimientoMapper.toReportDto(mov);
            if (rm.getTipoMovimiento().equals("Deposito"))
                rm.setMovimiento(rm.getValor());
            else
                rm.setMovimiento(-rm.getValor());
            rm.setSaldoFinal(rm.getSaldoInicial() + rm.getMovimiento());
            return rm;
        }).toList();
    }
}
