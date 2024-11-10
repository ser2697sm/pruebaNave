package com.prueba.nave;

import com.prueba.nave.entity.Nave;
import com.prueba.nave.repository.NaveRepository;
import com.prueba.nave.service.NaveServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class NaveServiceTest {

    @Mock
    private NaveRepository naveRepository;

    @InjectMocks
    NaveServiceImpl naveService;

    @Test
    public void testAllNaves() {
        List<Nave> listNaves = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Nave nave = new Nave();
            nave.setId((long) i);
            nave.setName("Nave " + i);
            nave.setDescription("Descripción " + i);
            nave.setCategory("Categoría " + i);
            listNaves.add(nave);
        }

        Mockito.when(naveRepository.findAll()).thenReturn(listNaves);

        List<Nave> result = naveService.findAllNaves();

        assertNotNull(result);
        assertNotNull(result.get(0));

        Mockito.verify(naveRepository).findAll();

    }

    @Test
    public void testAllNavesPagination() {
        List<Nave> listNaves = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Nave nave = new Nave();
            nave.setId((long) i);
            nave.setName("Nave " + i);
            nave.setDescription("Descripción " + i);
            nave.setCategory("Categoría " + i);
            listNaves.add(nave);
        }

        Pageable pageable = PageRequest.of(0, 2);
        Page<Nave> page = new PageImpl<>(listNaves.subList(0, 2), pageable, listNaves.size());

        Mockito.when(naveRepository.findAll(pageable)).thenReturn(page);

        Page<Nave> result = naveService.findAllNavesPagination(pageable);

        assertNotNull(result);
        assertEquals(2, result.getContent().size()); // Verificar tamaño de la página
        assertEquals(3, result.getTotalPages()); // Verificar número total de páginas
        assertEquals(5, result.getTotalElements()); // Verificar número total de elementos
        //assertEquals("The Expanse", result.getContent().get(0).getName());

        Mockito.verify(naveRepository).findAll(pageable);

    }

    @Test
    public void testFindById() {
        List<Nave> listNaves = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Nave nave = new Nave();
            nave.setId((long) i);
            nave.setName("Nave " + i);
            nave.setDescription("Descripción " + i);
            nave.setCategory("Categoría " + i);
            listNaves.add(nave);
        }

        Mockito.when(naveRepository.findById(Mockito.anyLong())).thenReturn(listNaves.stream().findFirst());

        Nave result = naveService.findById(Mockito.anyLong());

        assertNotNull(result);
        assertEquals("Nave 1",result.getName());

        Mockito.verify(naveRepository).findById(Mockito.anyLong());

    }

    @Test
    public void searchNameNave() {
        List<Nave> listNaves = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Nave nave = new Nave();
            nave.setId((long) i);
            nave.setName("Nave " + i);
            nave.setDescription("Descripción " + i);
            nave.setCategory("Categoría " + i);
            listNaves.add(nave);
        }

        Mockito.when(naveRepository.findByNameContainingIgnoreCase(Mockito.anyString())).thenReturn(listNaves);

        List<Nave> result = naveService.searchNameNave(Mockito.anyString());

        assertNotNull(result);
        assertEquals("Nave 1",result.get(0).getName());

        Mockito.verify(naveRepository).findByNameContainingIgnoreCase(Mockito.anyString());

    }

    @Test
    public void createNave() {

            Nave nave = new Nave();
            nave.setId(1L);
            nave.setName("Nave 1");
            nave.setDescription("Descripción 1");
            nave.setCategory("Categoría 1");



        Mockito.when(naveRepository.save(Mockito.any())).thenReturn(nave);

        // Llamar al método void de creación en el servicio
        naveService.createNave(nave);

        Mockito.verify(naveRepository).save(nave);

    }

    @Test
    public void updateNave() {

        Nave nave = new Nave();
        nave.setId(1L);
        nave.setName("Nave 1");
        nave.setDescription("Descripción 1");
        nave.setCategory("Categoría 1");

        Mockito.when(naveRepository.findById(1L)).thenReturn(Optional.of(nave));

        nave.setName("Nave 2");
        Mockito.when(naveRepository.save(Mockito.any())).thenReturn(nave);


        Nave result = naveService.updateNave(nave);

        assertNotNull(result);
        assertEquals("Nave 2",result.getName());

        Mockito.verify(naveRepository).save(nave);
        Mockito.verify(naveRepository).findById(1L);
        Mockito.verify(naveRepository).save(nave);

    }

    @Test
    public void deleteNave() {

        Nave nave = new Nave();
        nave.setId(1L);
        nave.setName("Nave 1");
        nave.setDescription("Descripción 1");
        nave.setCategory("Categoría 1");

        naveService.deleteNave(1L);

        Mockito.verify(naveRepository).deleteById(1L);

    }

}
