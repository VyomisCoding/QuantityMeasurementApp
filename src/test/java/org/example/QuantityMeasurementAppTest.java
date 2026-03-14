package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest{
        // Equality Tests
        @Test
        public void testEquality_KilogramToKilogram_SameValue(){
                assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                                .equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
        }

        @Test
        public void testEquality_KilogramToKilogram_DifferentValue(){
                assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                                .equals(new QuantityWeight(2.0, WeightUnit.KILOGRAM)));
        }

        @Test
        public void testEquality_KilogramToGram_EquivalentValue(){
                assertTrue(new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                                .equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
        }

        @Test
        public void testEquality_GramToKilogram_EquivalentValue(){
                assertTrue(new QuantityWeight(1000.0, WeightUnit.GRAM)
                                .equals(new QuantityWeight(1.0, WeightUnit.KILOGRAM)));
        }

        @Test
        public void testEquality_NullComparison(){
                assertFalse(new QuantityWeight(1.0, WeightUnit.KILOGRAM).equals(null));
        }

        @Test
        public void testEquality_SameReference(){
                QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
                assertTrue(w.equals(w));
        }

        @Test
        public void testEquality_NullUnit(){
                assertThrows(IllegalArgumentException.class,
                                () -> new QuantityWeight(1.0, null));
        }

        @Test
        public void testEquality_ZeroValue(){
                assertTrue(new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                                .equals(new QuantityWeight(0.0, WeightUnit.GRAM)));
        }

        @Test
        public void testEquality_NegativeWeight(){
                assertTrue(new QuantityWeight(-1.0, WeightUnit.KILOGRAM)
                                .equals(new QuantityWeight(-1000.0, WeightUnit.GRAM)));
        }

        @Test
        public void testEquality_LargeWeightValue(){
                assertTrue(new QuantityWeight(1000000.0, WeightUnit.GRAM)
                                .equals(new QuantityWeight(1000.0, WeightUnit.KILOGRAM)));
        }

        @Test
        public void testEquality_SmallWeightValue(){
                assertTrue(new QuantityWeight(0.001, WeightUnit.KILOGRAM)
                                .equals(new QuantityWeight(1.0, WeightUnit.GRAM)));
        }

        // Conversion Tests
        @Test
        public void testConversion_PoundToKilogram(){
                QuantityWeight result = new QuantityWeight(2.20462, WeightUnit.POUND)
                                .convertTo(WeightUnit.KILOGRAM);

                assertEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), result);
        }

        @Test
        public void testConversion_KilogramToPound(){
                QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                                .convertTo(WeightUnit.POUND);

                assertEquals(new QuantityWeight(2.20462, WeightUnit.POUND), result);
        }

        @Test
        public void testConversion_SameUnit(){
                QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                                .convertTo(WeightUnit.KILOGRAM);

                assertEquals(new QuantityWeight(5.0, WeightUnit.KILOGRAM), result);
        }

        @Test
        public void testConversion_ZeroValue(){
                QuantityWeight result = new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                                .convertTo(WeightUnit.GRAM);

                assertEquals(new QuantityWeight(0.0, WeightUnit.GRAM), result);
        }

        @Test
        public void testConversion_NegativeValue(){
                QuantityWeight result = new QuantityWeight(-1.0, WeightUnit.KILOGRAM)
                                .convertTo(WeightUnit.GRAM);

                assertEquals(new QuantityWeight(-1000.0, WeightUnit.GRAM), result);
        }

        @Test
        public void testConversion_RoundTrip(){
                QuantityWeight result = new QuantityWeight(1.5, WeightUnit.KILOGRAM)
                                .convertTo(WeightUnit.GRAM)
                                .convertTo(WeightUnit.KILOGRAM);

                assertEquals(new QuantityWeight(1.5, WeightUnit.KILOGRAM), result);
        }

        // Addition Tests
        @Test
        public void testAddition_SameUnit_KilogramPlusKilogram(){
                QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                                .add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));

                assertEquals(new QuantityWeight(3.0, WeightUnit.KILOGRAM), result);
        }

        @Test
        public void testAddition_CrossUnit_KilogramPlusGram(){
                QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

                assertEquals(new QuantityWeight(2.0, WeightUnit.KILOGRAM), result);
        }

        @Test
        public void testAddition_CrossUnit_PoundPlusKilogram(){
                QuantityWeight result = new QuantityWeight(2.20462, WeightUnit.POUND)
                                .add(new QuantityWeight(1.0, WeightUnit.KILOGRAM));

                assertEquals(new QuantityWeight(4.40924, WeightUnit.POUND), result);
        }

        @Test
        public void testAddition_ExplicitTargetUnit_Kilogram(){
                QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                                .add(new QuantityWeight(1000.0, WeightUnit.GRAM),
                                                WeightUnit.GRAM);

                assertEquals(new QuantityWeight(2000.0, WeightUnit.GRAM), result);
        }

        @Test
        public void testAddition_WithZero(){
                QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                                .add(new QuantityWeight(0.0, WeightUnit.GRAM));

                assertEquals(new QuantityWeight(5.0, WeightUnit.KILOGRAM), result);
        }

        @Test
        public void testAddition_NegativeValues(){
                QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                                .add(new QuantityWeight(-2000.0, WeightUnit.GRAM));

                assertEquals(new QuantityWeight(3.0, WeightUnit.KILOGRAM), result);
        }

        @Test
        public void testAddition_LargeValues(){
                QuantityWeight result = new QuantityWeight(1e6, WeightUnit.KILOGRAM)
                                .add(new QuantityWeight(1e6, WeightUnit.KILOGRAM));

                assertEquals(new QuantityWeight(2e6, WeightUnit.KILOGRAM), result);
        }

}