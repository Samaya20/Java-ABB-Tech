 public class Main {
    public static void main(String[] args) {

        // 1. declare an int primitive variable and an Integer wrapper variable.
        int num1 = 10;
        Integer obj1;

        // 2. assign the int value to the Integer variable (autoboxing).
        obj1 = num1;

        // 3. declare another Integer wrapper variable and assign it a value.
        Integer obj2 = 20;

        // 4. assign the Integer value to an int variable (unboxing).
        int num2 = obj2;


        System.out.println("integer: " + obj1);
        System.out.println("int: " + num2);

    }
}