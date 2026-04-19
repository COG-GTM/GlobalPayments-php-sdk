package com.globalpayments.api.entities.exceptions;

public class ApiException extends Exception {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
