package com.feijo.financj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.Parcela;
import com.feijo.financj.domain.ParcelaPK;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, ParcelaPK>{
	
}	
