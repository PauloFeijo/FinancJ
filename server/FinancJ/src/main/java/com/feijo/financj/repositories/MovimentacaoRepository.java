package com.feijo.financj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.Movimentacao;
import com.feijo.financj.domain.Parcela;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer>{

	public Movimentacao findByParcela(Parcela parcela);

}
