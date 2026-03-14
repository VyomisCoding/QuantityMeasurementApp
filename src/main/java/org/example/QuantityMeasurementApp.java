package org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("--VOLUME MEASUREMENT DEMO--\n");

        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> v4 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("--Equality Comparisons--");

        System.out.println("1 L == 1 L → " + new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, VolumeUnit.LITRE)));

        System.out.println("1 L == 1000 mL → " + new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));

        System.out.println("1 gallon == 1 gallon → " + new Quantity<>(1.0, VolumeUnit.GALLON).equals(new Quantity<>(1.0, VolumeUnit.GALLON)));

        System.out.println("1 L == ~0.264172 gallon → " + new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(0.264172, VolumeUnit.GALLON)));

        System.out.println("500 mL == 0.5 L → " + new Quantity<>(500.0, VolumeUnit.MILLILITRE).equals(new Quantity<>(0.5, VolumeUnit.LITRE)));

        System.out.println("3.78541 L == 1 gallon → " + new Quantity<>(3.78541, VolumeUnit.LITRE).equals(new Quantity<>(1.0, VolumeUnit.GALLON)));

        System.out.println();

        System.out.println("--Unit Conversions--");

        System.out.println("1 L → mL = " + new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE));

        System.out.println("2 gallon → L = " + new Quantity<>(2.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE));

        System.out.println("500 mL → gallon = " + new Quantity<>(500.0, VolumeUnit.MILLILITRE).convertTo(VolumeUnit.GALLON));

        System.out.println("0 L → mL = " + new Quantity<>(0.0, VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE));

        System.out.println("1 L → L = " + new Quantity<>(1.0, VolumeUnit.LITRE).convertTo(VolumeUnit.LITRE));

        System.out.println();

        System.out.println("-Addition (Implicit Target Unit)-");

        System.out.println("1 L + 2 L = " + new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(2.0, VolumeUnit.LITRE)));

        System.out.println("1 L + 1000 mL = " + new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));

        System.out.println("500 mL + 0.5 L = " + new Quantity<>(500.0, VolumeUnit.MILLILITRE).add(new Quantity<>(0.5, VolumeUnit.LITRE)));

        System.out.println("2 gallon + 3.78541 L = " + new Quantity<>(2.0, VolumeUnit.GALLON).add(new Quantity<>(3.78541, VolumeUnit.LITRE)));

        System.out.println();

        System.out.println("-Addition (Explicit Target Unit)-");

        System.out.println("1 L + 1000 mL → mL = " + new Quantity<>(1.0, VolumeUnit.LITRE).add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),VolumeUnit.MILLILITRE));

        System.out.println("1 gallon + 3.78541 L → gallon = " + new Quantity<>(1.0, VolumeUnit.GALLON).add(new Quantity<>(3.78541, VolumeUnit.LITRE),VolumeUnit.GALLON));

        System.out.println("500 mL + 1 L → gallon = " + new Quantity<>(500.0, VolumeUnit.MILLILITRE).add(new Quantity<>(1.0, VolumeUnit.LITRE),VolumeUnit.GALLON));

        System.out.println("2 L + 4 gallon → L = " + new Quantity<>(2.0, VolumeUnit.LITRE).add(new Quantity<>(4.0, VolumeUnit.GALLON),VolumeUnit.LITRE));

        System.out.println();

        System.out.println("----- Category Incompatibility -----");

        System.out.println("1 L == 1 FOOT → " + new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, LengthUnit.FEET)));

        System.out.println("1 L == 1 KG → " + new Quantity<>(1.0, VolumeUnit.LITRE).equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));

    }
}