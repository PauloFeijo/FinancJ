package com.feijo.financj.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Embeddable
public class ParcelaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne	
	@NonNull
	@JoinColumn(name="pagarReceberId")
	private PagarReceber pagarReceber;
	
	@NonNull
	private Integer numParcela;

	public PagarReceber getPagarReceber() {
		return pagarReceber;
	}

	public void setPagarReceber(PagarReceber pagarReceber) {
		this.pagarReceber = pagarReceber;
	}

	public Integer getNumParcela() {
		return numParcela;
	}

	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numParcela == null) ? 0 : numParcela.hashCode());
		result = prime * result + ((pagarReceber == null) ? 0 : pagarReceber.hashCode());
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
		ParcelaPK other = (ParcelaPK) obj;
		if (numParcela == null) {
			if (other.numParcela != null)
				return false;
		} else if (!numParcela.equals(other.numParcela))
			return false;
		if (pagarReceber == null) {
			if (other.pagarReceber != null)
				return false;
		} else if (!pagarReceber.equals(other.pagarReceber))
			return false;
		return true;
	}
}
