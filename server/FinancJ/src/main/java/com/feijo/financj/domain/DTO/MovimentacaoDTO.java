package com.feijo.financj.domain.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MovimentacaoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message = "Campo obrigatório")
	private Integer contaId;
	
	private String contaDescricao;
	
	@NotNull(message = "Campo obrigatório")
	private Integer categoriaId;
	
	private String categoriaDescricao;
	
	@NotNull(message = "Campo obrigatório")
	private String descricao;
	
	@NotNull(message = "Campo obrigatório")
	@JsonFormat(pattern="dd/MM/yyyy hh:mm:ss")
	private Date data;
	
	@NotNull(message = "Campo obrigatório")
	private Double valor;
	
	@NotNull(message = "Campo obrigatório")
	private String tipo;
	
	public MovimentacaoDTO() {
		super();
	}
	
	public MovimentacaoDTO(Integer id, Integer contaId, Integer categoriaId, String descricao, Date data,
			Double valor, String tipo) {
		super();
		this.id = id;
		this.contaId = contaId;
		this.categoriaId = categoriaId;
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

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
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

	public String getContaDescricao() {
		return contaDescricao;
	}

	public void setContaDescricao(String contaDescricao) {
		this.contaDescricao = contaDescricao;
	}

	public String getCategoriaDescricao() {
		return categoriaDescricao;
	}

	public void setCategoriaDescricao(String categoriaDescricao) {
		this.categoriaDescricao = categoriaDescricao;
	}
}
