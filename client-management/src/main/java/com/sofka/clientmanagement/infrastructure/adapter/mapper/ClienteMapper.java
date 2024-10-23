package com.sofka.clientmanagement.infrastructure.adapter.mapper;

import com.sofka.clientmanagement.domain.dto.ClienteDto;
import com.sofka.clientmanagement.domain.model.Cliente;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {
    @Mapping(target = "persona.edad", source = "edad")
    @Mapping(target = "persona.nombre", source = "nombre")
    @Mapping(target = "persona.genero", source = "genero")
    @Mapping(target = "persona.identificacion", source = "identificacion")
    @Mapping(target = "persona.direccion", source = "direccion")
    @Mapping(target = "persona.telefono", source = "telefono")
    @Mapping(target = "persona.id", source = "id")
    Cliente toEntity(ClienteDto clienteDto);

    @Mapping(target = "edad", source = "persona.edad")
    @Mapping(target = "nombre", source = "persona.nombre")
    @Mapping(target = "genero", source = "persona.genero")
    @Mapping(target = "identificacion", source = "persona.identificacion")
    @Mapping(target = "direccion", source = "persona.direccion")
    @Mapping(target = "telefono", source = "persona.telefono")
    @Mapping(target = "id", source = "persona.id")
    ClienteDto toDto(Cliente cliente);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cliente partialUpdate(ClienteDto cuentaDto, @MappingTarget Cliente cuenta);
}
