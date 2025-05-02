package com.StraCode.finance.exceptions;

public class NullDtoException extends RuntimeException{

    public NullDtoException() {
        super("Dto cant be null!");
    }

    public NullDtoException(String message) {
        super(message);
    }
}
