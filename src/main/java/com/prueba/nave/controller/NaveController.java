package com.prueba.nave.controller;

import com.prueba.nave.entity.Nave;
import com.prueba.nave.service.INaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/nave")
public class NaveController {

    @Autowired
    private INaveService iNaveService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(iNaveService.findAllNaves());
    }

    @GetMapping("/allPagination")
    public Page<Nave> findAllNave(Pageable pageable) {
        return iNaveService.findAllNavesPagination(pageable);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Nave> findById(@PathVariable Long id) {
        return ResponseEntity.ok(iNaveService.findById(id));
    }


    @GetMapping("/findByName/{name}")
    public List<Nave> findByName(@PathVariable String name) {
        return iNaveService.searchNameNave(name);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNave(@RequestBody Nave nave) {
        iNaveService.createNave(nave);
    }

    @PutMapping("/update")
    public Nave updateNave(@RequestBody Nave nave) {
        return iNaveService.updateNave(nave);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteNave(@PathVariable Long id) {
        iNaveService.deleteNave(id);
    }
}
