package org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("UC5 - Conversion");

        System.out.println(
                Length.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES)
        );

        System.out.println(
                Length.convert(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET)
        );

        Length length = new Length(3, Length.LengthUnit.FEET);

        Length converted = length.convertTo(Length.LengthUnit.INCHES);

        System.out.println(converted);

        System.out.println("UC6 - Addition");

        Length a = new Length(1, Length.LengthUnit.FEET);
        Length b = new Length(12, Length.LengthUnit.INCHES);

        Length result = a.add(b);

        System.out.println(result); // 2 FEET
    }
}