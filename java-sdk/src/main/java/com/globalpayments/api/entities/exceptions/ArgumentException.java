package com.globalpayments.api.entities.exceptions;

public class ArgumentException extends ApiException {

    public ArgumentException(String message) {
        super(message);
    }

    public ArgumentException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
