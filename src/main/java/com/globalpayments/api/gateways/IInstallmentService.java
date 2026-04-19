package com.globalpayments.api.gateways;

public interface IInstallmentService {
    Object processInstallment(Object builder) throws Exception;
}
