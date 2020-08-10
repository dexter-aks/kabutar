package com.dexter.kabutar.exception;

public class InvalidRequestException extends Exception{
    private static final long serialVerUID = 1L;

    private final String message;

    public InvalidRequestException(){
        message = "You cannot send message to yourself";
    }
}
