package org.example;

public class QuantityMeasurementApp{
    public static void main(String[] args){
        // System.out.println("UC5 - Unit Conversion");
        System.out.println(
                Length.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES)
        ); // 12
        System.out.println(
                Length.convert(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET)
        ); // 9
        System.out.println(
                Length.convert(36.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS)
        ); // 1
        System.out.println(
                Length.convert(1.0, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCHES)
        ); // ~0.393701
        Length length = new Length(3, Length.LengthUnit.FEET);            // Instance method example
        Length converted = length.convertTo(Length.LengthUnit.INCHES);
        System.out.println(converted); // 36 INCHES
    }
}