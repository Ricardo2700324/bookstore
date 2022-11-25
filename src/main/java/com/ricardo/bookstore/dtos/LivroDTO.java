package com.ricardo.bookstore.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ricardo.bookstore.domain.Livro;

public class LivroDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;
	private String titulo;
	
	public LivroDTO() {
	}


	public LivroDTO(Livro obj) {
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
		//this.nome_autor = obj.getNome_autor();
		//this.texto = obj.getTexto();
		//this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

//	public String getNome_autor() {
//		return nome_autor;
//	}
//
//	public void setNome_autor(String nome_autor) {
//		this.nome_autor = nome_autor;
//	}
//
//	public String getTexto() {
//		return texto;
//	}
//
//	public void setTexto(String texto) {
//		this.texto = texto;
//	}

}
