package com.feijo.financj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.feijo.financj.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

}
