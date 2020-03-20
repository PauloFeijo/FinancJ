package com.feijo.financj.repositories;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.Parcela;
import com.feijo.financj.domain.ParcelaPK;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, ParcelaPK>{
	
	@Query(" SELECT p FROM Parcela p, PagarReceber pr  "
		   + "WHERE p.id.pagarReceber.id = pr.id "
		   + "  AND pr.conta.id = :conta "
		   + "  AND p.vencimento between :inicio and :fim "
		   + "  AND p.valorPago = 0.00")
	public Set<Parcela> findEmAbertoByContaPeriodo(@Param("conta") Integer conta, @Param("inicio") Date inicio, @Param("fim") Date fim);
}	
