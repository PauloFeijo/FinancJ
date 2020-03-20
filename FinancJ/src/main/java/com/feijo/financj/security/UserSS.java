package com.feijo.financj.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.feijo.financj.domain.enums.Perfil;

public class UserSS implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String senha;
	private Collection<? extends GrantedAuthority> autorities; 
	
	public UserSS() {
		super();
	}

	public UserSS(String usuario, String senha, Set<Perfil> perfis) {
		super();
		this.usuario = usuario;
		this.senha = senha;
		this.autorities = perfis.stream().map(p -> new SimpleGrantedAuthority(p.getDescricao())).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasHole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}
	
}
