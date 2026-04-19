package com.globalpayments.api.terminals.abstractions;

public interface ITerminalConfiguration {
    Object getConnectionMode();
    void setConnectionMode(Object connectionMode);
    Object getDeviceType();
    void setDeviceType(Object deviceType);
    IRequestIdProvider getRequestIdProvider();
    void setRequestIdProvider(IRequestIdProvider requestIdProvider);
    String getIpAddress();
    void setIpAddress(String ipAddress);
    String getPort();
    void setPort(String port);
    Object getBaudRate();
    void setBaudRate(Object baudRate);
    Object getParity();
    void setParity(Object parity);
    Object getStopBits();
    void setStopBits(Object stopBits);
    Object getDataBits();
    void setDataBits(Object dataBits);
    int getTimeout();
    Object getGatewayConfig();
    void setGatewayConfig(Object gatewayConfig);
    String getConfigName();
    void setConfigName(String configName);
}
