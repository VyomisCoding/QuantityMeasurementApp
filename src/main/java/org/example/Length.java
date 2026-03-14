package org.example;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),           // base unit
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Constructor
    public Length(double value, LengthUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    // Convert to base unit (INCHES)
    private double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    // UC5 Static Conversion
    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        double baseValue = value * source.getConversionFactor();
        return baseValue / target.getConversionFactor();
    }

    // UC5 Instance Conversion
    public Length convertTo(LengthUnit targetUnit) {
        double convertedValue = convert(this.value, this.unit, targetUnit);
        return new Length(convertedValue, targetUnit);
    }

    // UC6 Addition
    public Length add(Length other) {

        if (other == null)
            throw new IllegalArgumentException("Length cannot be null");

        if (!Double.isFinite(other.value))
            throw new IllegalArgumentException("Value must be finite");

        // Convert both to base unit
        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();

        // Add
        double sumBase = base1 + base2;

        // Convert back to first operand unit
        double resultValue = sumBase / this.unit.getConversionFactor();

        return new Length(resultValue, this.unit);
    }

    // Comparison
    public boolean compare(Length thatLength) {

        if (thatLength == null)
            return false;

        return Double.compare(
                this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()
        ) == 0;
    }

    // equals override
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Length)) return false;

        Length that = (Length) o;

        return compare(that);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}