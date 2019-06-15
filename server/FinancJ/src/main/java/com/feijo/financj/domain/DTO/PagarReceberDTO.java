package com.feijo.financj.domain.DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.feijo.financj.domain.Parcela;

public class PagarReceberDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message = "Campo obrigatório")
	private String descricao;
	
	@NotNull(message = "Campo obrigatório")
	private Integer numParcelas;
	
	@NotNull(message = "Campo obrigatório")
	private Double valorTotal;
	
	@NotNull(message = "Campo obrigatório")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date vencimento;
	
	@NotNull(message = "Campo obrigatório")
	private Double valorPago;
	
	@NotNull(message = "Campo obrigatório")
	private Integer categoriaId;
	
	private String categoriaDescricao;
	
	@NotNull(message = "Campo obrigatório")
	private Integer contaId;
	
	private String contaDescricao;
	
	private Set<Parcela> parcelas = new HashSet<>();
	
	public PagarReceberDTO() {
		super();
	}	

	public PagarReceberDTO(Integer id, String descricao, Integer numParcelas, Double valorTotal, Date vencimento,
			Integer categoriaId, Integer contaId, Set<Parcela> parcelas) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.numParcelas = numParcelas;
		this.valorTotal = valorTotal;
		this.vencimento = vencimento;
		this.categoriaId = categoriaId;
		this.contaId = contaId;
		this.parcelas = parcelas;
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

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}
	
	public String getCategoriaDescricao() {
		return categoriaDescricao;
	}

	public void setCategoriaDescricao(String categoriaDescricao) {
		this.categoriaDescricao = categoriaDescricao;
	}

	public String getContaDescricao() {
		return contaDescricao;
	}

	public void setContaDescricao(String contaDescricao) {
		this.contaDescricao = contaDescricao;
	}

	public Set<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(Set<Parcela> parcelas) {
		this.parcelas = parcelas;
	}
}
