package com.globalpayments.api.entities.enums;

public enum ChallengeRequestIndicator {
    NO_PREFERENCE("NO_PREFERENCE"),
    NO_CHALLENGE_REQUESTED("NO_CHALLENGE_REQUESTED"),
    CHALLENGE_PREFERRED("CHALLENGE_PREFERRED"),
    CHALLENGE_MANDATED("CHALLENGE_MANDATED"),
    NO_CHALLENGE_REQUESTED_TRANSACTION_RISK_ANALYSIS_PERFORMED("NO_CHALLENGE_REQUESTED_TRANSACTION_RISK_ANALYSIS_PERFORMED"),
    NO_CHALLENGE_REQUESTED_DATA_SHARE_ONLY("NO_CHALLENGE_REQUESTED_DATA_SHARE_ONLY"),
    NO_CHALLENGE_REQUESTED_SCA_ALREADY_PERFORMED("NO_CHALLENGE_REQUESTED_SCA_ALREADY_PERFORMED"),
    NO_CHALLENGE_REQUESTED_WHITELIST("NO_CHALLENGE_REQUESTED_WHITELIST"),
    CHALLENGE_REQUESTED_PROMPT_FOR_WHITELIST("CHALLENGE_REQUESTED_PROMPT_FOR_WHITELIST");

    private final String value;

    ChallengeRequestIndicator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ChallengeRequestIndicator fromValue(String value) {
        for (ChallengeRequestIndicator item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
