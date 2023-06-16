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
import com.ricardo.bookstore.domain.Livro;
import com.ricardo.bookstore.repositories.LivroRepository;
import com.ricardo.bookstore.services.CategoriaService;
import com.ricardo.bookstore.services.LivroService;
import com.ricardo.bookstore.services.exceptions.ObjectNotFoundException;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LivroServiceTest {
	
	 @Mock
	    private LivroRepository repository;

	    @Mock
	    private CategoriaService categoriaService;

	    @InjectMocks
	    private LivroService service;

	    @Test
	    void findById_existingId_shouldReturnLivro() {
	       
	        Integer id = 1;
	        Livro livro = new Livro();
	        livro.setId(id);
	        Optional<Livro> optionalLivro = Optional.of(livro);
	        when(repository.findById(id)).thenReturn(optionalLivro);

	        
	        Livro result = service.findById(id);

	       
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
	    void findAll_existingCategoryId_shouldReturnListOfLivros() {
	       
	        Integer categoryId = 1;
	        List<Livro> livros = new ArrayList<>();
	        livros.add(new Livro());
	        livros.add(new Livro());
	        when(categoriaService.findById(categoryId)).thenReturn(new Categoria());
	        when(repository.findAllByCategoria(categoryId)).thenReturn(livros);

	       
	        List<Livro> result = service.findAll(categoryId);

	      
	        assertEquals(livros.size(), result.size());
	    }

	    @Test
	    void findAll_nonExistingCategoryId_shouldThrowException() {
	       
	        Integer categoryId = 1;
	        when(categoriaService.findById(categoryId)).thenThrow(ObjectNotFoundException.class);

	        
	        assertThrows(ObjectNotFoundException.class, () -> service.findAll(categoryId));
	    }

	    @Test
	    void update_existingId_shouldReturnUpdatedLivro() {
	        
	        Integer id = 1;
	        Livro livro = new Livro();
	        Livro updatedLivro = new Livro();
	        updatedLivro.setId(id);

	        when(repository.findById(id)).thenReturn(Optional.of(livro));
	        when(repository.save(any(Livro.class))).thenReturn(updatedLivro);

	        
	        Livro result = service.update(id, livro);

	        
	        assertNotNull(result);
	        assertEquals(id, result.getId());
	    }

	    @Test
	    void update_nonExistingId_shouldThrowException() {
	       
	        Integer id = 1;
	        Livro livro = new Livro();
	        when(repository.findById(id)).thenReturn(Optional.empty());

	        
	        assertThrows(ObjectNotFoundException.class, () -> service.update(id, livro));
	    }
	    
	    
	    @Test
	    void create_existingCategoryId_shouldSaveLivro() {
	       
	        Integer categoryId = 1;
	        Livro livro = new Livro();
	        Categoria categoria = new Categoria();
	        categoria.setId(categoryId);
	        
	        when(categoriaService.findById(categoryId)).thenReturn(categoria);
	        when(repository.save(any(Livro.class))).thenAnswer(invocation -> {
	            Livro savedLivro = invocation.getArgument(0);
	            savedLivro.setId(1); 
	            return savedLivro;
	        });

	        
	        Livro result = service.create(categoryId, livro);

	        
	        assertNotNull(result);
	        assertEquals(1, result.getId()); 
	        assertEquals(livro, result);
	        assertEquals(categoria, result.getCategoria());
	    }
	    
	    
	    @Test
	    void create_nonExistingCategoryId_shouldThrowException() {
	       
	        Integer categoryId = 1;
	        Livro livro = new Livro();

	        when(categoriaService.findById(categoryId)).thenThrow(ObjectNotFoundException.class);

	        
	        assertThrows(ObjectNotFoundException.class, () -> service.create(categoryId, livro));
	    }

	    @Test
	    void delete_existingId_shouldDeleteLivro() {
	        
	        Integer id = 1;
	        Livro livro = new Livro();
	        livro.setId(id);
	        when(repository.findById(id)).thenReturn(Optional.of(livro));

	       
	        assertDoesNotThrow(() -> service.delete(id));

	       
	        verify(repository, times(1)).delete(livro);
	    }

	    @Test
	    void delete_nonExistingId_shouldThrowException() {
	      
	        Integer id = 1;
	        when(repository.findById(id)).thenReturn(Optional.empty());

	       
	        assertThrows(ObjectNotFoundException.class, () -> service.delete(id));
	    }
	
}