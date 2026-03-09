package org.example;

public class Length{
    private final double value;
    private final LengthUnit unit;
    public enum LengthUnit{
        FEET(12.0),            // 1 foot = 12 inches
        INCHES(1.0),           // base unit
        YARDS(36.0),           // 1 yard = 36 inches
        CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

        private final double conversionFactor;
        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }
        public double getConversionFactor(){
            return conversionFactor;
        }
    }
    
    public Length(double value, LengthUnit unit){   // Constructor
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

    public static double convert(double value, LengthUnit source, LengthUnit target){    // STATIC CONVERSION API (UC5)
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        double baseValue = value * source.getConversionFactor();        // Step 1: convert source → base (inches)

        return baseValue / target.getConversionFactor();                // Step 2: base → target
    }

    public Length convertTo(LengthUnit targetUnit){      // INSTANCE METHOD CONVERSION
        double convertedValue = convert(this.value, this.unit, targetUnit);
        return new Length(convertedValue, targetUnit);
    }

    public boolean compare(Length thatLength){           // Compare two Length objects
        if (thatLength == null)
            return false;

        return Double.compare(
                this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()
        ) == 0;
    }

    @Override                     // equals() override
    public boolean equals(Object o){
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