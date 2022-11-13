package com.example.clinica.controller;

import com.example.clinica.model.Odontologo;
import com.example.clinica.service.OdontologoService;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class OdontologoController {

   @Autowired
    private final OdontologoService odontologoService;




    @GetMapping("/odontologos")
    public ArrayList<Odontologo> getOdontologos(Model model){
        return (ArrayList<Odontologo>)odontologoService.listar();

    }

    @PostMapping("/guardar")
    public Odontologo addOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardar(odontologo);

    }
    @GetMapping("/{id}")
    public Optional<Odontologo> buscar(@PathVariable int id){
        return odontologoService.buscar(id);
    }

}
