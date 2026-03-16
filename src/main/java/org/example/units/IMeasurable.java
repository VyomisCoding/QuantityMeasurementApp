package org.example.units;

public interface IMeasurable {

    double toBaseUnit(double value);

    double fromBaseUnit(double value);

    default boolean supportsArithmetic() {
        return true;
    }

    default void validateOperationSupport(String operation) {
        if(!supportsArithmetic()) {
            throw new UnsupportedOperationException(
                    operation + " not supported for this measurement type"
            );
        }
    }
}