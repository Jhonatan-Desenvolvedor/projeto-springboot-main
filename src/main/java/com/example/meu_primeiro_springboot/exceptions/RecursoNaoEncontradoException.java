package com.example.meu_primeiro_springboot.exceptions;

/**
 * Exceção personalizada para recursos não encontrados.
 * Lançada pelos serviços quando um recurso (como um produto ou usuário) não é localizado.
 * É capturada pelo GlobalExceptionHandler para retornar respostas HTTP adequadas.
 */
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String mensage){
        super(mensage);
    }
}
