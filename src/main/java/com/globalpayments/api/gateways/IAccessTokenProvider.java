package com.globalpayments.api.gateways;

import java.util.List;

public interface IAccessTokenProvider {
    Object signIn(String appId, String appKey, Integer secondsToExpire, String intervalToExpire, List<String> permissions) throws Exception;
    Object signOut() throws Exception;
}
