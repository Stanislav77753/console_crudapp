package main.java.com.stanislav.crudapp.exceptions;

public class AccountAlreadyExistException extends Exception {
    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
