package com.routes.requestInput.exception;

/**
 * When something during Validation went wrong
 */
public class RequestValidationException extends Exception {
    public RequestValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestValidationException(String message) {
        super(message);
    }

    public RequestValidationException() {
    }
}
