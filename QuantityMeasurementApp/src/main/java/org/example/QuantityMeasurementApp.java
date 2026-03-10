package org.example;

public class QuantityMeasurementApp{

        // --Subtraction Demo--
    public static <U extends IMeasurable> void demonstrateSubtraction(
            Quantity<U> q1,
            Quantity<U> q2) {

        Quantity<U> result = q1.subtract(q2);

        System.out.println("Input: " + q1 + " - " + q2 +
                " → Output: " + result);
    }

    public static <U extends IMeasurable> void demonstrateSubtractionTarget(
            Quantity<U> q1,
            Quantity<U> q2,
            U targetUnit) {

        Quantity<U> result = q1.subtract(q2, targetUnit);

        System.out.println("Input: " + q1 + " - " + q2 +
                " (" + targetUnit + ") → Output: " + result);
    }

    // --Division Demo--
    public static <U extends IMeasurable> void demonstrateDivision(
            Quantity<U> q1,
            Quantity<U> q2) {

        double result = q1.divide(q2);

        System.out.println("Input: " + q1 + " ÷ " + q2 +
                " → Output: " + result);
    }

    // --MAIN METHOD--
    public static void main(String[] args){

        System.out.println("Subtraction with Implicit Target Unit:");

        demonstrateSubtraction(
                new Quantity<>(10.0, LengthUnit.FEET),
                new Quantity<>(6.0, LengthUnit.INCHES)
        );

        demonstrateSubtraction(
                new Quantity<>(10.0, WeightUnit.KILOGRAM),
                new Quantity<>(5000.0, WeightUnit.GRAM)
        );

        demonstrateSubtraction(
                new Quantity<>(5.0, VolumeUnit.LITRE),
                new Quantity<>(500.0, VolumeUnit.MILLILITRE)
        );


        System.out.println("\nSubtraction with Explicit Target Unit:");

        demonstrateSubtractionTarget(
                new Quantity<>(10.0, LengthUnit.FEET),
                new Quantity<>(6.0, LengthUnit.INCHES),
                LengthUnit.INCHES
        );

        demonstrateSubtractionTarget(
                new Quantity<>(10.0, WeightUnit.KILOGRAM),
                new Quantity<>(5000.0, WeightUnit.GRAM),
                WeightUnit.GRAM
        );

        demonstrateSubtractionTarget(
                new Quantity<>(5.0, VolumeUnit.LITRE),
                new Quantity<>(2.0, VolumeUnit.LITRE),
                VolumeUnit.MILLILITRE
        );


        System.out.println("\nSubtraction Resulting in Negative Values:");

        demonstrateSubtraction(
                new Quantity<>(5.0, LengthUnit.FEET),
                new Quantity<>(10.0, LengthUnit.FEET)
        );

        demonstrateSubtraction(
                new Quantity<>(2.0, WeightUnit.KILOGRAM),
                new Quantity<>(5.0, WeightUnit.KILOGRAM)
        );


        System.out.println("\nSubtraction Resulting in Zero:");

        demonstrateSubtraction(
                new Quantity<>(10.0, LengthUnit.FEET),
                new Quantity<>(120.0, LengthUnit.INCHES)
        );

        demonstrateSubtraction(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
        );


        System.out.println("\nDivision Operations:");

        demonstrateDivision(
                new Quantity<>(10.0, LengthUnit.FEET),
                new Quantity<>(2.0, LengthUnit.FEET)
        );

        demonstrateDivision(
                new Quantity<>(10.0, LengthUnit.FEET),
                new Quantity<>(5.0, LengthUnit.FEET)
        );

        demonstrateDivision(
                new Quantity<>(24.0, LengthUnit.INCHES),
                new Quantity<>(2.0, LengthUnit.FEET)
        );

        demonstrateDivision(
                new Quantity<>(10.0, WeightUnit.KILOGRAM),
                new Quantity<>(5.0, WeightUnit.KILOGRAM)
        );

        demonstrateDivision(
                new Quantity<>(5.0, VolumeUnit.LITRE),
                new Quantity<>(10.0, VolumeUnit.LITRE)
        );


        System.out.println("\nDivision with Different Units:");

        demonstrateDivision(
                new Quantity<>(12.0, LengthUnit.INCHES),
                new Quantity<>(1.0, LengthUnit.FEET)
        );

        demonstrateDivision(
                new Quantity<>(2000.0, WeightUnit.GRAM),
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
        );

        demonstrateDivision(
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                new Quantity<>(1.0, VolumeUnit.LITRE)
        );
    }
}