package com.globalpayments.api.entities.exceptions;

public class ConfigurationException extends ApiException {

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
