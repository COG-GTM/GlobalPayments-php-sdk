package com.globalpayments.api.paymentmethods.interfaces;

public interface ITokenizable {
    Object tokenize() throws Exception;
    Object updateTokenExpiry() throws Exception;
    Object deleteToken() throws Exception;
    Object detokenize() throws Exception;
    Object updateToken() throws Exception;
}
