package com.globalpayments.api.gateways.interfaces;

public interface IBillingProvider {
    boolean isBillDataHosted();
    Object processBillingRequest(Object builder) throws Exception;
}
