package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest{
        // LengthUnit Enum Tests
        @Test
        public void testLengthUnitEnum_FeetConstant(){
                assertEquals(1.0, LengthUnit.FEET.getConversionFactor());
        }

        @Test
        public void testLengthUnitEnum_InchesConstant(){
                assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), 0.0001);
        }

        @Test
        public void testLengthUnitEnum_YardsConstant(){
                assertEquals(3.0, LengthUnit.YARDS.getConversionFactor());
        }

        @Test
        public void testLengthUnitEnum_CentimetersConstant(){
                assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), 0.0001);
        }

        // Convert To Base Unit
        @Test
        public void testConvertToBaseUnit_FeetToFeet(){
                assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0));
        }

        @Test
        public void testConvertToBaseUnit_InchesToFeet(){
                assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0));
        }

        @Test
        public void testConvertToBaseUnit_YardsToFeet(){
                assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0));
        }

        @Test
        public void testConvertToBaseUnit_CentimetersToFeet(){
                assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), 0.0001);
        }

        // Convert From Base Unit
        @Test
        public void testConvertFromBaseUnit_FeetToFeet(){
                assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0));
        }

        @Test
        public void testConvertFromBaseUnit_FeetToInches(){
                assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0));
        }

        @Test
        public void testConvertFromBaseUnit_FeetToYards(){
                assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0));
        }

        @Test
        public void testConvertFromBaseUnit_FeetToCentimeters(){
                assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), 0.0001);
        }

        // Refactored Length Tests
        @Test
        public void testQuantityLengthRefactored_Equality(){
                assertTrue(new Length(1.0, LengthUnit.FEET)
                                .equals(new Length(12.0, LengthUnit.INCHES)));
        }

        @Test
        public void testQuantityLengthRefactored_ConvertTo(){

                Length result = new Length(1.0, LengthUnit.FEET)
                                .convertTo(LengthUnit.INCHES);

                assertEquals(new Length(12.0, LengthUnit.INCHES), result);
        }

        @Test
        public void testQuantityLengthRefactored_Add(){

                Length result = new Length(1.0, LengthUnit.FEET)
                                .add(new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);

                assertEquals(new Length(2.0, LengthUnit.FEET), result);
        }

        @Test
        public void testQuantityLengthRefactored_AddWithTargetUnit(){

                Length result = new Length(1.0, LengthUnit.FEET)
                                .add(new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);

                assertEquals(new Length(0.6666666666666666, LengthUnit.YARDS), result);
        }

        @Test
        public void testQuantityLengthRefactored_NullUnit(){

                assertThrows(IllegalArgumentException.class,
                                () -> new Length(1.0, null));
        }

        @Test
        public void testQuantityLengthRefactored_InvalidValue(){

                assertThrows(IllegalArgumentException.class,
                                () -> new Length(Double.NaN, LengthUnit.FEET));
        }

        // Round Trip Conversion
        @Test
        public void testRoundTripConversion_RefactoredDesign(){

                double value = 5.0;

                double converted = Length.convert(
                                Length.convert(value, LengthUnit.FEET, LengthUnit.INCHES),
                                LengthUnit.INCHES,
                                LengthUnit.FEET);

                assertEquals(value, converted, 0.0001);
        }

        
        // Enum Immutability
        @Test
        public void testUnitImmutability(){

                assertEquals("FEET", LengthUnit.FEET.name());
                assertEquals("INCHES", LengthUnit.INCHES.name());
        }
}