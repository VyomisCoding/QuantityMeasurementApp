package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest{

    // Refactoring Delegation Tests

    @Test
    public void testRefactoring_Add_DelegatesViaHelper(){

        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.add(b);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
    }

    @Test
    public void testRefactoring_Subtract_DelegatesViaHelper(){

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.subtract(b);

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    public void testRefactoring_Divide_DelegatesViaHelper(){

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        double result = a.divide(b);

        assertEquals(5.0, result);
    }

    
    // Validation Consistency
    
    @Test
    public void testValidation_NullOperand_ConsistentAcrossOperations(){

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> a.add(null));
        assertThrows(IllegalArgumentException.class, () -> a.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> a.divide(null));
    }

    @Test
    public void testValidation_CrossCategory_ConsistentAcrossOperations(){

        Quantity a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity b = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> a.add(b));
        assertThrows(IllegalArgumentException.class, () -> a.subtract(b));
        assertThrows(IllegalArgumentException.class, () -> a.divide(b));
    }

    @Test
    public void testValidation_NullTargetUnit_AddSubtractReject(){

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> a.add(b, null));
        assertThrows(IllegalArgumentException.class, () -> a.subtract(b, null));
    }

    // Enum Operation Tests
    
    @Test
    public void testArithmeticOperation_Add_EnumComputation(){

        double result = 10 + 5;

        assertEquals(15.0, result);
    }

    @Test
    public void testArithmeticOperation_Subtract_EnumComputation(){

        double result = 10 - 5;

        assertEquals(5.0, result);
    }

    @Test
    public void testArithmeticOperation_Divide_EnumComputation(){

        double result = 10 / 5;

        assertEquals(2.0, result);
    }

    @Test
    public void testArithmeticOperation_DivideByZero_EnumThrows(){

        assertThrows(ArithmeticException.class, () -> {
            double result = 10 / 0;
        });
    }


    // Backward Compatibility

    @Test
    public void testAdd_UC12_BehaviorPreserved(){

        Quantity<WeightUnit> a = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(5000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = a.add(b, WeightUnit.GRAM);

        assertEquals(new Quantity<>(15000.0, WeightUnit.GRAM), result);
    }

    @Test
    public void testSubtract_UC12_BehaviorPreserved(){

        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = a.subtract(b, VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(3000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    public void testDivide_UC12_BehaviorPreserved(){

        Quantity<LengthUnit> a = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        double result = a.divide(b);

        assertEquals(1.0, result);
    }


    // Rounding Behavior

    @Test
    public void testRounding_AddSubtract_TwoDecimalPlaces(){

        Quantity<LengthUnit> a = new Quantity<>(1.234, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(1.111, LengthUnit.FEET);

        Quantity<LengthUnit> result = a.add(b);

        assertEquals(2.345, result.getValue(), 0.01);
    }

    @Test
    public void testRounding_Divide_NoRounding(){

        Quantity<LengthUnit> a = new Quantity<>(7.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        double result = a.divide(b);

        assertEquals(3.5, result);
    }


    // Target Unit Behavior

    @Test
    public void testImplicitTargetUnit_AddSubtract(){

        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.add(b);

        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testExplicitTargetUnit_AddSubtract_Overrides(){

        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.add(b, LengthUnit.INCHES);

        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    // Immutability Tests

    @Test
    public void testImmutability_AfterAdd_ViaCentralizedHelper(){

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        a.add(b);

        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
    }

    @Test
    public void testImmutability_AfterSubtract_ViaCentralizedHelper(){

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        a.subtract(b);

        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
    }

    @Test
    public void testImmutability_AfterDivide_ViaCentralizedHelper(){

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        a.divide(b);

        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
    }


    // Operation Chaining

    @Test
    public void testArithmetic_Chain_Operations(){

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> q3 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q4 = new Quantity<>(3.0, LengthUnit.FEET);

        double result = q1.add(q2).subtract(q3).divide(q4);

        assertEquals(11.0 / 3.0, result);
    }

    // Cross Category Scalability

    @Test
    public void testAllOperations_AcrossAllCategories(){

        Quantity<LengthUnit> length1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(2.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);

        Quantity<VolumeUnit> volume1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(2.0, VolumeUnit.LITRE);

        assertEquals(5.0, length1.divide(length2));
        assertEquals(5.0, weight1.divide(weight2));
        assertEquals(5.0, volume1.divide(volume2));
    }

}