package com.example.fau.controladores;

import com.example.fau.dtos.FacturaDTO;
import com.example.fau.servicios.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> listarFacturas() {
        return ResponseEntity.ok(facturaService.listarFacturas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> obtenerFacturaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.obtenerFacturaPorId(id));
    }

    @PostMapping("/generar/{ordenId}")
    public ResponseEntity<FacturaDTO> generarFactura(@PathVariable Long ordenId) {
        return ResponseEntity.ok(facturaService.generarFactura(ordenId));
    }
}
