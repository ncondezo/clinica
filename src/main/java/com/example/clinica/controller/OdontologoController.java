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
@RequestMapping(path="odontologo")
public class OdontologoController {

   @Autowired
    private final OdontologoService odontologoService;


    @GetMapping
    @ResponseBody
    public List<Odontologo> getOdontologos(){
        return odontologoService.listar();

    }

    @PostMapping
    public Odontologo addOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardar(odontologo);

    }
    @GetMapping("/{id}")
    public Optional<Odontologo> buscar(@PathVariable int id){
        return odontologoService.buscar(id);
    }

}
