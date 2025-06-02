package io.github.paulofeijo.financj.repositories;

import io.github.paulofeijo.financj.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query("select sum(e.amount) from Transfer e where e.accountCredit.id = :accountId")
    Optional<Double> getSumAmountByAccountCredit(Long accountId);

    @Query("select sum(e.amount) from Transfer e where e.accountDebit.id = :accountId")
    Optional<Double> getSumAmountByAccountDebit(Long accountId);
}
