package com.globalpayments.api.entities;

public interface IWebProxy {
    String getProxy(String destination);
    boolean isBypassed(String host);
}
