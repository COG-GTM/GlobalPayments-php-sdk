package com.globalpayments.api.entities.enums;

public enum CustomerAuthenticationMethod {
    NOT_AUTHENTICATED("NOT_AUTHENTICATED"),
    MERCHANT_SYSTEM("MERCHANT_SYSTEM_AUTHENTICATION"),
    FEDERATED_ID("FEDERATED_ID_AUTHENTICATION"),
    ISSUER_CREDENTIAL("ISSUER_CREDENTIAL_AUTHENTICATION"),
    THIRD_PARTY("THIRD_PARTY_AUTHENTICATION"),
    FIDO("FIDO_AUTHENTICATION");

    private final String value;

    CustomerAuthenticationMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CustomerAuthenticationMethod fromValue(String value) {
        for (CustomerAuthenticationMethod item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
