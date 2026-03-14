package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {
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
}
