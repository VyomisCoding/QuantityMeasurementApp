package org.example;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 0.0001;

    public Quantity(double value, U unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    private double round(double value) {
        return Math.round(value * 100000.0) / 100000.0;
    }

    // Conversion
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);

        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(round(converted), targetUnit);
    }

    // Addition (UC6 style)
    public Quantity<U> add(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();

        double sum = base1 + base2;

        double result = unit.convertFromBaseUnit(sum);

        return new Quantity<>(round(result), unit);
    }

    // Addition with target unit (UC7 style)
    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new Quantity<>(round(result), targetUnit);
    }

    // Equality
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Prevent cross category comparison
        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        // Normalize values to avoid floating precision issues
        base1 = Math.round(base1 * 1000000.0) / 1000000.0;
        base2 = Math.round(base2 * 1000000.0) / 1000000.0;

        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        base = Math.round(base * 1000000.0) / 1000000.0;

        return Double.hashCode(base);
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}