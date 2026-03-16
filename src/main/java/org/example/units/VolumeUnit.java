package org.example.units;

public enum VolumeUnit implements IMeasurable {

    LITRE(1),
    MILLILITRE(0.001),
    GALLON(3.78);

    private final double factor;

    VolumeUnit(double factor) {
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