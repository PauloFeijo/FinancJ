package com.feijo.financj.domain.DTO;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private String tipo;
	private Integer categoriaPaiId;
	
	public CategoriaDTO() {
		super();
	}
	
	public CategoriaDTO(Integer id, String descricao, String tipo, Integer categoriaPaiId) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipo;
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
