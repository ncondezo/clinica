package com.example.clinica.controller;

import com.example.clinica.model.Odontologo;
import com.example.clinica.model.Paciente;
import com.example.clinica.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping(path="/paciente")
public class PacienteController {

    @Autowired
    private final PacienteService pacienteService;

    @GetMapping
    @ResponseBody
    public List<Paciente> getPacientes(){
        return pacienteService.listar();
    }

    @PostMapping
    public Paciente addPaciente(@RequestBody Paciente p){
        return pacienteService.guardar(p);
    }
    @GetMapping("/{id}")
    public Optional<Paciente> buscar(@PathVariable int id){
        return pacienteService.buscar(id);
    }

    @PutMapping(path="/{id}")
    public void updatePaciente(
            @PathVariable("id") int odontologoId,
            @RequestParam(required = false)String apellido,
            @RequestParam(required = false)String nombre,
            @RequestParam(required = false)String dni,
            @RequestParam(required = false)String domicilio,
            @RequestParam(required = false) LocalDate alta){
        pacienteService.modificar(odontologoId,apellido,nombre,dni,domicilio,alta);
    }


}

