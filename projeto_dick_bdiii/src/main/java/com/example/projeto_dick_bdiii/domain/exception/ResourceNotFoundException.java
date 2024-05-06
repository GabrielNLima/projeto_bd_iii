package com.example.projeto_dick_bdiii.domain.exception;

public class ResourceNotFoundException extends RunTimeException {

    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }   
}
