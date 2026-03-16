package org.example.units;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS,
    FAHRENHEIT;

    @Override
    public double toBaseUnit(double value) {

        if(this == CELSIUS)
            return value;

        return (value - 32) * 5 / 9;
    }

    @Override
    public double fromBaseUnit(double value) {

        if(this == CELSIUS)
            return value;

        return (value * 9 / 5) + 32;
    }

    @Override
    public boolean supportsArithmetic() {
        return false;
    }
}