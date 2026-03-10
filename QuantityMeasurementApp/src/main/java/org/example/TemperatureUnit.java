package org.example;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(
            c -> c,
            c -> c),

    FAHRENHEIT(
            f -> (f - 32) * 5 / 9,
            c -> (c * 9 / 5) + 32);

    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;

    private final SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(Function<Double, Double> toBase,
            Function<Double, Double> fromBase) {

        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromBase.apply(baseValue);
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support arithmetic operations");
    }
}