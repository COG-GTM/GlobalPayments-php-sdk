package com.globalpayments.api.paymentmethods.interfaces;

public interface IBalanceable {
    Object balanceInquiry(String inquiry) throws Exception;
}
