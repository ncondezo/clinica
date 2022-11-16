package com.example.clinica.service;

import com.example.clinica.model.Turno;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TuService {

    Turno guardar(Turno t);
    void eliminar(int id);
    void modificar(int id, LocalDateTime fecha);
    Optional<Turno> buscar(int id);
    List<Turno> listar();
}
