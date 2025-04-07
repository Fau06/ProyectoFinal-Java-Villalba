package com.example.fau.dtos;

import java.util.List;

public class OrdenDTO {
    private Long id;
    private Long clienteId;
    private Double total;
    private List<DetalleOrdenDTO> detalles;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetalleOrdenDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrdenDTO> detalles) {
        this.detalles = detalles;
    }
}
