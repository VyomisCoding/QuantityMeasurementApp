package org.example;

public class QuantityMeasurementApp {
    public static class Feet {               // Inner Class
        private final double value;          // Encapsulation + Immutability

        public Feet(double value) {          // Constructor
            this.value = value;
        }

        public double getValue() {           // Getter
            return value;
        }


        @Override
        public boolean equals(Object obj){

            if(this == obj){
                return true;
            }

            if (obj == null){      // Null check
                return false;
            }

            if (getClass() != obj.getClass()){    // Type check
                return false;
            }

            Feet other = (Feet) obj;              // Type casting
            return Double.compare(this.value, other.value) == 0;      // Floating point comparison
        }

        @Override
        public int hashCode(){
            return Double.hashCode(value);
        }
    }

    public static void main(String[] args){        // Main
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        boolean result = feet1.equals(feet2);
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + result + ")");
    }

}
