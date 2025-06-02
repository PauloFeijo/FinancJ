package io.github.paulofeijo.financj.services;

import io.github.paulofeijo.financj.enums.Type;
import io.github.paulofeijo.financj.repositories.AccountRepository;
import io.github.paulofeijo.financj.repositories.EntryRepository;
import io.github.paulofeijo.financj.repositories.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class BalanceService {
    private final EntryRepository entryRepository;
    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;

    public void process(Long accountId) {
        var account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        var revenues = entryRepository.getSumAmountByAccountAndType(accountId, Type.R).orElse(0.0);
        var expenses = entryRepository.getSumAmountByAccountAndType(accountId, Type.E).orElse(0.0);
        var credits = transferRepository.getSumAmountByAccountCredit(accountId).orElse(0.0);
        var debits = transferRepository.getSumAmountByAccountDebit(accountId).orElse(0.0);

        var balance = revenues - expenses + credits - debits;

        account.setBalance(balance);
        accountRepository.save(account);
    }

    public void process(List<Long> accountIds) {
        var setAccounts = new HashSet<>(accountIds);
        setAccounts.remove(null);

        for (Long id : setAccounts) {
            process(id);
        }
    }
}
