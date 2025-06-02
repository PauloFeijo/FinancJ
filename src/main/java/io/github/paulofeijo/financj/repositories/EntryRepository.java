package io.github.paulofeijo.financj.repositories;

import io.github.paulofeijo.financj.entities.Entry;
import io.github.paulofeijo.financj.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    @Query("select sum(e.amount) from Entry e where e.account.id = :accountId and e.type = :type")
    Optional<Double> getSumAmountByAccountAndType(Long accountId, Type type);

    @Query("select e.account.id from Entry e where e.id = :entryId")
    Optional<Long> getAccountIdByEntryId(Long entryId);
}
