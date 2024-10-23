package com.sofka.transactions.infrastructure.adapter.controller;

import com.sofka.transactions.application.service.CuentaService;
import com.sofka.transactions.domain.dto.CuentaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CuentaDto cuentaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaService.createCuenta(cuentaDTO));
    }

    @GetMapping
    public ResponseEntity<List<CuentaDto>> getAll() {
        return ResponseEntity.ok(cuentaService.readAllCuenta());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.readCuenta(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDto> update(@PathVariable Long id, @RequestBody CuentaDto cuentaDTO) {
        return ResponseEntity.ok(cuentaService.updateCuenta(id, cuentaDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CuentaDto> updatePartial(@PathVariable Long id, @RequestBody CuentaDto cuentaDTO) {
        return ResponseEntity.ok(cuentaService.updateCuentaPartial(id, cuentaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
