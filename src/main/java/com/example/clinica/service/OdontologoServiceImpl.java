package com.example.clinica.service;

import com.example.clinica.dao.IDao;
import com.example.clinica.model.Odontologo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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
    @Transactional
    public void modificar(int odontologoId,String apellido,String nombre,String matricula) {
        var odontologo = idao.buscar(odontologoId).orElseThrow(() -> new IllegalStateException(
                "El odontólogo con id " + odontologoId + " no existe."
        ));
        if (apellido != null && apellido.length() > 0 && !Objects.equals(odontologo.getApellido(), apellido)) {
            odontologo.setApellido(apellido);
        }
        if (nombre != null && nombre.length() > 0 && !Objects.equals(odontologo.getNombre(), nombre)) {
            odontologo.setNombre(nombre);

        }
        if (matricula != null && matricula.length() > 0 && !Objects.equals(odontologo.getMatricula(), matricula)) {
            odontologo.setMatricula(matricula);
        }
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
