package com.feijo.financj.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.Categoria;
import com.feijo.financj.domain.Usuario;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	public List<Categoria> findByUsuario(Usuario usuario);
}
