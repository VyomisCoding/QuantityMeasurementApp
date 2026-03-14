package org.example;

public class QuantityMeasurementApp {

    public static void main(String[] args){
        
        Length l1 = new Length(1.0, LengthUnit.FEET);                         // LENGTH (existing UC8)
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println(l1.add(l2, LengthUnit.FEET));

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);     // WEIGHT (UC9)
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        System.out.println(w1.equals(w2));
        System.out.println(w1.convertTo(WeightUnit.POUND));
        System.out.println(w1.add(w2, WeightUnit.GRAM));
    }
}