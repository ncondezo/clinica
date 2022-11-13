package com.example.clinica.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Repository
public interface IDao<T> {

    T guardar(T t);
    void eliminar(int id);
    void modificar(T t);
    Optional<T> buscar(int id);
    List<T> listar();

}
