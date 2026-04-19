package com.globalpayments.api.entities.exceptions;

public class MessageException extends ApiException {

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
