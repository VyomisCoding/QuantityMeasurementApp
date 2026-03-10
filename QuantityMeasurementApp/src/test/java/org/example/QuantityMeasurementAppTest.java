package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    // =========================
    // Equality Tests
    // =========================

    @Test
    public void testEquality_LitreToLitre_SameValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_LitreToLitre_DifferentValue() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(2.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testEquality_MillilitreToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_LitreToGallon_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(0.26417, VolumeUnit.GALLON)));
    }

    @Test
    public void testEquality_GallonToLitre_EquivalentValue() {
        assertTrue(new Quantity<>(1.0, VolumeUnit.GALLON)
                .equals(new Quantity<>(3.78541, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_VolumeVsLength_Incompatible() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, LengthUnit.FEET)));
    }

    @Test
    public void testEquality_VolumeVsWeight_Incompatible() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
    }

    @Test
    public void testEquality_NullComparison() {
        assertFalse(new Quantity<>(1.0, VolumeUnit.LITRE).equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(v.equals(v));
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    public void testEquality_TransitiveProperty() {

        Quantity<VolumeUnit> A = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> B = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> C = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertTrue(A.equals(B));
        assertTrue(B.equals(C));
        assertTrue(A.equals(C));
    }

    @Test
    public void testEquality_ZeroValue() {
        assertTrue(new Quantity<>(0.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(0.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testEquality_NegativeVolume() {
        assertTrue(new Quantity<>(-1.0, VolumeUnit.LITRE)
                .equals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    public void testEquality_LargeVolumeValue() {
        assertTrue(new Quantity<>(1000000.0, VolumeUnit.MILLILITRE)
                .equals(new Quantity<>(1000.0, VolumeUnit.LITRE)));
    }

    @Test
    public void testEquality_SmallVolumeValue() {
        assertTrue(new Quantity<>(0.001, VolumeUnit.LITRE)
                .equals(new Quantity<>(1.0, VolumeUnit.MILLILITRE)));
    }

    // =========================
    // Conversion Tests
    // =========================

    @Test
    public void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    public void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE), result);
    }

    @Test
    public void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(new Quantity<>(3.78541, VolumeUnit.LITRE), result);
    }

    @Test
    public void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> result =
                new Quantity<>(3.78541, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.GALLON);

        assertEquals(new Quantity<>(1.0, VolumeUnit.GALLON), result);
    }

    @Test
    public void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.GALLON);

        assertEquals(new Quantity<>(0.26417, VolumeUnit.GALLON), result);
    }

    @Test
    public void testConversion_SameUnit() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(new Quantity<>(5.0, VolumeUnit.LITRE), result);
    }

    @Test
    public void testConversion_ZeroValue() {
        Quantity<VolumeUnit> result =
                new Quantity<>(0.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(0.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    public void testConversion_NegativeValue() {
        Quantity<VolumeUnit> result =
                new Quantity<>(-1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(-1000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    public void testConversion_RoundTrip() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.5, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(new Quantity<>(1.5, VolumeUnit.LITRE), result);
    }

    // =========================
    // Addition Tests
    // =========================

    @Test
    public void testAddition_SameUnit_LitrePlusLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(new Quantity<>(3.0, VolumeUnit.LITRE), result);
    }

    @Test
    public void testAddition_SameUnit_MillilitrePlusMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(500.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(500.0, VolumeUnit.MILLILITRE));

        assertEquals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    public void testAddition_CrossUnit_LitrePlusMillilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), result);
    }

    @Test
    public void testAddition_CrossUnit_MillilitrePlusLitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(1.0, VolumeUnit.LITRE));

        assertEquals(new Quantity<>(2000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Litre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.LITRE);

        assertEquals(new Quantity<>(2.0, VolumeUnit.LITRE), result);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Millilitre() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.MILLILITRE);

        assertEquals(new Quantity<>(2000.0, VolumeUnit.MILLILITRE), result);
    }

    @Test
    public void testAddition_WithZero() {

        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

        assertEquals(new Quantity<>(5.0, VolumeUnit.LITRE), result);
    }

    @Test
    public void testAddition_NegativeValues() {

        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

        assertEquals(new Quantity<>(3.0, VolumeUnit.LITRE), result);
    }

    @Test
    public void testAddition_LargeValues() {

        Quantity<VolumeUnit> result =
                new Quantity<>(1e6, VolumeUnit.LITRE)
                        .add(new Quantity<>(1e6, VolumeUnit.LITRE));

        assertEquals(new Quantity<>(2e6, VolumeUnit.LITRE), result);
    }

    @Test
    public void testAddition_SmallValues() {

        Quantity<VolumeUnit> result =
                new Quantity<>(0.001, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.002, VolumeUnit.LITRE));

        assertEquals(new Quantity<>(0.003, VolumeUnit.LITRE), result);
    }
}