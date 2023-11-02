package com.example.tech.interview.controller;

import com.example.tech.interview.dao.entity.AccountEntity;
import com.example.tech.interview.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/accounts")
public class AccountsController {

    private final AccountService accountService;

    @PostMapping("/createAccount")
    public AccountEntity createAccount(@RequestBody AccountEntity entity) throws Exception {
        return accountService.createAccount(entity);
    }

    @GetMapping("/read")
    public AccountEntity read(@RequestParam("accountId") Long accountId) {
        return accountService.ReadAccount(accountId);
    }

    @GetMapping("/readAll")
    public List<AccountEntity> readAll() {
        return accountService.readAllAccounts();
    }

    @PutMapping("/changeAccount")
    public AccountEntity updateAccount(@RequestBody AccountEntity accountEntity) throws Exception {
        return accountService.updateAccount(accountEntity);
    }

    @GetMapping("/{accountId}")
    public void deleteAccount(Long accountId) throws Exception {
        accountService.deleteAccount(accountId);
    }


}
