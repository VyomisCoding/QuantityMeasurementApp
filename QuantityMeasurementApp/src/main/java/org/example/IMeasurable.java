package org.example;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

public interface IMeasurable {

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();

    // default arithmetic support
    default boolean supportsArithmetic() {
        SupportsArithmetic supportsArithmetic = () -> true;
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) {
        // default: allow
    }
}