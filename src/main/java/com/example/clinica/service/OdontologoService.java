package com.example.clinica.service;

import com.example.clinica.model.Odontologo;

import java.util.List;
import java.util.Optional;

public interface OdontologoService {

    Odontologo guardar(Odontologo odontologo);
    void eliminar(int id);
    void modificar(int odontologoId,String apellido,String nombre,String matricula);
    Optional<Odontologo> buscar(int id);
    List<Odontologo> listar();
}
