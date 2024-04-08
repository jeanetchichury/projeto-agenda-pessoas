package com.agenda.pessoas.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException() {

    }
}
