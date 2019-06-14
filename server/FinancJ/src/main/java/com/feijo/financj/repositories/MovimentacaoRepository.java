package com.feijo.financj.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.Movimentacao;
import com.feijo.financj.domain.Parcela;
import com.feijo.financj.domain.Usuario;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer>{

	public List<Movimentacao> findByUsuario(Usuario usuario);
	
	public Movimentacao findByParcela(Parcela parcela);

}
