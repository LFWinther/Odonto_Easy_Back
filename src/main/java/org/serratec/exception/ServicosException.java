package org.serratec.exception;

public class ServicosException extends RuntimeException {
    
    public ServicosException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicosException(String message) {
        super(message);
    }

}
