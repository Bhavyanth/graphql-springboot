package com.phoenix.graphqlapp.exception;

public class LocoException extends RuntimeException{

    private String message;

    private Exception exception;

    public LocoException(String message){
        this.message = message;
    }

    public LocoException(String message, Exception exception){
        this.message = message;
        this.exception = exception;
    }
}
