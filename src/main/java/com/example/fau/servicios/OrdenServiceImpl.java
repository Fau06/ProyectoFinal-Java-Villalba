package com.example.fau.servicios;

import com.example.fau.dtos.DetalleOrdenDTO;
import com.example.fau.dtos.OrdenDTO;
import com.example.fau.entidades.Cliente;
import com.example.fau.entidades.DetalleOrden;
import com.example.fau.entidades.Orden;
import com.example.fau.entidades.Producto;
import com.example.fau.exceptions.RecursoNoEncontradoException;
import com.example.fau.repositorios.ClienteRepository;
import com.example.fau.repositorios.OrdenRepository;
import com.example.fau.repositorios.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    public OrdenServiceImpl(
            OrdenRepository ordenRepository,
            ClienteRepository clienteRepository,
            ProductoRepository productoRepository
    ) {
        this.ordenRepository = ordenRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public OrdenDTO crearOrden(OrdenDTO ordenDTO) {
        // Buscar cliente
        Cliente cliente = clienteRepository.findById(ordenDTO.getClienteId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con id: " + ordenDTO.getClienteId()));

        // Crear nueva orden
        Orden orden = new Orden();
        orden.setCliente(cliente);
        orden.setTotal(ordenDTO.getTotal());

        // Convertir los detalles del DTO a entidades DetalleOrden
        List<DetalleOrden> detalles = new ArrayList<>();

        for (DetalleOrdenDTO detalleDTO : ordenDTO.getDetalles()) {
            Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con id: " + detalleDTO.getProductoId()));

            DetalleOrden detalle = new DetalleOrden();
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setOrden(orden); // Relación bidireccional

            detalles.add(detalle);
        }

        orden.setDetalles(detalles);

        // Guardar la orden
        ordenRepository.save(orden);

        // Retornar DTO básico con ID generado
        OrdenDTO respuesta = new OrdenDTO();
        respuesta.setId(orden.getId());
        respuesta.setClienteId(cliente.getId());
        respuesta.setTotal(orden.getTotal());
        // (Omitimos los detalles por simplicidad, o los podés mapear también)

        return respuesta;
    }

    @Override
    public OrdenDTO obtenerOrdenPorId(Long id) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden no encontrada con id: " + id));

        OrdenDTO dto = new OrdenDTO();
        dto.setId(orden.getId());
        dto.setClienteId(orden.getCliente().getId());
        dto.setTotal(orden.getTotal());
        // Podés mapear los detalles si querés también

        return dto;
    }

    @Override
    public List<OrdenDTO> listarOrdenes() {
        List<Orden> ordenes = ordenRepository.findAll();
        List<OrdenDTO> dtos = new ArrayList<>();

        for (Orden orden : ordenes) {
            OrdenDTO dto = new OrdenDTO();
            dto.setId(orden.getId());
            dto.setClienteId(orden.getCliente().getId());
            dto.setTotal(orden.getTotal());
            dtos.add(dto);
        }

        return dtos;
    }
}
