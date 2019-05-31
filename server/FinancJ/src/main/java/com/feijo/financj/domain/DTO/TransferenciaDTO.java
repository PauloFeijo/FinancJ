package com.feijo.financj.domain.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.Conta;
import com.feijo.financj.domain.Movimentacao;

public class TransferenciaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NonNull
	private Integer origemId;
	
	@NonNull
	private Integer destinoId;
	
	@NonNull
	@JsonFormat(pattern="dd/MM/yyyy hh:mm:ss")
	private Date data;
	
	@NonNull
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
