package com.feijo.financj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feijo.financj.domain.PagarReceber;

@Repository
public interface PagarReceberRepository extends JpaRepository<PagarReceber, Integer>{

}
