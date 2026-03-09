package org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);

        System.out.println(a.convertTo(LengthUnit.INCHES));

        System.out.println(a.add(b, LengthUnit.FEET));

        System.out.println(
                new Length(36.0, LengthUnit.INCHES)
                        .equals(new Length(1.0, LengthUnit.YARDS)));

        System.out.println(
                new Length(1.0, LengthUnit.YARDS)
                        .add(new Length(3.0, LengthUnit.FEET), LengthUnit.YARDS));
    }
}