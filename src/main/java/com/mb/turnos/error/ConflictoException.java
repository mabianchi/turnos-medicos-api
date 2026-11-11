package com.mb.turnos.error;

public class ConflictoException extends RuntimeException{
    public ConflictoException(String mensaje){
        super(mensaje);
    }
}
