package org.serratec.exception;

public class AgendamentoException extends RuntimeException {
    
    public AgendamentoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgendamentoException(String message) {
        super(message);
    }

}
