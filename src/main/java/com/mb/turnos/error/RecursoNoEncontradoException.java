package com.mb.turnos.error;

public class RecursoNoEncontradoException extends RuntimeException{
    public RecursoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
