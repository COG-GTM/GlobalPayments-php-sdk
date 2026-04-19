package com.globalpayments.api.entities.exceptions;

public class GatewayException extends ApiException {

    private String responseCode;
    private String responseMessage;

    public GatewayException(String message) {
        super(message);
    }

    public GatewayException(String message, Throwable innerException) {
        super(message, innerException);
    }

    public GatewayException(String message, String responseCode, String responseMessage) {
        super(message);
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public GatewayException(String message, String responseCode, String responseMessage, Throwable innerException) {
        super(message, innerException);
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
