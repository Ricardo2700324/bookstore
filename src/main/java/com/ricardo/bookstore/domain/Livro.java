package com.ricardo.bookstore.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name = "tb_livro")
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Campo TITULO requerido!")
	@Length(min = 3, max = 50, message = "O campo TITULO deve ter entre 3 e 50 caracteres!")
	private String titulo;
	
	//@NotEmpty(message = "Campo NOME DO AUTOR requerido!")
	@Length(min = 3, max = 50, message = "O campo NOME DO AUTOR deve ter entre 3 e 50 caracteres!")
	private String nome_autor;
	
	//@NotEmpty(message = "Campo TEXTO requerido!")
	@Length(min = 10, max = 5000000, message = "O campo TEXTO deve ter entre 10 e 5.000.000 caracteres!")
	private String texto;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	public Livro() {
	}

	public Livro(Integer id, String titulo, String nome_autor, String texto, Categoria categoria) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.nome_autor = nome_autor;
		this.texto = texto;
		this.categoria = categoria;
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

	public String getNome_autor() {
		return nome_autor;
	}

	public void setNome_autor(String nome_autor) {
		this.nome_autor = nome_autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}