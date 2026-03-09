package org.example;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 0.0001;

    public QuantityWeight(double value, WeightUnit unit) {

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

    // Rounding helper
    private double round(double value) {
        return Math.round(value * 100000.0) / 100000.0;
    }

    // Conversion
    public QuantityWeight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.convertToBaseUnit(value);

        double converted = targetUnit.convertFromBaseUnit(base);

        return new QuantityWeight(round(converted), targetUnit);
    }

    // Addition (result in first operand unit)
    public QuantityWeight add(QuantityWeight other) {

        if (other == null)
            throw new IllegalArgumentException("Weight cannot be null");

        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();

        double sum = base1 + base2;

        double result = unit.convertFromBaseUnit(sum);

        return new QuantityWeight(round(result), unit);
    }

    // Addition with target unit
    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Weight cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new QuantityWeight(round(result), targetUnit);
    }

    // Equality with tolerance
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityWeight other = (QuantityWeight) obj;

        double a = this.convertToBaseUnit();
        double b = other.convertToBaseUnit();

        return Math.abs(a - b) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(round(convertToBaseUnit()));
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}