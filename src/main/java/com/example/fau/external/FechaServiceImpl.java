package com.example.fau.external;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class FechaServiceImpl implements FechaService {

    @Override
    public LocalDateTime obtenerFechaActual() {
        // Aquí podrías consumir una API externa real, pero simulamos la respuesta
        return LocalDateTime.now();
    }
}
