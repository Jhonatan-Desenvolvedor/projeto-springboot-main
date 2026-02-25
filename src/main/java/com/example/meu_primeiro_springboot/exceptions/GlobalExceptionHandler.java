package com.example.meu_primeiro_springboot.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manipulador global de exceções para a aplicação Spring Boot.
 * Intercepta exceções lançadas pelos controladores e serviços, retornando respostas padronizadas em JSON.
 * Trata RecursoNaoEncontradoException e exceções genéricas, não se comunica diretamente com outras classes,
 * mas é invocado automaticamente pelo framework quando exceções ocorrem.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Trata exceções do tipo RecursoNaoEncontradoException.
     * Retorna uma resposta JSON com status 404 e detalhes da exceção.
     */
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Recurso nao encontrado");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
    }
    /**
     * Trata exceções genéricas (Exception).
     * Retorna uma resposta JSON com status 500 e detalhes da exceção.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex){
        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Erro Interno do Servidor");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
