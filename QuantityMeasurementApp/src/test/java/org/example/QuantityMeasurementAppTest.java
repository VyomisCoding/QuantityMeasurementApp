package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {
    // UC4 - Equality Tests
    // Yard Tests
    @Test
    public void testYardToYardEquality() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(1.0, Length.LengthUnit.YARDS)));
    }

    @Test
    public void testYardToFeetEquality() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(3.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testYardToInchEquality() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(36.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testYardToFeetInequality() {
        assertFalse(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(2.0, Length.LengthUnit.FEET)));
    }

    // CM Tests
    @Test
    public void testCmToCmEquality() {
        assertTrue(new Length(2.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(2.0, Length.LengthUnit.CENTIMETERS)));
    }

    @Test
    public void testCmToInchEquality() {
        assertTrue(new Length(1.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testCmToFeetInequality() {
        assertFalse(new Length(1.0, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(1.0, Length.LengthUnit.FEET)));
    }

    // Transitive property UC4
    @Test
    public void testMultiUnitTransitiveProperty() {
        Length A = new Length(1.0, Length.LengthUnit.YARDS);
        Length B = new Length(3.0, Length.LengthUnit.FEET);
        Length C = new Length(36.0, Length.LengthUnit.INCHES);
        assertTrue(A.equals(B));
        assertTrue(B.equals(C));
        assertTrue(A.equals(C));
    }

    // UC5 - Conversion Tests
    @Test
    public void testConversion_FeetToInches() {
        assertEquals(12.0,
                Length.convert(1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES));
    }

    @Test
    public void testConversion_InchesToFeet() {
        assertEquals(2.0,
                Length.convert(24.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.FEET));
    }

    @Test
    public void testConversion_YardsToInches() {
        assertEquals(36.0,
                Length.convert(1.0,
                        Length.LengthUnit.YARDS,
                        Length.LengthUnit.INCHES));
    }

    @Test
    public void testConversion_InchesToYards() {
        assertEquals(2.0,
                Length.convert(72.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.YARDS));
    }

    @Test
    public void testConversion_CmToInches() {
        assertEquals(1.0,
                Length.convert(2.54,
                        Length.LengthUnit.CENTIMETERS,
                        Length.LengthUnit.INCHES),
                0.0001); // epsilon tolerance
    }

    @Test
    public void testConversion_FeetToYards() {
        assertEquals(2.0,
                Length.convert(6.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.YARDS));
    }

    @Test
    public void testConversion_ZeroValue() {
        assertEquals(0.0,
                Length.convert(0.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES));
    }

    @Test
    public void testConversion_NegativeValue() {
        assertEquals(-12.0,
                Length.convert(-1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES));
    }

    @Test
    public void testConversion_RoundTrip() {

        double value = 5.0;

        double converted = Length.convert(
                Length.convert(value,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES),
                Length.LengthUnit.INCHES,
                Length.LengthUnit.FEET);

        assertEquals(value, converted, 0.0001);
    }

    // Validation Tests
    @Test
    public void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> Length.convert(5.0, null, Length.LengthUnit.FEET));
    }

    @Test
    public void testConversion_NaN_Throws() {

        assertThrows(IllegalArgumentException.class, () -> Length.convert(Double.NaN,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES));
    }

    @Test
    public void testConversion_Infinite_Throws() {

        assertThrows(IllegalArgumentException.class, () -> Length.convert(Double.POSITIVE_INFINITY,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES));
    }

    // UC6 Addition Tests
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length result = new Length(1.0, Length.LengthUnit.FEET)
                .add(new Length(2.0, Length.LengthUnit.FEET));
        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        Length result = new Length(6.0, Length.LengthUnit.INCHES)
                .add(new Length(6.0, Length.LengthUnit.INCHES));
        assertEquals(new Length(12.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {

        Length result = new Length(1.0, Length.LengthUnit.FEET)
                .add(new Length(12.0, Length.LengthUnit.INCHES));

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {

        Length result = new Length(12.0, Length.LengthUnit.INCHES)
                .add(new Length(1.0, Length.LengthUnit.FEET));

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_WithZero() {

        Length result = new Length(5.0, Length.LengthUnit.FEET)
                .add(new Length(0.0, Length.LengthUnit.INCHES));

        assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NegativeValues() {

        Length result = new Length(5.0, Length.LengthUnit.FEET)
                .add(new Length(-2.0, Length.LengthUnit.FEET));

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NullSecondOperand() {

        assertThrows(IllegalArgumentException.class, () -> new Length(1.0, Length.LengthUnit.FEET).add(null));
    }

}