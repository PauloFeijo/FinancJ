package com.feijo.financj.domain.enums;

public enum Tipo {
	RECEITA("R", "Receita"),
	DESPESA("D", "Despesa");
	
	private String flag;
	private String descricao;
	
	private Tipo(String flag, String descricao) {
		this.flag = flag;
		this.descricao = descricao;
	}

	public String getFlag() {
		return flag;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Tipo toEnum(String flag) {
		if (flag == null) return null;
		
		for (Tipo tipo : Tipo.values()) {
			if (flag.equals(tipo.getFlag())) {
				return tipo;
			}
		}
		
		throw new IllegalArgumentException("Tipo inválido: " + flag);
	}
	
	
}
