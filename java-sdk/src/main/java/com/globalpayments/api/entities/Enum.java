package com.globalpayments.api.entities;

import com.globalpayments.api.entities.exceptions.ArgumentException;

import java.lang.reflect.Method;

/**
 * Base utility class for enum validation, mirroring the PHP SDK's Enum class.
 * Provides reflection-based lookup for Java enums with backing values.
 */
public final class Enum {

    private Enum() {
    }

    /**
     * Validates that the given value exists in the specified enum type.
     * Matches by enum constant name or by the backing value returned from getValue().
     *
     * @param enumClass the enum class to validate against
     * @param value     the value to look up (can be a name string or a backing value)
     * @param <E>       the enum type
     * @return the matching enum constant
     * @throws ArgumentException if the value is not found
     */
    public static <E extends java.lang.Enum<E>> E validate(Class<E> enumClass, Object value) throws ArgumentException {
        String valueStr = String.valueOf(value);

        for (E constant : enumClass.getEnumConstants()) {
            if (constant.name().equals(value) || constant.name().equals(valueStr)) {
                return constant;
            }
        }

        Method getValueMethod = findGetValueMethod(enumClass);
        if (getValueMethod != null) {
            for (E constant : enumClass.getEnumConstants()) {
                try {
                    Object backingValue = getValueMethod.invoke(constant);
                    if ((value != null && value.equals(backingValue)) || valueStr.equals(String.valueOf(backingValue))) {
                        return constant;
                    }
                } catch (ReflectiveOperationException ignored) {
                }
            }
        }

        throw new ArgumentException(
                String.format("Invalid value `%s` on enum `%s`", value, enumClass.getSimpleName())
        );
    }

    /**
     * Gets the name of the enum constant matching the given value.
     * Matches by enum constant name or by the backing value returned from getValue().
     *
     * @param enumClass the enum class to search
     * @param value     the value to look up (can be a name string or a backing value)
     * @param <E>       the enum type
     * @return the name of the matching constant, or null if not found
     */
    public static <E extends java.lang.Enum<E>> String getKey(Class<E> enumClass, Object value) {
        String valueStr = String.valueOf(value);

        for (E constant : enumClass.getEnumConstants()) {
            if (constant.name().equals(value) || constant.name().equals(valueStr)) {
                return constant.name();
            }
        }

        Method getValueMethod = findGetValueMethod(enumClass);
        if (getValueMethod != null) {
            for (E constant : enumClass.getEnumConstants()) {
                try {
                    Object backingValue = getValueMethod.invoke(constant);
                    if ((value != null && value.equals(backingValue)) || valueStr.equals(String.valueOf(backingValue))) {
                        return constant.name();
                    }
                } catch (ReflectiveOperationException ignored) {
                }
            }
        }

        return null;
    }

    private static <E extends java.lang.Enum<E>> Method findGetValueMethod(Class<E> enumClass) {
        try {
            return enumClass.getMethod("getValue");
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
