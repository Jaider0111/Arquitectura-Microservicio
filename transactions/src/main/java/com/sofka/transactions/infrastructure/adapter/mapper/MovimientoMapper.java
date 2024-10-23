package com.sofka.transactions.infrastructure.adapter.mapper;

import com.sofka.transactions.domain.dto.MovimientoDto;
import com.sofka.transactions.domain.dto.ReporteMovimientoDto;
import com.sofka.transactions.domain.model.Movimiento;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovimientoMapper {

    @Mapping(source = "cuentaId", target = "cuenta.id")
    Movimiento toEntity(MovimientoDto movimientoDto);

    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "cliente", source = "cuenta.cliente.persona.nombre")
    @Mapping(target = "estado", source = "cuenta.estado")
    @Mapping(target = "saldoInicial", source = "saldo")
    @Mapping(target = "tipoCuenta", source = "cuenta.tipoCuenta")
    @Mapping(target = "numeroCuenta", source = "cuenta.numeroCuenta")
    ReporteMovimientoDto toReportDto(Movimiento movimiento);

    @InheritInverseConfiguration(name = "toEntity")
    MovimientoDto toDto(Movimiento movimiento);


    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Movimiento partialUpdate(MovimientoDto movimientoDto, @MappingTarget Movimiento movimiento);
}