package com.feijo.financj.domain.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransferenciaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message = "Campo obrigatório")
	private Integer origemId;
	
	@NotNull(message = "Campo obrigatório")
	private Integer destinoId;
	
	@NotNull(message = "Campo obrigatório")
	@JsonFormat(pattern="dd/MM/yyyy hh:mm:ss")
	private Date data;
	
	@NotNull(message = "Campo obrigatório")
	private Double valor;

	public TransferenciaDTO() {
		super();
	}
	
	public TransferenciaDTO(Integer id, Integer origemId, Integer destinoId, Date data, Double valor) {
		super();
		this.id = id;
		this.origemId = origemId;
		this.destinoId = destinoId;
		this.data = data;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrigemId() {
		return origemId;
	}

	public void setOrigemId(Integer origemId) {
		this.origemId = origemId;
	}

	public Integer getDestinoId() {
		return destinoId;
	}

	public void setDestinoId(Integer destinoId) {
		this.destinoId = destinoId;
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
	
}
