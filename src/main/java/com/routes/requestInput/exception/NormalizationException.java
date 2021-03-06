package com.routes.requestInput.exception;

/**
 * Created by rudolfplettenberg on 20.06.2016.
 */
public class NormalizationException extends Exception {

    public NormalizationException() {
    }

    public NormalizationException(String message) {
        super(message);
    }

    public NormalizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NormalizationException(Throwable cause) {
        super(cause);
    }

    public NormalizationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
