package com.globalpayments.api.gateways;

import com.globalpayments.api.entities.enums.Secure3dVersion;

public interface ISecure3dProvider {
    Secure3dVersion getVersion();
    Object processSecure3d(Object builder) throws Exception;
}
