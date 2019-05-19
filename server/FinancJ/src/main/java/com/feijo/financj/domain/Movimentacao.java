package com.feijo.financj.domain;

import java.io.Serializable;
import java.util.Currency;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.feijo.financj.domain.enums.Tipo;

@Entity
public class Movimentacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Conta conta;
	
	@ManyToOne
	private Categoria categoria;
	
	private String descricao;
	private Date data;
	private Double valor;
	private String tipo;
	
	public Movimentacao() {
		super();
	}
	
	public Movimentacao(Integer id, Conta conta, Categoria categoria, String descricao, Date data, Double valor,
			Tipo tipo) {
		super();
		this.id = id;
		this.conta = conta;
		this.categoria = categoria;
		this.descricao = descricao;
		this.data = data;
		this.valor = valor;
		setTipo(tipo);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	public Tipo getTipo() {
		return Tipo.toEnum(tipo);
	}

	public void setTipo(Tipo tipo) {
		this.tipo = (tipo == null) ? null : tipo.getFlag();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}