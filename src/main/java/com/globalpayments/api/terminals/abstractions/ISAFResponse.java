package com.globalpayments.api.terminals.abstractions;

public interface ISAFResponse extends IDeviceResponse {
    Object getApproved();
    Object getPending();
    Object getDeclined();
}
