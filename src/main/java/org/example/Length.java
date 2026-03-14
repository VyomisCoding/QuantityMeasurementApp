package org.example;

public class Length{
    private final double value;
    private final LengthUnit unit;
    public Length(double value, LengthUnit unit){
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit(){
        return unit.convertToBaseUnit(value);
    }

    public Length convertTo(LengthUnit targetUnit){    // UC5 Conversion
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new Length(converted, targetUnit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target){    // UC5 Static conversion API
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        double baseValue = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(baseValue);
    }

    public Length add(Length other){     // UC6 Addition
        if (other == null)
            throw new IllegalArgumentException("Length cannot be null");

        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();
        double sumBase = base1 + base2;
        double result = unit.convertFromBaseUnit(sumBase);
        return new Length(result, unit);
    }

    public Length add(Length other, LengthUnit targetUnit){      // UC7 Addition with Target Unit
        if (other == null)
            throw new IllegalArgumentException("Length cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = this.convertToBaseUnit();
        double base2 = other.convertToBaseUnit();
        double sumBase = base1 + base2;
        double result = targetUnit.convertFromBaseUnit(sumBase);
        return new Length(result, targetUnit);
    }

    public boolean compare(Length thatLength){
        if (thatLength == null)
            return false;

        return Double.compare(
                this.convertToBaseUnit(),
                thatLength.convertToBaseUnit()
        ) == 0;
    }

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

    @Override
    public String toString(){
        return value + " " + unit;
    }
}