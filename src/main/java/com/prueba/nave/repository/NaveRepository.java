package com.prueba.nave.repository;

import com.prueba.nave.entity.Nave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NaveRepository extends JpaRepository<Nave,Long> {

    @Query("SELECT n FROM Nave n WHERE LOWER(n.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Nave> findByNameContainingIgnoreCase(String name);
}
