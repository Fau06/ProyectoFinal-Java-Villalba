package com.example.fau.repositorios;

import com.example.fau.entidades.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
