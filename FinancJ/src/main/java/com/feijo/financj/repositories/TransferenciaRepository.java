package com.feijo.financj.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.Transferencia;
import com.feijo.financj.domain.Usuario;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer>{

	public List<Transferencia> findByUsuario(Usuario usuario);
}
