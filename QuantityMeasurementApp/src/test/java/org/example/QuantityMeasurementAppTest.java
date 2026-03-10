package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

        // --SUBTRACTION TESTS--

        @Test
        public void testSubtraction_SameUnit_FeetMinusFeet() {
                assertEquals(new Quantity<>(5.0, LengthUnit.FEET),
                                new Quantity<>(10.0, LengthUnit.FEET)
                                                .subtract(new Quantity<>(5.0, LengthUnit.FEET)));
        }

        @Test
        public void testSubtraction_SameUnit_LitreMinusLitre() {
                assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE),
                                new Quantity<>(10.0, VolumeUnit.LITRE)
                                                .subtract(new Quantity<>(3.0, VolumeUnit.LITRE)));
        }

        @Test
        public void testSubtraction_CrossUnit_FeetMinusInches() {
                assertEquals(new Quantity<>(9.5, LengthUnit.FEET),
                                new Quantity<>(10.0, LengthUnit.FEET)
                                                .subtract(new Quantity<>(6.0, LengthUnit.INCHES)));
        }

        @Test
        public void testSubtraction_CrossUnit_InchesMinusFeet() {
                assertEquals(new Quantity<>(60.0, LengthUnit.INCHES),
                                new Quantity<>(120.0, LengthUnit.INCHES)
                                                .subtract(new Quantity<>(5.0, LengthUnit.FEET)));
        }

        @Test
        public void testSubtraction_ExplicitTargetUnit_Feet() {
                assertEquals(new Quantity<>(9.5, LengthUnit.FEET),
                                new Quantity<>(10.0, LengthUnit.FEET)
                                                .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.FEET));
        }

        @Test
        public void testSubtraction_ExplicitTargetUnit_Inches() {
                assertEquals(new Quantity<>(114.0, LengthUnit.INCHES),
                                new Quantity<>(10.0, LengthUnit.FEET)
                                                .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES));
        }

        @Test
        public void testSubtraction_ExplicitTargetUnit_Millilitre() {
                assertEquals(new Quantity<>(3000.0, VolumeUnit.MILLILITRE),
                                new Quantity<>(5.0, VolumeUnit.LITRE)
                                                .subtract(new Quantity<>(2.0, VolumeUnit.LITRE),
                                                                VolumeUnit.MILLILITRE));
        }

        @Test
        public void testSubtraction_ResultingInNegative() {
                assertEquals(new Quantity<>(-5.0, LengthUnit.FEET),
                                new Quantity<>(5.0, LengthUnit.FEET)
                                                .subtract(new Quantity<>(10.0, LengthUnit.FEET)));
        }

        @Test
        public void testSubtraction_ResultingInZero() {
                assertEquals(new Quantity<>(0.0, LengthUnit.FEET),
                                new Quantity<>(10.0, LengthUnit.FEET)
                                                .subtract(new Quantity<>(120.0, LengthUnit.INCHES)));
        }

        @Test
        public void testSubtraction_WithZeroOperand() {
                assertEquals(new Quantity<>(5.0, LengthUnit.FEET),
                                new Quantity<>(5.0, LengthUnit.FEET)
                                                .subtract(new Quantity<>(0.0, LengthUnit.INCHES)));
        }

        @Test
        public void testSubtraction_WithNegativeValues() {
                assertEquals(new Quantity<>(7.0, LengthUnit.FEET),
                                new Quantity<>(5.0, LengthUnit.FEET)
                                                .subtract(new Quantity<>(-2.0, LengthUnit.FEET)));
        }

        @Test
        public void testSubtraction_NonCommutative() {

                Quantity<LengthUnit> A = new Quantity<>(10.0, LengthUnit.FEET)
                                .subtract(new Quantity<>(5.0, LengthUnit.FEET));

                Quantity<LengthUnit> B = new Quantity<>(5.0, LengthUnit.FEET)
                                .subtract(new Quantity<>(10.0, LengthUnit.FEET));

                assertNotEquals(A, B);
        }

        @Test
        public void testSubtraction_WithLargeValues() {
                assertEquals(new Quantity<>(5e5, WeightUnit.KILOGRAM),
                                new Quantity<>(1e6, WeightUnit.KILOGRAM)
                                                .subtract(new Quantity<>(5e5, WeightUnit.KILOGRAM)));
        }

        @Test
        public void testSubtraction_WithSmallValues() {
                assertEquals(new Quantity<>(0.0005, LengthUnit.FEET),
                                new Quantity<>(0.001, LengthUnit.FEET)
                                                .subtract(new Quantity<>(0.0005, LengthUnit.FEET)));
        }

        @Test
        public void testSubtraction_NullOperand() {
                assertThrows(IllegalArgumentException.class,
                                () -> new Quantity<>(10.0, LengthUnit.FEET).subtract(null));
        }

        @Test
        public void testSubtraction_NullTargetUnit() {
                assertThrows(IllegalArgumentException.class,
                                () -> new Quantity<>(10.0, LengthUnit.FEET)
                                                .subtract(new Quantity<>(5.0, LengthUnit.FEET), null));
        }

        @Test
        public void testSubtraction_CrossCategory() {

                Quantity a = new Quantity<>(10.0, LengthUnit.FEET);
                Quantity b = new Quantity<>(5.0, WeightUnit.KILOGRAM);

                assertThrows(IllegalArgumentException.class,
                                () -> a.subtract(b));
        }

        @Test
        public void testSubtraction_ChainedOperations() {

                Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                                .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                                .subtract(new Quantity<>(1.0, LengthUnit.FEET));

                assertEquals(new Quantity<>(7.0, LengthUnit.FEET), result);
        }

        // --DIVISION TESTS--

        @Test
        public void testDivision_SameUnit_FeetDividedByFeet() {
                assertEquals(5.0,
                                new Quantity<>(10.0, LengthUnit.FEET)
                                                .divide(new Quantity<>(2.0, LengthUnit.FEET)));
        }

        @Test
        public void testDivision_SameUnit_LitreDividedByLitre() {
                assertEquals(2.0,
                                new Quantity<>(10.0, VolumeUnit.LITRE)
                                                .divide(new Quantity<>(5.0, VolumeUnit.LITRE)));
        }

        @Test
        public void testDivision_CrossUnit_FeetDividedByInches() {
                assertEquals(1.0,
                                new Quantity<>(24.0, LengthUnit.INCHES)
                                                .divide(new Quantity<>(2.0, LengthUnit.FEET)));
        }

        @Test
        public void testDivision_CrossUnit_KilogramDividedByGram() {
                assertEquals(1.0,
                                new Quantity<>(2.0, WeightUnit.KILOGRAM)
                                                .divide(new Quantity<>(2000.0, WeightUnit.GRAM)));
        }

        @Test
        public void testDivision_RatioGreaterThanOne() {
                assertEquals(5.0,
                                new Quantity<>(10.0, LengthUnit.FEET)
                                                .divide(new Quantity<>(2.0, LengthUnit.FEET)));
        }

        @Test
        public void testDivision_RatioLessThanOne() {
                assertEquals(0.5,
                                new Quantity<>(5.0, LengthUnit.FEET)
                                                .divide(new Quantity<>(10.0, LengthUnit.FEET)));
        }

        @Test
        public void testDivision_RatioEqualToOne() {
                assertEquals(1.0,
                                new Quantity<>(10.0, LengthUnit.FEET)
                                                .divide(new Quantity<>(10.0, LengthUnit.FEET)));
        }

        @Test
        public void testDivision_NonCommutative() {

                double A = new Quantity<>(10.0, LengthUnit.FEET)
                                .divide(new Quantity<>(5.0, LengthUnit.FEET));

                double B = new Quantity<>(5.0, LengthUnit.FEET)
                                .divide(new Quantity<>(10.0, LengthUnit.FEET));

                assertNotEquals(A, B);
        }

        @Test
        public void testDivision_ByZero() {
                assertThrows(ArithmeticException.class,
                                () -> new Quantity<>(10.0, LengthUnit.FEET)
                                                .divide(new Quantity<>(0.0, LengthUnit.FEET)));
        }

        @Test
        public void testDivision_WithLargeRatio() {
                assertEquals(1e6,
                                new Quantity<>(1e6, WeightUnit.KILOGRAM)
                                                .divide(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
        }

        @Test
        public void testDivision_WithSmallRatio() {
                assertEquals(1e-6,
                                new Quantity<>(1.0, WeightUnit.KILOGRAM)
                                                .divide(new Quantity<>(1e6, WeightUnit.KILOGRAM)));
        }

        @Test
        public void testDivision_NullOperand() {
                assertThrows(IllegalArgumentException.class,
                                () -> new Quantity<>(10.0, LengthUnit.FEET).divide(null));
        }

        @Test
        public void testDivision_CrossCategory() {

                Quantity a = new Quantity<>(10.0, LengthUnit.FEET);
                Quantity b = new Quantity<>(5.0, WeightUnit.KILOGRAM);

                assertThrows(IllegalArgumentException.class,
                                () -> a.divide(b));
        }

        // --INTEGRATION/DESIGN TESTS--

        @Test
        public void testSubtractionAndDivision_Integration() {

                double result = new Quantity<>(10.0, LengthUnit.FEET)
                                .subtract(new Quantity<>(5.0, LengthUnit.FEET))
                                .divide(new Quantity<>(5.0, LengthUnit.FEET));

                assertEquals(1.0, result);
        }

        @Test
        public void testSubtractionAddition_Inverse() {

                Quantity<LengthUnit> A = new Quantity<>(10.0, LengthUnit.FEET);

                Quantity<LengthUnit> result = A.add(new Quantity<>(5.0, LengthUnit.FEET))
                                .subtract(new Quantity<>(5.0, LengthUnit.FEET));

                assertEquals(A, result);
        }

        @Test
        public void testSubtraction_Immutability() {

                Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);

                original.subtract(new Quantity<>(5.0, LengthUnit.FEET));

                assertEquals(new Quantity<>(10.0, LengthUnit.FEET), original);
        }

        @Test
        public void testDivision_Immutability() {

                Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);

                original.divide(new Quantity<>(5.0, LengthUnit.FEET));

                assertEquals(new Quantity<>(10.0, LengthUnit.FEET), original);
        }

}