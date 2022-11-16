package com.example.clinica.service;

import com.example.clinica.dao.IDao;
import com.example.clinica.model.Odontologo;
import com.example.clinica.model.Paciente;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@AllArgsConstructor
@Service
public class PacienteServiceIpl implements PacienteService {

    @Autowired
        private final IDao<Paciente> idao;


        @Override
        public Paciente guardar(Paciente p) {
            return idao.guardar(p);
        }

        @Override
        public void eliminar(int id) {
            idao.eliminar(id);
        }

        @Override
        @Transactional
        public void modificar(int pacienteId, String apellido, String nombre, String dni, String domicilio, LocalDate alta) {
            var p = idao.buscar(pacienteId).orElseThrow(() -> new IllegalStateException(
                    "El paciente con id " + pacienteId + " no existe."
            ));
            if (apellido != null && apellido.length() > 0 && !Objects.equals(p.getApellido(), apellido)) {
                p.setApellido(apellido);
            }
            if (nombre != null && nombre.length() > 0 && !Objects.equals(p.getNombre(), nombre)) {
                p.setNombre(nombre);

            }
            if (dni != null && dni.length() > 0 && !Objects.equals(p.getDni(), dni)) {
                p.setDni(dni);
            }
            if (domicilio != null && dni.length() > 0 && !Objects.equals(p.getDomicilio(), domicilio)) {
                p.setDomicilio(domicilio);
            }
            if (alta != null && dni.length() > 0 && !Objects.equals(p.getAlta(), alta)) {
                p.setAlta(alta);
            }
            idao.modificar(p);
        }

        @Override
        public Optional<Paciente> buscar(int id) {
            return idao.buscar(id);
        }

        @Override
        public List<Paciente> listar() {
            return idao.listar();
        }
}
