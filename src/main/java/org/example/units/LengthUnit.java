package org.example.units;

public enum LengthUnit implements IMeasurable {

    FEET(12),
    INCH(1),
    YARD(36);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double toBaseUnit(double value) {
        return value * factor;
    }

    @Override
    public double fromBaseUnit(double value) {
        return value / factor;
    }
}