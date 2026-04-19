package com.globalpayments.api.entities.enums;

import java.util.HashMap;
import java.util.Map;

public class IsoCountries {

    private static final Map<String, String> COUNTRY_CODE_MAP = new HashMap<>();
    private static final Map<String, String> COUNTRY_NUMERIC_MAP = new HashMap<>();

    private IsoCountries() {
    }

    public static String getCountryCodeByCountry(String country) {
        return COUNTRY_CODE_MAP.get(country);
    }

    public static String getNumericCodeByCountry(String country) {
        return COUNTRY_NUMERIC_MAP.get(country);
    }
}
