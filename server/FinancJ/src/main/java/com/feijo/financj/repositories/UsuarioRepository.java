package com.feijo.financj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	public Usuario findByUsuario(String usuario);
}
