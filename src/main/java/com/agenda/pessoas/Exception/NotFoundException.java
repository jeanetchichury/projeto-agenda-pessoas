package com.agenda.pessoas.Exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException() {

    }
}
