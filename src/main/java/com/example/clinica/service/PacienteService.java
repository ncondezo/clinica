package com.example.clinica.service;

import com.example.clinica.model.Odontologo;
import com.example.clinica.model.Paciente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PacienteService {
    Paciente guardar(Paciente p);
    void eliminar(int id);
    void modificar(int pacienteId, String apellido, String nombre, String dni, String domicilio, LocalDate alta);
    Optional<Paciente> buscar(int id);
    List<Paciente> listar();
}

