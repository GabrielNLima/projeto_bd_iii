package com.example.projeto_dick_bdiii.domain.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String mensagem){
        super(mensagem);
    }
    
}
