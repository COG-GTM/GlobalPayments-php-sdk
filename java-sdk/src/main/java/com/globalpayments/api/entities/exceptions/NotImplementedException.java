package com.globalpayments.api.entities.exceptions;

public class NotImplementedException extends ApiException {

    public NotImplementedException() {
        super("Not implemented");
    }

    public NotImplementedException(String message) {
        super(message);
    }
}
