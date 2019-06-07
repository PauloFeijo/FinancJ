package com.feijo.financj.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Transferencia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@ManyToOne
	private Conta origem;
	
	@ManyToOne
	private Conta destino;
	
	@OneToOne
	private Movimentacao entrada;
	
	@OneToOne
	private Movimentacao saida;
	
	@JsonFormat(pattern="dd/MM/yyyy hh:mm:ss")
	private Date data;
	
	private Double valor;
	
	@JsonIgnore
	@ManyToOne
	private Usuario usuario;	

	public Transferencia() {
		super();
	}
	
	public Transferencia(Integer id, Conta origem, Conta destino, Date data, Double valor) {
		super();
		this.id = id;
		this.origem = origem;
		this.destino = destino;
		this.data = data;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getOrigem() {
		return origem;
	}

	public void setOrigem(Conta origem) {
		this.origem = origem;
	}

	public Conta getDestino() {
		return destino;
	}

	public void setDestino(Conta destino) {
		this.destino = destino;
	}

	public Movimentacao getEntrada() {
		return entrada;
	}

	public void setEntrada(Movimentacao entrada) {
		this.entrada = entrada;
	}

	public Movimentacao getSaida() {
		return saida;
	}

	public void setSaida(Movimentacao saida) {
		this.saida = saida;
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
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Transferencia other = (Transferencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
