package com.globalpayments.api.gateways.interfaces;

public interface IBillPayResponse {
    Object map();
    IBillPayResponse withResponseTagName(String tagName);
    IBillPayResponse withResponse(String response);
}
