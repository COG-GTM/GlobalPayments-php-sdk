package com.globalpayments.api.entities.exceptions;

public class BuilderException extends ApiException {

    public BuilderException(String message) {
        super(message);
    }

    public BuilderException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
