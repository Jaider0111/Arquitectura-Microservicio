package com.sofka.clientmanagement.infrastructure.adapter.repository;

import com.sofka.clientmanagement.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}