package com.example.fau.servicios;

import com.example.fau.dtos.ProductoDTO;
import java.util.List;

public interface ProductoService {
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO obtenerProductoPorId(Long id);
    List<ProductoDTO> listarProductos();
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDTO);
    void eliminarProducto(Long id);
}
