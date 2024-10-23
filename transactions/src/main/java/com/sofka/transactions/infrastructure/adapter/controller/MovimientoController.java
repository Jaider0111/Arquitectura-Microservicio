package com.sofka.transactions.infrastructure.adapter.controller;

import com.sofka.transactions.application.service.MovimientoService;
import com.sofka.transactions.domain.dto.MovimientoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDto> readMovimiento(@PathVariable Long id) {
        return ResponseEntity.ok(movimientoService.readMovimiento(id));
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDto>> readAllMovimientos() {
        return ResponseEntity.ok(movimientoService.readAllMovimientos());
    }

    @PostMapping
    public ResponseEntity<Long> createMovimiento(@RequestBody MovimientoDto movimientoDto) {
        return new ResponseEntity<>(movimientoService.createMovimiento(movimientoDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDto> updateMovimiento(@PathVariable Long id, @RequestBody MovimientoDto movimientoDto) {
        return ResponseEntity.ok(movimientoService.updateMovimiento(id, movimientoDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovimientoDto> updateMovimientoPartial(@PathVariable Long id, @RequestBody MovimientoDto movimientoDto) {
        return ResponseEntity.ok(movimientoService.updateMovimientoPartial(id, movimientoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}