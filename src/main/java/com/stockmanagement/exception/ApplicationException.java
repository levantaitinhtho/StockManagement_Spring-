package com.stockmanagement.exception;

public class ApplicationException extends RuntimeException{

    private static final long serialVersionUID = 5776681206288518465L;

    public ApplicationException(String message)
    {
        super(message);
    }

}
