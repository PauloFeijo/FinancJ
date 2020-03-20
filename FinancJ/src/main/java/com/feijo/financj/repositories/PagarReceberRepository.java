package com.feijo.financj.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.PagarReceber;
import com.feijo.financj.domain.Usuario;

@Repository
public interface PagarReceberRepository extends JpaRepository<PagarReceber, Integer>{
	
	public List<PagarReceber> findByUsuario(Usuario usuario);
}
