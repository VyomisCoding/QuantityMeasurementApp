package org.example;

public enum WeightUnit {

    KILOGRAM(1.0),        // base unit
    GRAM(0.001),          // 1 g = 0.001 kg
    POUND(0.453592);      // 1 lb ≈ 0.453592 kg

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert to base unit (KILOGRAM)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert from base unit (KILOGRAM)
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}