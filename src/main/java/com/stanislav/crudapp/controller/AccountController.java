package main.java.com.stanislav.crudapp.controller;

import main.java.com.stanislav.crudapp.exceptions.AccountAlreadyExistException;
import main.java.com.stanislav.crudapp.exceptions.CloseOperationException;
import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Account;
import main.java.com.stanislav.crudapp.service.AccountService;


public class AccountController {
    AccountService accountService = new AccountService();

    public void addAccount(Account account) throws AccountAlreadyExistException {
        accountService.addAccount(account);
    }

    public void lookAllAccounts() throws EmptyFileException {
        accountService.getAllAccounts();
    }
    public void deleteAccount(Account account) throws EmptyFileException {
        accountService.deleteAccount(account);
    }
    public void updateAccount(Account account) throws EmptyFileException {
        accountService.updateAccountById(account);
    }
    public Account getAccountById(Long id) throws EmptyFileException {
        return accountService.getAccountById(id);
    }
}
