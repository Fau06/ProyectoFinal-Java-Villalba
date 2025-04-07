package com.example.fau.servicios;

import com.example.fau.dtos.FacturaDTO;
import com.example.fau.entidades.Factura;
import com.example.fau.entidades.Orden;
import com.example.fau.exceptions.RecursoNoEncontradoException;
import com.example.fau.external.FechaService;
import com.example.fau.repositorios.FacturaRepository;
import com.example.fau.repositorios.OrdenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaServiceImpl implements FacturaService {

    private final OrdenRepository ordenRepository;
    private final FacturaRepository facturaRepository;
    private final FechaService fechaService;

    public FacturaServiceImpl(
            OrdenRepository ordenRepository,
            FacturaRepository facturaRepository,
            FechaService fechaService
    ) {
        this.ordenRepository = ordenRepository;
        this.facturaRepository = facturaRepository;
        this.fechaService = fechaService;
    }

    @Override
    public FacturaDTO generarFactura(Long ordenId) {
        Orden orden = ordenRepository.findById(ordenId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden no encontrada con ID: " + ordenId));

        double total = orden.getDetalles().stream()
                .mapToDouble(detalle -> detalle.getProducto().getPrecio() * detalle.getCantidad())
                .sum();

        Factura factura = new Factura();
        factura.setOrden(orden);
        factura.setTotal(total);
        factura.setFecha(fechaService.obtenerFechaActual());

        Factura guardada = facturaRepository.save(factura);
        return convertirADTO(guardada);
    }

    @Override
    public FacturaDTO obtenerFacturaPorId(Long id) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Factura no encontrada con ID: " + id));
        return convertirADTO(factura);
    }

    @Override
    public List<FacturaDTO> listarFacturas() {
        return facturaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private FacturaDTO convertirADTO(Factura factura) {
        FacturaDTO dto = new FacturaDTO();
        dto.setId(factura.getId());
        dto.setFecha(factura.getFecha());
        dto.setTotal(factura.getTotal());
        dto.setOrdenId(factura.getOrden().getId());

        // Obtener nombre del cliente si está presente
        if (factura.getOrden().getCliente() != null) {
            dto.setClienteNombre(factura.getOrden().getCliente().getNombre());
        }

        return dto;
    }
}
