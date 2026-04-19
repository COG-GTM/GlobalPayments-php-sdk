package com.globalpayments.api.gateways.interfaces;

public interface IDeviceCloudService {
    Object processPassThrough(String jsonRequest) throws Exception;
}
