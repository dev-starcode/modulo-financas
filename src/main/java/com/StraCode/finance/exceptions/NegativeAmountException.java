package com.StraCode.finance.exceptions;

public class NegativeAmountException extends RuntimeException{

    public NegativeAmountException() {
        super("The amount cant be a negative value!");
    }

    public NegativeAmountException(String message) {
        super(message);
    }
}
