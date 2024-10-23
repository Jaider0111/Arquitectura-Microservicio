package com.sofka.clientmanagement.application.ports;

import com.sofka.clientmanagement.domain.dto.ClienteDto;

public interface UpdateClienteUseCase {
    /**
    * Update a client
    * @param id the client id to update
    * @param clienteDto the client to update
    * @return ClienteDto the updated client
     */
    ClienteDto updateCliente(Long id, ClienteDto clienteDto);

    /**
    * Update a client partial
    * @param id the client id to update
    * @param clienteDto the client to update
    * @return ClienteDto the updated client
     */
    ClienteDto updateClientePartial(Long id, ClienteDto clienteDto);
}
