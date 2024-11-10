package com.prueba.nave.service;

import com.prueba.nave.entity.Nave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INaveService {

    List<Nave> findAllNaves();

    //Consultar todas las naves utilizando paginación.
    Page<Nave> findAllNavesPagination(Pageable pageable);

    //Consultar una única nave por id.
    Nave findById(long id);

    //Consultar todas las naves que contienen, en su nombre, el valor de un parámetro enviado en
    //la petición
    List<Nave> searchNameNave(String name);

    //Crear una nueva nave.
    void createNave(Nave nave);

    // Modificar una nave.
    Nave updateNave(Nave nave);
    // Eliminar una nave.
    void deleteNave(long id);

}
