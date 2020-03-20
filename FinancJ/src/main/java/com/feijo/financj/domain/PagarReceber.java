package com.feijo.financj.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PagarReceber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@NotNull(message = "Campo obrigatório")
	private String descricao;
	
	@NotNull(message = "Campo obrigatório")
	private Integer numParcelas;
	
	@NotNull(message = "Campo obrigatório")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date vencimento;
	
	@NotNull(message = "Campo obrigatório")
	@ManyToOne
	private Categoria categoria;
	
	@NotNull(message = "Campo obrigatório")
	@ManyToOne
	private Conta conta;
	
	@OneToMany(mappedBy = "id.pagarReceber", cascade=CascadeType.ALL)
	private Set<Parcela> parcelas = new HashSet<>();
	
	@JsonIgnore
	@ManyToOne
	private Usuario usuario;	
	
	public PagarReceber() {
		super();
	}

	public PagarReceber(Integer id, String descricao, Integer numParcelas, Date vencimento,
			Categoria categoria, Conta conta) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.numParcelas = numParcelas;
		this.vencimento = vencimento;
		this.categoria = categoria;
		this.conta = conta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumParcelas() {
		return numParcelas;
	}

	public void setNumParcelas(Integer numParcelas) {
		this.numParcelas = numParcelas;
	}

	public Double getValorTotal() {
		Double soma = 0.0;
		for (Parcela parc : getParcelas()) {
			soma += parc.getValor();
		}
		return soma;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Double getValorPago() {
		Double soma = 0.0;
		for (Parcela parc : getParcelas()) {
			soma += parc.getValorPago();
		}
		return soma;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public Set<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(Set<Parcela> parcelas) {
		this.parcelas = parcelas;
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
		PagarReceber other = (PagarReceber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
