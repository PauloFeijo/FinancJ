package com.feijo.financj.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Parcela implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ParcelaPK id = new ParcelaPK();

	@NonNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date vencimento;

	@NonNull
	private Double valor;

	private Double valorPago;

	public Parcela() {
		super();
	}

	public Parcela(PagarReceber pagarReceber, Integer numParcela, Date vencimento, Double valor, Double valorPago) {
		super();
		this.id.setPagarReceber(pagarReceber);
		this.id.setNumParcela(numParcela);
		this.vencimento = vencimento;
		this.valor = valor;
		this.valorPago = valorPago;
	}

	@JsonIgnore
	public PagarReceber getPagarReceber() {
		return id.getPagarReceber();
	}

	@JsonIgnore
	public void setPagarReceber(PagarReceber pagarReceber) {
		id.setPagarReceber(pagarReceber);
	}

	public Integer getNumParcela() {
		return id.getNumParcela();
	}

	public void setNumParcela(Integer numParcela) {
		id.setNumParcela(numParcela);
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

}
