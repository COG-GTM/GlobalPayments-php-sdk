package com.globalpayments.api.gateways;

public interface IOpenBankingProvider {
    Object processOpenBanking(Object builder) throws Exception;
    Object manageOpenBanking(Object builder) throws Exception;
}
