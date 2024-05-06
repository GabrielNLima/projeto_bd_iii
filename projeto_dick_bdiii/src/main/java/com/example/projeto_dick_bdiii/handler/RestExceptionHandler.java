package com.example.projeto_dick_bdiii.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.projeto_dick_bdiii.domain.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResposta>
    handlerResourceNotFoundException(ResourceNotFoundException ex){
        String dataHora = ConversorData
        .converterDataParaHora(new Date());
        ErroResposta erro = new ErroResposta(dataHora,
        HttpStatus.NOT_FOUND.value(), titulo:"NOT FOUND",
        ex.getMessage());
        return new ResponseEntity<>(erro,
        HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErroResposta>
    handlerBadRequestException(BadRequestException ex){
        String dataHora = ConversorData
        .converterDataParaHora(new Date());
        ErroResposta erro = new ErroResposta(dataHora,
        HttpStatus.BAD_REQUEST.value(), titulo:"BAD REQUEST",
        ex.getMessage());
        return new ResponseEntity<>(erro,
        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta>
    handlerException(Exception ex){
        String dataHora = ConversorData
        .converterDataParaHora(new Date());
        ErroResposta erro = new ErroResposta(dataHora,
        HttpStatus.INTERNAL_SERVER_ERROR.value(), titulo:"Internal Server Error",
        ex.getMessage());
        return new ResponseEntity<>(erro,
        HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
