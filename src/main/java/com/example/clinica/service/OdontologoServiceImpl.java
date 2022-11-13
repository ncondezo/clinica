package com.example.clinica.service;

import com.example.clinica.dao.IDao;
import com.example.clinica.model.Odontologo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class OdontologoServiceImpl implements OdontologoService {

    @Autowired
    private final IDao<Odontologo> idao;


    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return idao.guardar(odontologo);
    }

    @Override
    public void eliminar(int id) {
        idao.eliminar(id);
    }

    @Override
    public void modificar(Odontologo odontologo) {
        idao.modificar(odontologo);
    }

    @Override
    public Optional<Odontologo> buscar(int id) {
        return idao.buscar(id);
    }

    @Override
    public List<Odontologo> listar() {
        return idao.listar();
    }
}
