package io.github.paulofeijo.financj.services;

import io.github.paulofeijo.financj.entities.Account;
import io.github.paulofeijo.financj.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, AccountRepository>{

    public AccountService(AccountRepository repository) {
        super(repository);
    }

    @Override
    public Account create(Account account) {
        account.setBalance(0.0);
        return super.create(account);
    }

    @Override
    public Account update(Long id, Account account) {
        var actual = repository.findById(account.getId()).orElse(null);
        account.setBalance(actual.getBalance());
        return super.update(id, account);
    }
}
