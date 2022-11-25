package com.ricardo.bookstore.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.bookstore.domain.Categoria;
import com.ricardo.bookstore.domain.Livro;
import com.ricardo.bookstore.repositories.CategoriaRepository;
import com.ricardo.bookstore.repositories.LivroRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepositori;
	@Autowired
	private LivroRepository livroRepositori;
	
	public void instaciaBaseDeDados() {
		Categoria cat1 = new Categoria(null, "Informatica", "Livro de TI");
		Categoria cat2 = new Categoria(null, "Publicidade", "Livro de Publicidade");
		Categoria cat3 = new Categoria(null, "Biografias", "Livro de Biografia");
		
		Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Loren ipsum", cat1);
		Livro l2 = new Livro(null, "Engenharia de Software", "Louis V. Gerstner", "Loren ipsum", cat1);
		Livro l3 = new Livro(null, "Java", "Michele Brito", "Loren ipsum", cat2);
		Livro l4 = new Livro(null, "I Robo", "Isac Asimov", "Loren ipsum", cat2);
		
		cat1.getLivros().addAll(Arrays.asList(l1, l2));
		cat2.getLivros().addAll(Arrays.asList(l3, l4));
		
		this.categoriaRepositori.saveAll(Arrays.asList(cat1,cat2, cat3));
		this.livroRepositori.saveAll(Arrays.asList(l1,l2, l3, l4));
	}

}
