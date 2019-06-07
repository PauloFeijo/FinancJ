package com.feijo.financj.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
	
	String SQL_MOV = "select coalesce(sum(valor),0.0) from Movimentacao where conta.id = :conta and tipo = ";
	String COND_PERIODO = " and data between :inicio and :fim";

	@Query(SQL_MOV + "'R'")
    public Double somaReceitas(@Param("conta") Integer conta);
	
	@Query(SQL_MOV + "'D'")
    public Double somaDespesas(@Param("conta") Integer conta);
	
	@Query("SELECT coalesce(sum(p.valor),0.0) - coalesce(sum(p.valorPago),0.0) "
		+ "FROM Parcela p, PagarReceber pr  "
		+ "WHERE p.id.pagarReceber.id = pr.id AND pr.conta.id = :conta")
	public Double somaFaturaFutura(@Param("conta") Integer conta);
	
	@Query(SQL_MOV + "'R'" + COND_PERIODO)
    public Double somaReceitasNoPeriodo(@Param("conta") Integer conta, @Param("inicio") Date inicio, @Param("fim") Date fim);
	
	@Query(SQL_MOV + "'D'" + COND_PERIODO)
    public Double somaDespesasNoPeriodo(@Param("conta") Integer conta, @Param("inicio") Date inicio, @Param("fim") Date fim);
}
