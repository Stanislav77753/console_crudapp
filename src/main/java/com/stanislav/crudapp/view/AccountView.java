package main.java.com.stanislav.crudapp.view;

import main.java.com.stanislav.crudapp.controller.AccountController;
import main.java.com.stanislav.crudapp.controller.HelpController;
import main.java.com.stanislav.crudapp.exceptions.AccountAlreadyExistException;
import main.java.com.stanislav.crudapp.exceptions.CloseOperationException;
import main.java.com.stanislav.crudapp.exceptions.EmptyFileException;
import main.java.com.stanislav.crudapp.model.Account;

import java.util.Scanner;

public class AccountView {
    public void accountMenu(){
        Scanner scanner = new Scanner(System.in);
        String command = "";
        AccountController accountController = new AccountController();
        while (!command.equals("close")){
            System.out.println("You are in account menu, to get help enter command \"help\":");
            System.out.println("Enter command:");
            command = scanner.nextLine().toLowerCase().trim();
            switch(command){
                case "add new account":
                    try {
                        accountController.addAccount(new Account(null,ConsoleHelper.getStringFromConsole("new account")));
                    }catch (AccountAlreadyExistException accountAlreadyExistException) {
                        System.out.println(accountAlreadyExistException.getMessage());
                    }catch (CloseOperationException closeOperationException){
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "look all accounts" :
                    try {
                        accountController.lookAllAccounts();
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    }
                    break;
                case "delete account by id" :
                    try {
                        accountController.deleteAccount(new Account(ConsoleHelper.getIdFromConsole("account"),
                                ""));
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    }catch (CloseOperationException closeOperationException){
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "delete account by accountname" :
                    try {
                        accountController.deleteAccount(new Account(null,
                                ConsoleHelper.getStringFromConsole("account")));
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    }catch (CloseOperationException closeOperationException){
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "update account by id" :
                    try {
                        accountController.updateAccount(new Account(ConsoleHelper.getIdFromConsole("account"),
                                ConsoleHelper.getStringFromConsole("new account")));
                    } catch (EmptyFileException emptyFileException) {
                        System.out.println(emptyFileException.getMessage());
                    }catch (CloseOperationException closeOperationException) {
                        System.out.println(closeOperationException.getMessage());
                    }
                    break;
                case "help":
                    new HelpController().getHelp("ACCOUNT");
                    break;
            }
        }
    }
}
