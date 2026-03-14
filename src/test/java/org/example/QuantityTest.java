package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityTest {

    // ==============================
    // IMeasurable Interface Tests
    // ==============================

    @Test
    public void testIMeasurableInterface_LengthUnitImplementation() {

        assertNotNull(LengthUnit.FEET.getConversionFactor());
        assertEquals(1.0, LengthUnit.FEET.convertToBaseUnit(1.0));
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0));
    }

    @Test
    public void testIMeasurableInterface_WeightUnitImplementation() {

        assertEquals(1.0, WeightUnit.KILOGRAM.convertToBaseUnit(1.0));
        assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0));
    }

    @Test
    public void testIMeasurableInterface_ConsistentBehavior() {

        assertTrue(LengthUnit.FEET instanceof IMeasurable);
        assertTrue(WeightUnit.KILOGRAM instanceof IMeasurable);
    }

    // ==============================
    // Equality Tests
    // ==============================

    @Test
    public void testGenericQuantity_LengthOperations_Equality() {

        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(a.equals(b));
    }

    @Test
    public void testGenericQuantity_WeightOperations_Equality() {

        Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(a.equals(b));
    }

    // ==============================
    // Conversion Tests
    // ==============================

    @Test
    public void testGenericQuantity_LengthOperations_Conversion() {

        Quantity<LengthUnit> q =
                new Quantity<>(1.0, LengthUnit.FEET)
                        .convertTo(LengthUnit.INCHES);

        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), q);
    }

    @Test
    public void testGenericQuantity_WeightOperations_Conversion() {

        Quantity<WeightUnit> q =
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), q);
    }

    // ==============================
    // Addition Tests
    // ==============================

    @Test
    public void testGenericQuantity_LengthOperations_Addition() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                a.add(b, LengthUnit.FEET);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testGenericQuantity_WeightOperations_Addition() {

        Quantity<WeightUnit> a =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> b =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                a.add(b, WeightUnit.KILOGRAM);

        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
    }

    // ==============================
    // Cross Category Prevention
    // ==============================

    @Test
    public void testCrossCategoryPrevention_LengthVsWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }

    // ==============================
    // Constructor Validation
    // ==============================

    @Test
    public void testGenericQuantity_ConstructorValidation_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    public void testGenericQuantity_ConstructorValidation_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    // ==============================
    // HashCode Consistency
    // ==============================

    @Test
    public void testHashCode_GenericQuantity_Consistency() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(a.hashCode(), b.hashCode());
    }

    // ==============================
    // Equals Contract
    // ==============================

    @Test
    public void testEquals_GenericQuantity_ContractPreservation() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> c =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    // ==============================
    // Immutability
    // ==============================

    @Test
    public void testImmutability_GenericQuantity() {

        Quantity<LengthUnit> a =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> b =
                a.convertTo(LengthUnit.INCHES);

        assertNotSame(a, b);
    }
}