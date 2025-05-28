package io.github.paulofeijo.financj.services;

import io.github.paulofeijo.financj.entities.Account;
import io.github.paulofeijo.financj.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, AccountRepository>{

    public AccountService(AccountRepository repository) {
        super(repository);
    }
}
