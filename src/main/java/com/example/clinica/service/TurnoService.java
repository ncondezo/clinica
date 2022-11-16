package com.example.clinica.service;

import com.example.clinica.dao.IDao;
import com.example.clinica.model.Odontologo;
import com.example.clinica.model.Paciente;
import com.example.clinica.model.Turno;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TurnoService implements TuService{

    @Autowired
    private final IDao<Turno>tDao;


    @Override
    public Turno guardar(Turno t) {
        return tDao.guardar(t);
    }

    @Override
    public void eliminar(int id) {
        tDao.eliminar(id);
    }

    @Override
    public void modificar(int id, LocalDateTime fecha) {
        var turno = tDao.buscar(id).orElseThrow(() -> new IllegalStateException(
                "Turno inexistente."
        ));
        if (fecha != null && !Objects.equals(turno.getFecha(), fecha)) {
            turno.setFecha(fecha);
        }
        tDao.modificar(turno);


    }

    @Override
    public Optional<Turno> buscar(int id) {
        return tDao.buscar(id);
    }

    @Override
    public List<Turno> listar() {
        return tDao.listar();
    }
}


