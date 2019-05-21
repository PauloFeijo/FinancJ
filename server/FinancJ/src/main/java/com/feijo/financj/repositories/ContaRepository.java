package com.feijo.financj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
	
	String SQL = "select coalesce(sum(valor),0.0) from Movimentacao where conta.id = :conta and tipo = ";

	@Query(SQL + "'R'")
    public Double somaReceitas(@Param("conta") Integer conta);
	
	@Query(SQL + "'D'")
    public Double somaDespesas(@Param("conta") Integer conta);		
}
