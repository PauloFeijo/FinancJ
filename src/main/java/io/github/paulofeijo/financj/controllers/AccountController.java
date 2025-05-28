package io.github.paulofeijo.financj.controllers;

import io.github.paulofeijo.financj.entities.Account;
import io.github.paulofeijo.financj.services.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController extends BaseController<Account, AccountService>{

    public AccountController(AccountService service) {
        super(service);
    }
}
