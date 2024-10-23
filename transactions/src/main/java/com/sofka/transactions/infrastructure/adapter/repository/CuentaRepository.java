package com.sofka.transactions.infrastructure.adapter.repository;

import com.sofka.transactions.domain.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}