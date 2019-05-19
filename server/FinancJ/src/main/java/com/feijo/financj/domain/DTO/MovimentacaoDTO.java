package com.feijo.financj.domain.DTO;

import java.io.Serializable;
import java.util.Currency;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.Conta;

public class MovimentacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer conta_id;
	private Integer categoria_id;
	private String descricao;
	private Date data;
	private Double valor;
	private String tipo;
	
	public MovimentacaoDTO() {
		super();
	}
	
	public MovimentacaoDTO(Integer id, Integer conta_id, Integer categoria_id, String descricao, Date data,
			Double valor, String tipo) {
		super();
		this.id = id;
		this.conta_id = conta_id;
		this.categoria_id = categoria_id;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConta_id() {
		return conta_id;
	}

	public void setConta_id(Integer conta_id) {
		this.conta_id = conta_id;
	}

	public Integer getCategoria_id() {
		return categoria_id;
	}

	public void setCategoria_id(Integer categoria_id) {
		this.categoria_id = categoria_id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
