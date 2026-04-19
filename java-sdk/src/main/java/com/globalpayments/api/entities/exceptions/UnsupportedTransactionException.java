package com.globalpayments.api.entities.exceptions;

public class UnsupportedTransactionException extends ApiException {

    public UnsupportedTransactionException() {
        super("Unsupported transaction");
    }

    public UnsupportedTransactionException(String message) {
        super(message);
    }
}
