package com.StraCode.finance.exceptions;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException() {
        super("The account was not found");
    }

    public AccountNotFoundException(String message) {
        super(message);
    }
}
