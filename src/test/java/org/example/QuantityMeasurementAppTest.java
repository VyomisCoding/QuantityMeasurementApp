package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest{

    private static final double EPSILON = 0.001;

    // -Equality Tests ----------

    @Test
    void testTemperatureEquality_CelsiusToCelsius_SameValue() {
        assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(0.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureEquality_FahrenheitToFahrenheit_SameValue() {
        assertTrue(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)
                .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_0Celsius32Fahrenheit() {
        assertTrue(new Quantity<>(0.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_100Celsius212Fahrenheit() {
        assertTrue(new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(212.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal() {
        assertTrue(new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT)));
    }

    @Test
    void testTemperatureEquality_SymmetricProperty() {
        Quantity<TemperatureUnit> a = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> b = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    void testTemperatureEquality_ReflexiveProperty() {
        Quantity<TemperatureUnit> temp =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        assertTrue(temp.equals(temp));
    }

    @Test
    void testTemperatureDifferentValuesInequality() {
        assertFalse(new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(100.0, TemperatureUnit.CELSIUS)));
    }

    // -Conversion Tests ----------

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit_VariousValues() {
        Quantity<TemperatureUnit> q =
                new Quantity<>(50.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                q.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(122.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius_VariousValues() {
        Quantity<TemperatureUnit> q =
                new Quantity<>(122.0, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> result =
                q.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(50.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_RoundTrip_PreservesValue() {

        Quantity<TemperatureUnit> q =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> converted =
                q.convertTo(TemperatureUnit.FAHRENHEIT)
                        .convertTo(TemperatureUnit.CELSIUS);

        assertEquals(q.getValue(), converted.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_SameUnit() {

        Quantity<TemperatureUnit> q =
                new Quantity<>(30.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                q.convertTo(TemperatureUnit.CELSIUS);

        assertEquals(30.0, result.getValue());
    }

    @Test
    void testTemperatureConversion_ZeroValue() {

        Quantity<TemperatureUnit> q =
                new Quantity<>(0.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                q.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(32.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_NegativeValues() {

        Quantity<TemperatureUnit> q =
                new Quantity<>(-20.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                q.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(-4.0, result.getValue(), EPSILON);
    }

    @Test
    void testTemperatureConversion_LargeValues() {

        Quantity<TemperatureUnit> q =
                new Quantity<>(1000.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                q.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(1832.0, result.getValue(), EPSILON);
    }

    // -Unsupported Arithmetic ----------

    @Test
    void testTemperatureUnsupportedOperation_Add() {

        assertThrows(UnsupportedOperationException.class, () ->
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .add(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureUnsupportedOperation_Subtract() {

        assertThrows(UnsupportedOperationException.class, () ->
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .subtract(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
    }

    @Test
    void testTemperatureUnsupportedOperation_Divide() {

        assertThrows(UnsupportedOperationException.class, () ->
                new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                        .divide(new Quantity<>(50.0, TemperatureUnit.CELSIUS)));
    }

    // -Cross Category ----------

    @Test
    void testTemperatureVsLengthIncompatibility() {

        assertFalse(new Quantity<>(100.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(100.0, LengthUnit.FEET)));
    }

    @Test
    void testTemperatureVsWeightIncompatibility() {

        assertFalse(new Quantity<>(50.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(50.0, WeightUnit.KILOGRAM)));
    }

    @Test
    void testTemperatureVsVolumeIncompatibility() {

        assertFalse(new Quantity<>(25.0, TemperatureUnit.CELSIUS)
                .equals(new Quantity<>(25.0, VolumeUnit.LITRE)));
    }

    // --Enum Tests ----------

    @Test
    void testTemperatureUnit_AllConstants() {
        assertNotNull(TemperatureUnit.CELSIUS);
        assertNotNull(TemperatureUnit.FAHRENHEIT);
    }

    @Test
    void testTemperatureEnumImplementsIMeasurable() {
        assertTrue(TemperatureUnit.CELSIUS instanceof IMeasurable);
    }

    @Test
    void testTemperatureUnit_NameMethod() {
        assertEquals("CELSIUS",
                TemperatureUnit.CELSIUS.getUnitName());
    }

    // -Validation ----------

    @Test
    void testTemperatureNullUnitValidation() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(100.0, null));
    }

    @Test
    void testTemperatureNullOperandValidation_InComparison() {

        Quantity<TemperatureUnit> q =
                new Quantity<>(100.0, TemperatureUnit.CELSIUS);

        assertFalse(q.equals(null));
    }

    // --Precision ----------

    @Test
    void testTemperatureConversionPrecision_Epsilon() {

        Quantity<TemperatureUnit> q =
                new Quantity<>(37.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> result =
                q.convertTo(TemperatureUnit.FAHRENHEIT);

        assertEquals(98.6, result.getValue(), 0.01);
    }

    @Test
    void testTemperatureIntegrationWithGenericQuantity() {

        Quantity<TemperatureUnit> q =
                new Quantity<>(25.0, TemperatureUnit.CELSIUS);

        Quantity<TemperatureUnit> converted =
                q.convertTo(TemperatureUnit.FAHRENHEIT);

        assertNotNull(converted);
    }

}