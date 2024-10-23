package com.sofka.transactions.infrastructure.adapter.mapper;

import com.sofka.transactions.domain.dto.CuentaDto;
import com.sofka.transactions.domain.model.Cuenta;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CuentaMapper {
    @Mapping(target = "cliente.clienteId", source = "clienteId")
    Cuenta toEntity(CuentaDto cuentaDto);

    CuentaDto toDto(Cuenta cuenta);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cuenta partialUpdate(CuentaDto cuentaDto, @MappingTarget Cuenta cuenta);
}