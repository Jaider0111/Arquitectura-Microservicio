package com.sofka.transactions.infrastructure.adapter.controller;

import com.sofka.transactions.application.service.ReportService;
import com.sofka.transactions.domain.dto.MovimientoDto;
import com.sofka.transactions.domain.dto.MovimientoFilterDto;
import com.sofka.transactions.domain.dto.ReporteMovimientoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<ReporteMovimientoDto>> reportMovimientos(MovimientoFilterDto movimientoFilterDto) {
        return ResponseEntity.ok(reportService.reportMovimientos(movimientoFilterDto));
    }
}
