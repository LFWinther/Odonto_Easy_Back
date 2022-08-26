package org.serratec.exception;

public class UsuarioException extends RuntimeException{

    public UsuarioException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsuarioException(String message) {
        super(message);
    }

}
