package com.feijo.financj.domain.DTO;

import java.io.Serializable;
import java.util.Date;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PagarFaturaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NonNull
	private Integer cartaoId;
	
	@NonNull
	private Integer contaId;
	
	@NonNull
	private Double valor;
	
	@NonNull
	@JsonFormat(pattern="dd/MM/yyyy hh:mm:ss")
	private Date data;
	
	public PagarFaturaDTO() {
		super();
	}	

	public PagarFaturaDTO(Integer cartaoId, Integer contaId, Double valor, Date data) {
		super();
		this.cartaoId = cartaoId;
		this.contaId = contaId;
		this.valor = valor;
		this.data = data;
	}

	public Integer getCartaoId() {
		return cartaoId;
	}

	public void setCartaoId(Integer cartaoId) {
		this.cartaoId = cartaoId;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}	
}
