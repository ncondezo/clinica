package com.example.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class Paciente {

    private int id;
    private String apellido;
    private String nombre;
    private String dni;
    private String domicilio;
    private LocalDate alta;

    public Paciente(){};

    public Paciente(String apellido, String nombre, String dni, String domicilio, LocalDate alta) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio=domicilio;
        this.alta = alta;
    }
}
