package com.globalpayments.api.entities;

public interface IRequestBuilder {
    Object buildRequest(Object builder, Object config) throws Exception;
    Object buildRequestFromJson(String jsonRequest, Object config) throws Exception;
    boolean canProcess(Object builder);
}
