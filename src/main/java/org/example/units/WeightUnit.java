package org.example.units;

public enum WeightUnit implements IMeasurable {

    GRAM(1),
    KILOGRAM(1000),
    TONNE(1000000);

    private final double factor;

    WeightUnit(double factor) {
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