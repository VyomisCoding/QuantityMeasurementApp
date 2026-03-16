package org.example.model;

import org.example.units.IMeasurable;

public class Quantity<U extends IMeasurable> {

    private double value;
    private U unit;

    public Quantity(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }
}