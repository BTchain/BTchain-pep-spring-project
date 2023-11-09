package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository accRep;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accRep = accountRepository;
    }

    public Account addAccount(Account account) {
        Account accountR = accRep.save(account);
        return accountR;
    }

    public Account getAccountByName(String username) {
        Account accRet1 = accRep.findAccountByUsername(username);
        return accRet1;
    }

    public Account getById(Integer posted_by) {
        Optional<Account> account = accRep.findById(posted_by);
        if (account.isPresent()) {
            return account.orElseThrow();
        } else {
            return null;
        }

    }

}
