package com.example.fau.controladores;

import com.example.fau.dtos.OrdenDTO;
import com.example.fau.servicios.OrdenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping
    public ResponseEntity<List<OrdenDTO>> listarOrdenes() {
        return ResponseEntity.ok(ordenService.listarOrdenes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDTO> obtenerOrdenPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordenService.obtenerOrdenPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrdenDTO> crearOrden(@RequestBody OrdenDTO ordenDTO) {
        return ResponseEntity.ok(ordenService.crearOrden(ordenDTO));
    }
}
