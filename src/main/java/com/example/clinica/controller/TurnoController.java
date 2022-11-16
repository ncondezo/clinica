package com.example.clinica.controller;

import com.example.clinica.model.Paciente;
import com.example.clinica.service.TuService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.clinica.model.Turno;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path="/turno")
public class TurnoController {

    @Autowired
    private final TuService turnoService;

    @GetMapping
    @ResponseBody
    public List<Turno> getTurnos(){
        return turnoService.listar();
    }

    @PostMapping
    public Turno addTurno(@RequestBody Turno t){
        return turnoService.guardar(t);
    }

    @GetMapping("/{id}")
    public Optional<Turno> buscar(@PathVariable int id){
        return turnoService.buscar(id);
    }

    @PutMapping(path="/{id}")
    public void updatePaciente(
            @PathVariable("id") int id,
            @RequestParam(required = false) LocalDateTime fecha){
        turnoService.modificar(id,fecha);
    }





}
