package com.feijo.financj.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
public class CartaoCredito extends Conta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@NonNull
	private Date dataFatura;
	
	@NonNull
	private Double faturaFechada;
	
	@NonNull
	private Double valorLimite;
	
	@NonNull
	private Double faturaFutura;
	
	public CartaoCredito() {
		super();
	}
	
	public CartaoCredito(Integer id, String descricao, String numero, Double saldo) {
		super(id, descricao, numero, saldo);
	}

	public CartaoCredito(Integer id, String descricao, String numero, Double saldo, Date dataFatura,
			Double faturaFechada, Double valorLimite, Double faturaFutura) {
		super(id, descricao, numero, saldo);
		this.dataFatura = dataFatura;
		this.faturaFechada = faturaFechada;
		this.valorLimite = valorLimite;
		this.faturaFutura = faturaFutura;
	}

	public Date getDataFatura() {
		return dataFatura;
	}

	public void setDataFatura(Date dataFatura) {
		this.dataFatura = dataFatura;
	}

	public Double getFaturaFechada() {
		return faturaFechada;
	}

	public void setFaturaFechada(Double faturaFechada) {
		this.faturaFechada = faturaFechada;
	}

	public Double getValorLimite() {
		return valorLimite;
	}

	public void setValorLimite(Double valorLimite) {
		this.valorLimite = valorLimite;
	}
	
	public Double getFaturaFutura() {
		return faturaFutura;
	}

	public void setFaturaFutura(Double faturaFutura) {
		this.faturaFutura = faturaFutura;
	}

	public double getFaturaAtual() {
		return ((getSaldo() != 0) ? (getSaldo() * -1) : 0) - this.faturaFechada;
	}
	
	public double getLimiteDisponivel() {
		return this.valorLimite - this.getFaturaAtual() - this.faturaFechada - this.faturaFutura;
	}
		
}
