package com.feijo.financj.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class CartaoCredito extends Conta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@NotNull(message = "Campo obrigatório")
	private Date dataFatura;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@NotNull(message = "Campo obrigatório")
	private Date dataVencimentoFatura;
	
	@NotNull(message = "Campo obrigatório")
	private Integer diasVencimentoFatura;
	
	@NotNull(message = "Campo obrigatório")
	private Double faturaFechada;
	
	@NotNull(message = "Campo obrigatório")
	private Double valorLimite;
	
	@NotNull(message = "Campo obrigatório")
	private Double faturaFutura;
	
	public CartaoCredito() {
		super();
	}
	
	public CartaoCredito(Integer id, String descricao, String numero, Double saldo) {
		super(id, descricao, numero, saldo);
	}

	public CartaoCredito(Integer id, String descricao, String numero, Double saldo, Date dataFatura,
			Date dataVencimentoFatura, Integer diasVencimentoFatura, Double valorLimite) {
		super(id, descricao, numero, saldo);
		this.dataFatura = dataFatura;
		this.dataVencimentoFatura = dataVencimentoFatura;
		this.diasVencimentoFatura = diasVencimentoFatura;
		this.valorLimite = valorLimite;
		this.faturaFechada = 0.0;
		this.faturaFutura = 0.0;
	}

	public Date getDataFatura() {
		return dataFatura;
	}

	public void setDataFatura(Date dataFatura) {
		this.dataFatura = dataFatura;
	}

	public Date getDataVencimentoFatura() {
		return dataVencimentoFatura;
	}

	public void setDataVencimentoFatura(Date dataVencimentoFatura) {
		this.dataVencimentoFatura = dataVencimentoFatura;
	}
	
	public Integer getDiasVencimentoFatura() {
		return diasVencimentoFatura;
	}

	public void setDiasVencimentoFatura(Integer diasVencimentoFatura) {
		this.diasVencimentoFatura = diasVencimentoFatura;
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
