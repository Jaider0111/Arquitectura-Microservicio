package com.sofka.transactions.application.ports;

import com.sofka.transactions.domain.dto.MovimientoDto;
import com.sofka.transactions.domain.dto.MovimientoFilterDto;
import com.sofka.transactions.domain.dto.ReporteMovimientoDto;

import java.util.List;

public interface ReportMovimientoUseCase {
    /**
     * Report of movimientos
     * @param MovimientoFilterDto the filter to apply
     * @return List<MovimientoDto> the list of movimientos
     */
    List<ReporteMovimientoDto> reportMovimientos(MovimientoFilterDto movimientoFilterDto);
}
