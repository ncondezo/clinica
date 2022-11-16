package com.example.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.dialect.SAPDBDialect;

import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
public class Turno {
    private int id;
    private int odontologoId;
    private int pacienteId;
    private LocalDateTime fecha;

    public Turno(){};

    public Turno(int odontologoId, int pacienteId, LocalDateTime fecha) {
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
        this.fecha = fecha;
    }


}
