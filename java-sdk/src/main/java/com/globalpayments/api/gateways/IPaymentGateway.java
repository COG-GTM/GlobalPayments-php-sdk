package com.globalpayments.api.gateways;

public interface IPaymentGateway {
    Object processAuthorization(Object builder) throws Exception;
    Object manageTransaction(Object builder) throws Exception;
    Object processReport(Object builder) throws Exception;
    Object serializeRequest(Object builder) throws Exception;
    boolean supportsOpenBanking();
}
