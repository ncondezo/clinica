package com.example.clinica.model;


import com.example.clinica.dao.OdontologoH2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Odontologo {

    private int id;
    private String apellido;
    private String nombre;
    private String matricula;

    public Odontologo(){};
    public Odontologo(String apellido, String nombre, String matricula) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }
}



