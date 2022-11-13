package com.example.clinica.model;

import java.time.LocalDate;

public record Paciente(long id, String apellido, String nombre, int dni, String domicilio, LocalDate alta) {
}
