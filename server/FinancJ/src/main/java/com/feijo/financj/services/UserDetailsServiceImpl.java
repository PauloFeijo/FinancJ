package com.feijo.financj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.feijo.financj.domain.Usuario;
import com.feijo.financj.repositories.UsuarioRepository;
import com.feijo.financj.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		Usuario user = repo.findById(usuario).orElse(null);
		
		if (user == null) {
			throw new UsernameNotFoundException(usuario);
		}
		return new UserSS(user.getUsuario(), user.getSenha(), user.getPerfis());
	}

}
