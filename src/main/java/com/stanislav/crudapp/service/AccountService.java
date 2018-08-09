package main.java.com.stanislav.crudapp.service;

import main.java.com.stanislav.crudapp.exceptions.AccountAlreadyExistException;
import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Account;
import main.java.com.stanislav.crudapp.repository.io.JavaIOAccountRepositoryImpl;

import java.util.List;

public class AccountService {
    JavaIOAccountRepositoryImpl javaIOAccountRepository = new JavaIOAccountRepositoryImpl();

    public void addAccount(Account account) throws AccountAlreadyExistException {
        Long id = 0L;
        try {
            List<String> allAccounts = javaIOAccountRepository.getAccountsInListString();
            for(String stringAccount: allAccounts){
                String[] strArray = stringAccount.trim().replace(" - ", "-").split("-");
                if(strArray[1].equals(account.getDeveloperData())){
                    throw new AccountAlreadyExistException("This account already exist");
                }
                id = new Long(strArray[0]) + 1L;
            }
        } catch (EmptyFileException e) {
            id = 1L;
        }
        account.setId(id);
        javaIOAccountRepository.save(account);
    }

    public void getAllAccounts() throws EmptyFileException {
        List<String> allAccountsFromFile = javaIOAccountRepository.getAccountsInListString();
        for(String stringAccount: allAccountsFromFile){
            System.out.println(stringAccount);
        }
    }
    public void deleteAccount(Account account) throws EmptyFileException {
        javaIOAccountRepository.delete(account);
    }

    public void updateAccountById(Account account) throws EmptyFileException {
        javaIOAccountRepository.update(account);
    }

    public Account getAccountById(Long id) throws EmptyFileException {
        return javaIOAccountRepository.getById(id);
    }
}
