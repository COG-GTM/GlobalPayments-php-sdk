package com.globalpayments.api.entities.enums;

public enum CardHolderAuthenticationEntity {
    NOT_AUTHENTICATED("NOT_AUTHENTICATED"),
    ICC_OFFLINE_PIN("ICC_OFFLINE_PIN"),
    CARD_ACCEPTANCE_DEVICE("CARD_ACCEPTANCE_DEVICE"),
    AUTHORIZING_AGENT_ONLINE_PIN("AUTHORIZING_AGENT_ONLINE_PIN"),
    MERCHANT_CARD_ACCEPTOR_SIGNATURE("MERCHANT_CARD_ACCEPTOR_SIGNATURE"),
    OTHER("OTHER");

    private final String value;

    CardHolderAuthenticationEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CardHolderAuthenticationEntity fromValue(String value) {
        for (CardHolderAuthenticationEntity item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
