package com.example.fau.servicios;

import com.example.fau.dtos.FacturaDTO;
import java.util.List;

public interface FacturaService {
    FacturaDTO generarFactura(Long ordenId);
    FacturaDTO obtenerFacturaPorId(Long id);
    List<FacturaDTO> listarFacturas();
}
