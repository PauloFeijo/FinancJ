package com.feijo.financj.domain.DTO;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feijo.financj.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private String tipo;
	private Integer categoriaPaiId;
	
	public CategoriaDTO() {
		super();
	}
	
	public CategoriaDTO(Integer id, String descricao, Integer categoriaPaiId) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.categoriaPaiId = categoriaPaiId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getCategoriaPaiId() {
		return categoriaPaiId;
	}

	public void setCategoriaPaiId(Integer categoriaPaiId) {
		this.categoriaPaiId = categoriaPaiId;
	}
	
}
