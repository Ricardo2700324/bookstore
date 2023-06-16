package com.ricardo.bookstore.servicesTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.bookstore.domain.Categoria;
import com.ricardo.bookstore.dtos.CategoriaDTO;
import com.ricardo.bookstore.repositories.CategoriaRepository;
import com.ricardo.bookstore.services.CategoriaService;
import com.ricardo.bookstore.services.exceptions.ObjectNotFoundException;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoriaServiceTest {
	
	@Mock
    private CategoriaRepository repository;

    @InjectMocks
    private CategoriaService service;

    @Test
    void findById_existingId_shouldReturnCategoria() {
        
        Integer id = 1;
        Categoria categoria = new Categoria();
        categoria.setId(id);
        Optional<Categoria> optionalCategoria = Optional.of(categoria);
        when(repository.findById(id)).thenReturn(optionalCategoria);

       
        Categoria result = service.findById(id);

        
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findById_nonExistingId_shouldThrowException() {
        
        Integer id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        
        assertThrows(ObjectNotFoundException.class, () -> service.findById(id));
    }

    @Test
    void findAll_shouldReturnListOfCategorias() {
        
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria());
        categorias.add(new Categoria());
        when(repository.findAll()).thenReturn(categorias);

       
        List<Categoria> result = service.findAll();

        
        assertEquals(categorias.size(), result.size());
    }

    @Test
    void create_shouldSaveCategoria() {
        
        Categoria categoria = new Categoria();
        when(repository.save(any(Categoria.class))).thenReturn(categoria);

        
        Categoria result = service.create(categoria);

        
        assertNotNull(result);
        assertEquals(categoria, result);
    }

    @Test
    void update_existingId_shouldReturnUpdatedCategoria() {
        
        Integer id = 1;
        CategoriaDTO objetoDTO = new CategoriaDTO();
        objetoDTO.setNome("Novo Nome");
        objetoDTO.setDescricao("Nova Descrição");

        Categoria categoria = new Categoria();
        categoria.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(categoria));
        when(repository.save(any(Categoria.class))).thenReturn(categoria);

       
        Categoria result = service.update(id, objetoDTO);

        
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(objetoDTO.getNome(), result.getNome());
        assertEquals(objetoDTO.getDescricao(), result.getDescricao());
    }

    @Test
    void update_nonExistingId_shouldThrowException() {
       
        Integer id = 1;
        CategoriaDTO objetoDTO = new CategoriaDTO();
        objetoDTO.setNome("Novo Nome");
        objetoDTO.setDescricao("Nova Descrição");

        when(repository.findById(id)).thenReturn(Optional.empty());

        
        assertThrows(ObjectNotFoundException.class, () -> service.update(id, objetoDTO));
    }

    @Test
    void delete_existingId_shouldDeleteCategoria() {
        
        Integer id = 1;
        Categoria categoria = new Categoria();
        categoria.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(categoria));

        
        assertDoesNotThrow(() -> service.delete(id));

        
        verify(repository, times(1)).deleteById(id);
    }
    
    
    @Test
    void delete_nonExistingId_shouldThrowException() {
        
        Integer id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());

        
        assertThrows(ObjectNotFoundException.class, () -> service.delete(id));
    }
}
