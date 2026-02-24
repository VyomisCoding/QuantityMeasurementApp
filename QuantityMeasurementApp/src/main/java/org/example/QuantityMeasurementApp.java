package org.example;

public class QuantityMeasurementApp {
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static void demonstrateFeetEquality() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(1.0, Length.LengthUnit.FEET);

        System.out.println("Feet equality: " + feet1.equals(feet2));
    }

    public static void demonstrateInchesEquality() {
        Length inch1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length inch2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Inches equality: " + inch1.equals(inch2));
    }

    public static void demonstrateFeetInchesComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Feet and Inches comparison: " + feet.equals(inches));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}
