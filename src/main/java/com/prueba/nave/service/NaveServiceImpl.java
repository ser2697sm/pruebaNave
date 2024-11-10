package com.prueba.nave.service;

import com.prueba.nave.commons.NaveException;
import com.prueba.nave.config.CacheConfig;
import com.prueba.nave.entity.Nave;
import com.prueba.nave.repository.NaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NaveServiceImpl implements INaveService {

    @Autowired
    private NaveRepository naveRepository;

    @Override
    public List<Nave> findAllNaves() {
        return naveRepository.findAll();
    }

    @Override
    public Page<Nave> findAllNavesPagination(Pageable pageable) {
        return naveRepository.findAll(pageable);
    }

    @Override
    public Nave findById(long id) {

        // Verificar si el id es negativo antes de hacer la consulta
        if (id < 0) {
            // Lanzar una excepción personalizada si el id es negativo
            throw new IllegalArgumentException("El ID no puede ser negativo: " + id);
        }

        return naveRepository.findById(id).orElseThrow(() -> new NaveException(HttpStatus.NOT_FOUND,"Nave no encontrada para el id: " + id));
    }

    @Override
    @Cacheable(value = CacheConfig.NAVE_INFO_CACHE, unless = "#result == null")
    public List<Nave> searchNameNave(String name) {
        return naveRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void createNave(Nave nave) {
        naveRepository.save(nave);
    }

    @Override
    public Nave updateNave(Nave nave) {
        naveRepository.findById(nave.getId()).orElseThrow();
        return naveRepository.save(nave);
    }

    public void deleteNave(long id) {
        naveRepository.deleteById(id);
    }
}
