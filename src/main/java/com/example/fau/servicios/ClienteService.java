package com.example.fau.servicios;

import com.example.fau.dtos.ClienteDTO;
import com.example.fau.entidades.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> obtenerTodos();

    Cliente obtenerPorId(Long id);

    Cliente crear(ClienteDTO clienteDTO);

    Cliente actualizar(Long id, ClienteDTO clienteDTO);

    void eliminar(Long id);
}
