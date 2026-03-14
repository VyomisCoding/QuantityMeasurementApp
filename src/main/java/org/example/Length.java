package org.example;

public class Length{
    private final double value;
    private final LengthUnit unit;
    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0),           // base unit
        YARDS(36.0),
        CENTIMETERS(0.393701);
        private final double conversionFactor;
        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }
    }
    
    public Length(double value, LengthUnit unit){    // Constructor
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit(){    // Convert to base unit (INCHES)
        return value * unit.getConversionFactor();
    }

    public static double convert(double value, LengthUnit source, LengthUnit target){    // UC5 Static Conversion
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        double baseValue = value * source.getConversionFactor();
        return baseValue / target.getConversionFactor();
    }
    
    public Length convertTo(LengthUnit targetUnit){     // UC5 Instance Conversion
        double convertedValue = convert(this.value, this.unit, targetUnit);
        return new Length(convertedValue, targetUnit);
    }

    // UC6 Addition (result in first operand unit)
    public Length add(Length other){
        if (other == null)
            throw new IllegalArgumentException("Length cannot be null");

        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();
        double sumBase = base1 + base2;
        double resultValue = sumBase / this.unit.getConversionFactor();
        return new Length(resultValue, this.unit);
    }

    // UC7 Addition with Target Unit
    public Length add(Length other, LengthUnit targetUnit){
        if (other == null)
            throw new IllegalArgumentException("Length cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();
        double sumBase = base1 + base2;
        double resultValue = sumBase / targetUnit.getConversionFactor();
        return new Length(resultValue, targetUnit);
    }

    public boolean compare(Length thatLength){        // Comparison
        if (thatLength == null)
            return false;

        return Double.compare(
                this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()
        ) == 0;
    }
    
    @Override
    public boolean equals(Object o){     // equals override
        if (this == o) return true;

        if (!(o instanceof Length)) return false;

        Length that = (Length) o;
        return compare(that);
    }

    @Override
    public int hashCode(){
        return Double.hashCode(convertToBaseUnit());
    }

    @Override
    public String toString(){
        return value + " " + unit;
    }
}