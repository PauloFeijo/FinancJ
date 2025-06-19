package io.github.paulofeijo.financj.controllers;

import io.github.paulofeijo.financj.controllers.dtos.InputAccountDto;
import io.github.paulofeijo.financj.controllers.dtos.OutputAccountDto;
import io.github.paulofeijo.financj.controllers.mappers.AccountMapper;
import io.github.paulofeijo.financj.entities.Account;
import io.github.paulofeijo.financj.services.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController extends BaseController<Account, AccountService, InputAccountDto, OutputAccountDto, AccountMapper> {

    public AccountController(AccountService service, AccountMapper mapper) {
        super(service, mapper);
    }
}
