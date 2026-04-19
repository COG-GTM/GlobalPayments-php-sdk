package com.globalpayments.api.entities;

import com.globalpayments.api.gateways.GatewayResponse;
import java.util.Map;

public interface IRequestLogger {
    void requestSent(String verb, String endpoint, Map<String, String> headers, Map<String, String> queryStringParams, String data);
    void responseReceived(GatewayResponse response);
    void responseError(Exception e, String headers);
}
