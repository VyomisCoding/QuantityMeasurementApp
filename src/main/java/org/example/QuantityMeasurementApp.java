package org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("UC7 Addition With Target Unit");

        Length a = new Length(1, Length.LengthUnit.FEET);
        Length b = new Length(12, Length.LengthUnit.INCHES);

        System.out.println(a.add(b, Length.LengthUnit.FEET));
        System.out.println(a.add(b, Length.LengthUnit.INCHES));
        System.out.println(a.add(b, Length.LengthUnit.YARDS));
    }
}