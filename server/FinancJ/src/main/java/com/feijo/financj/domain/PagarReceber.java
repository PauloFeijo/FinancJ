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
import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagarReceber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@NonNull
	private String descricao;
	
	@NonNull
	private Integer numParcelas;
	
	@NonNull
	private Double valorTotal;
	
	@NonNull
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date vencimento;
	
	@NonNull
	private Double valorPago;
	
	@NonNull
	@ManyToOne
	private Categoria categoria;
	
	@NonNull
	@ManyToOne
	private Conta conta;
	
	@OneToMany(mappedBy = "id.pagarReceber", cascade=CascadeType.ALL)
	private Set<Parcela> parcelas = new HashSet<>();
	
	public PagarReceber() {
		super();
	}

	public PagarReceber(Integer id, String descricao, Integer numParcelas, Double valorTotal, Date vencimento,
			Double valorPago, Categoria categoria, Conta conta) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.numParcelas = numParcelas;
		this.valorTotal = valorTotal;
		this.vencimento = vencimento;
		this.valorPago = valorPago;
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
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
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