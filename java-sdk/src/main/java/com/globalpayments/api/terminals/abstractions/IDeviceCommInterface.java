package com.globalpayments.api.terminals.abstractions;

public interface IDeviceCommInterface {
    void connect() throws Exception;
    void disconnect() throws Exception;
    byte[] send(Object message, String requestType) throws Exception;
    Object parseResponse(Object gatewayResponse) throws Exception;
}
