package com.feijo.financj.domain.DTO;

import java.io.Serializable;

public class CredenciaisDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String senha;

	public CredenciaisDTO(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}

	public CredenciaisDTO() {
		super();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}