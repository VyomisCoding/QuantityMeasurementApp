package org.example;

public class Length {

    private double value;
    private LengthUnit unit;

    public enum LengthUnit{     // enum
        FEET(12.0),             // 1 foot = 12 inches
        INCHES(1.0),            // base unit
        YARDS(36.0),            // 1 yard = 3 feet = 36 inches
        CENTIMETERS(0.393701);  // 1 cm = 0.393701 inches

        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor(){
            return conversionFactor;
        }
    }

    
    public Length(double value, LengthUnit unit){   // Constructor
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit(){    // Convert any unit to inches (base unit)
        return this.value * this.unit.getConversionFactor();
    }

    
    public boolean compare(Length thatLength){   // Compare two Length objects
        if (thatLength == null)
            return false;

        return Double.compare(
                this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()
        ) == 0;
    }

    // equals() override
    @Override
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
}