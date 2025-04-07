package com.example.fau.servicios;

import com.example.fau.dtos.OrdenDTO;
import java.util.List;

public interface OrdenService {
    OrdenDTO crearOrden(OrdenDTO ordenDTO);
    OrdenDTO obtenerOrdenPorId(Long id);
    List<OrdenDTO> listarOrdenes();
}
