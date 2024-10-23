package com.sofka.transactions.infrastructure.adapter.repository;

import com.sofka.transactions.domain.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query("SELECT m FROM Movimiento m " +
            "INNER JOIN m.cuenta c " +
            "INNER JOIN c.cliente cl " +
            "WHERE cl.clienteId = :idCliente AND m.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Movimiento> findByClienteAndFechas(Long idCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query("SELECT m FROM Movimiento m " +
            "INNER JOIN m.cuenta c " +
            "WHERE c.id = :cuentaId " +
            "ORDER BY m.fecha DESC")
    Movimiento findLastMovimientoByCuentaId(Long cuentaId);
}