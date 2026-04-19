package com.globalpayments.api.gateways;

public interface IPayFacProvider {
    Object processPayFac(Object builder) throws Exception;
    Object processBoardingUser(Object builder) throws Exception;
}
