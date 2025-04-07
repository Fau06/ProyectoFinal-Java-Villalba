package com.example.fau.dtos;

public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion; // NUEVO CAMPO
    private Double precio;
    private Integer stock;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() { // NUEVO GETTER
        return descripcion;
    }

    public void setDescripcion(String descripcion) { // NUEVO SETTER
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
