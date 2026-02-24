package org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("UC4 ");

        // 1 Yard = 3 Feet
        System.out.println(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(3.0, Length.LengthUnit.FEET)));  // true

        // 1 Yard = 36 Inches
        System.out.println(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(36.0, Length.LengthUnit.INCHES))); // true

        // Yard to Yard
        System.out.println(new Length(2.0, Length.LengthUnit.YARDS)
                .equals(new Length(2.0, Length.LengthUnit.YARDS))); // true

        // Centimeter to Centimeter
        System.out.println(new Length(2.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(2.0, Length.LengthUnit.CENTIMETERS))); // true

        // 1 cm = 0.393701 inches
        System.out.println(new Length(1.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, Length.LengthUnit.INCHES))); // true

        // Negative case
        System.out.println(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(2.0, Length.LengthUnit.FEET))); // false
    }
}