package com.globalpayments.api.entities;

import com.globalpayments.api.entities.exceptions.ArgumentException;

/**
 * Base utility class for enum validation, mirroring the PHP SDK's Enum class.
 * Provides reflection-based lookup for Java enums with backing values.
 */
public final class Enum {

    private Enum() {
    }

    /**
     * Validates that the given value exists in the specified enum type.
     *
     * @param enumClass the enum class to validate against
     * @param value     the value to look up
     * @param <E>       the enum type
     * @return the matching enum constant
     * @throws ArgumentException if the value is not found
     */
    @SuppressWarnings("unchecked")
    public static <E extends java.lang.Enum<E>> E validate(Class<E> enumClass, Object value) throws ArgumentException {
        for (E constant : enumClass.getEnumConstants()) {
            if (constant.name().equals(value) || constant.toString().equals(String.valueOf(value))) {
                return constant;
            }
        }
        throw new ArgumentException(
                String.format("Invalid value `%s` on enum `%s`", value, enumClass.getSimpleName())
        );
    }

    /**
     * Gets the name of the enum constant matching the given value.
     *
     * @param enumClass the enum class to search
     * @param value     the value to look up
     * @param <E>       the enum type
     * @return the name of the matching constant, or null if not found
     */
    public static <E extends java.lang.Enum<E>> String getKey(Class<E> enumClass, Object value) {
        for (E constant : enumClass.getEnumConstants()) {
            if (constant.name().equals(value) || constant.toString().equals(String.valueOf(value))) {
                return constant.name();
            }
        }
        return null;
    }
}
