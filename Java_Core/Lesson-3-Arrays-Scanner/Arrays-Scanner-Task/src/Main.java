import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        // Task 1:
        // Ask the user to enter a number. Determine if the number is positive, negative, or zero, and print the result.

//        System.out.print("Enter the number: ");
//        int userNum = scanner.nextInt();
//
//        if (userNum == 0) {
//            System.out.println("Number is zero.");
//        } else {
//            String result = userNum < 0 ? "Number is negative." : "Number is positive.";
//            System.out.println(result);
//        }
//

        // --------------------------------------------------------------------

        // Task 2:
        // Ask the user to enter a number between 1 and 7. Print the corresponding day of the week for that number.

//        System.out.print("Enter the number (1-7): ");
//        int num = scanner.nextInt();
//
//        switch (num){
//            case 1:
//                System.out.println("Monday");
//                break;
//            case 2:
//                System.out.println("Tuesday");
//                break;
//            case 3:
//                System.out.println("Wednesday");
//                break;
//            case 4:
//                System.out.println("Thursday");
//                break;
//            case 5:
//                System.out.println("Friday");
//                break;
//            case 6:
//                System.out.println("Saturday");
//                break;
//            case 7:
//                System.out.println("Sunday");
//                break;
//            default:
//                System.out.println("Invalid day!");
//        }


        // -------------------------------------------------------------------------------

        // Task 3:
        // Ask the user to enter a number. Using a loop, print all the odd numbers up to that number.

//        System.out.print("Enter the number: ");
//        int num = scanner.nextInt();
//
//        for (int i = 1; i < num; i++) {
//            if (i % 2 != 0) {
//                System.out.println(i);
//            }
//        }

        // --------------------------------------------------------------------------------

        // Task 4:

        // Ask the user to enter a number. Perform the following checks

//        System.out.print("Enter the number: ");
//        int num = scanner.nextInt();

        // If the number is a palindrome (e.g., 12321), display:

//        int original = num;
//        int reverse = 0;
//
//        while (num > 0) {
//            reverse = (reverse * 10) + num % 10;
//            num = num / 10;
//        }
//
//        if (reverse == original) {
//            System.out.println("Number is palindrome.");
//        } else {
//            System.out.println("Number is not palindrome!");
//        }


        // If the digits of the number are in increasing order (e.g., 123489), indicate that.
        // If the digits of the number are in decreasing order (e.g., 97530), indicate that.
        // Otherwise, display:  "The digits are neither in increasing nor decreasing order."

//        int lastNum = num % 10;
//        num = num / 10;
//        boolean isDecrease = true;
//        boolean isIncrease = true;
//
//        while (num > 0) {
//            int currentNum = num % 10;
//            if (currentNum < lastNum) {
//                isDecrease = false;
//            }
//            if (currentNum > lastNum) {
//                isIncrease = false;
//            }
//            lastNum = currentNum;
//            num = num / 10;
//        }
//
//        if (isDecrease) {
//            System.out.println("Number is decreasing order.");
//        } else if (isIncrease) {
//            System.out.println("Number is increasing order.");
//        } else {
//            System.out.println("Neither in increasing nor decreasing order.");
//        }


        // ----------------------------------------------------------------------------------

        // Task 5:
        // Ask the user to enter their age and gender (M or F).
        // If the user is under 18, print "Access denied".
        // If the user is 18 or older, print:
        // For M: "Male access granted"
        // For F: "Female access granted"
        // If the user enters an incorrect gender, print "Invalid gender entered".


//        System.out.print("Enter your age: ");
//        int age = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("\nEnter your gender (M/F): ");
//        char gender = scanner.next().charAt(0);
//
//        if (age < 18) {
//            System.out.println("Access Denied!");
//        } else {
//            if (gender == 'M' || gender == 'm') {
//                System.out.println("Male access granted.");
//            } else if (gender == 'F' || gender == 'f') {
//                System.out.println("Female access granted.");
//            } else {
//                System.out.println("Invalid gender entered.");
//            }
//        }


        // ----------------------------------------------------------------------------

        // Task 6:
        // Write a program that prints Fibonacci numbers.
        // Ask the user how many Fibonacci numbers to print.
        // If the entered number is zero or negative, print "Please enter a valid number".
        // Use loops to print the Fibonacci sequence.
        // Use a to show whether each Fibonacci number is even or odd.


//        System.out.println("Enter your count: ");
//        int count = scanner.nextInt();
//
//        if (count <= 0) {
//            System.out.println("Please enter a valid number: ");
//            count = scanner.nextInt();
//        } else {
//            int a = 1, b = 1;
//
//            for (int i = 0; i < count; i++) {
//                System.out.println(a + ((a % 2 == 0) ? " Even" : " Odd"));
//                int next = a + b;
//                a = b;
//                b = next;
//            }
//        }


        // -----------------------------------------------------------------------------

        // Task 7:
        // Ask the user to enter a number and calculate its factorial.
        // If the user enters a negative number, print "Factorial does not exist for negative numbers".
        // Use loops and (like i++ or --) during the calculation.
        // Print the result.

//        System.out.print("Enter your number: ");
//        int num = scanner.nextInt();
//
//        if (num < 0) {
//            System.out.println("Number is negative!!");
//        } else {
//            long fact = 1;
//            for (int i = 1; i <= num; i++) {
//                fact = fact * i;
//            }
//            System.out.println(num + " != " + fact);
//        }

        // ------------------------------------------------------------------------------------

        // Task 8:
        // A "Strong number" is defined as follows:

        // If the sum of the factorials of each digit of the number equals the number itself, then it is a .
        // 145 → 1! + 4! + 5! = 1 + 24 + 120 = → ✅
        // 123 → 1! + 2! + 3! = 1 + 2 + 6 = ≠ 123 → ❌
        // Calculate the factorial for each digit.
        // Sum them up and compare the result with the original number.


//        System.out.print("Enter a number: ");
//        int num = scanner.nextInt();
//
//        int originalNum = num;
//        int sum = 0;
//
//        while (num > 0) {
//            int lastNum = num % 10;
//            int fact = 1;
//            for (int i = 1; i <= lastNum; i++) {
//                fact = fact * i;
//            }
//            sum = fact + sum;
//            num = num / 10;
//        }
//
//        if (sum == originalNum) {
//            System.out.println(originalNum + " is a strong number.");
//        } else {
//            System.out.println(originalNum + " not a strong number!");
//        }


        // ----------------------------------------------------------------------------

        // Task 9:
        // An is defined as:
        // If the sum of each digit raised to the power of the number of digits equals the number itself, then it is an .
        // 153 → 1³ + 5³ + 3³ = 1 + 125 + 27 = → ✅
        // 9474 → 9⁴ + 4⁴ + 7⁴ + 4⁴ = 6561 + 256 + 2401 + 256 = → ✅
        // 123 → 1³ + 2³ + 3³ = 1 + 8 + 27 = ≠ 123 → ❌
        // Extract the digits of the number.
        // Count how many digits it has.
        // Raise each digit to the power of the number of digits.
        // Sum the results and compare with the original number.

//        System.out.print("Enter a number: ");
//        int num = scanner.nextInt();
//
//        int originalNum = num;
//        int temp = num;
//        int reqemSayi = 0;
//
//        while (temp > 0) {
//            temp = temp / 10;
//            reqemSayi++;
//        }
//
//        temp = num;
//        int sum = 0;
//
//        while (temp > 0) {
//            int reqem = temp % 10;
//
//            //bu hisseni eslinde hazır metodla yazmaq olardı,
//            //quvveti tapmaq üçün, amma keçməmişik deyə dövrlə yazıram.
//            int quvvet = 1;
//            for (int i = 0; i < reqemSayi; i++) {
//                quvvet = quvvet * reqem;
//            }
//
//            sum = sum + quvvet;
//            temp = temp / 10;
//        }
//
//        if (sum == originalNum) {
//            System.out.println(originalNum + " is a armstrong number.");
//        } else {
//            System.out.println(originalNum + " not a armstrong number!!");
//        }


        // -------------------------------------------------------------------------

        // Task 10: Ən böyük və ən kiçik ədədi tap
        // Şərt:
        // Verilmiş int[] tipli array-dən
        // istifadə edərək:
        // Ən böyük ədədi tap.
        // Ən kiçik ədədi tap.
        // Hər ikisini ekrana çap et.
        // Nümunə:
        // int[] numbers = {4, 7, -2, 15, 0, 99, -100};
        // Gözlənilən çıxış:
        // Ən böyük ədəd: 99
        // Ən kiçik ədəd: -100

//        int[] numbers = {4, 7, -2, 15, 0, 99, -100};
//
//        int max = numbers[0];
//        int min = numbers[0];
//
//        for (int i = 1; i < numbers.length; i++) {
//            if (numbers[i] > max) {
//                max = numbers[i];
//            } else if (numbers[i] < min) {
//                min = numbers[i];
//            }
//        }
//
//        System.out.println("En böyük eded: " + max);
//        System.out.println("En kicik eded: " + min);


        // --------------------------------------------------------------------

        // Task 11: 2 Ölçülü array-də əsas və köməkçi diaqonalın
        // cəmi
        // Şərt:
        // 3x3 ölçülü kvadrat matrisi
        // verilir.
        // Əsas diaqonal: matrix[0][0],
        // matrix[1][1], matrix[2][2]
        // Köməkçi diaqonal: matrix[0][2],
        //         matrix[1][1], matrix[2][0]
        // Hər iki diaqonalın cəmini hesabla
        // və çap et
        // (hesablayan zaman sadece : matrix[0][0]+ matrix[1][1]+matrix[2][2]etmeyin,dovrlerden
        // istifade edin).
        // Nümunə:
        // int[][] matrix = {
        //         {1, 2, 3},
        //         {4, 5, 6},
        //         {7, 8, 9}
        // };
        // Gözlənilən çıxış:
        // Əsas diaqonal cəmi: 15
        // Köməkçi diaqonal cəmi: 15


//        int[][] matrix = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}
//        };
//
//        int firstSum = 0;
//        int secondSum = 0;
//        int a = matrix.length;
//
//        for (int i = 0; i < a; i++) {
//            firstSum += matrix[i][i];
//            secondSum += matrix[i][a - 1 - i];
//        }
//
//        System.out.println("Esas diaqonalin cemi: " + firstSum);
//        System.out.println("Kömekchi diaqonalin cemi: " + secondSum);

        // -------------------------------------------------------------------------------------


        // Task 12: 3 Ölçülü Array-də müəyyən şərtə uyğun ədədləri
        // çap et
        // Şərt:
        // 3D array verilir.
        // 6-dan böyük olan bütün ədədləri
        // tap və çap et.
        // Nümunə:
        // int[][][] cube = {
        //         {
        //                 {1, 2}, {3, 4}
        //         },
        //         {
        //                 {5, 6}, {7, 8}
        //         }
        // };
        // Gözlənilən çıxış:
        // 6-dan böyük ədədlər: 7, 8


//        int[][][] cube = {
//                {
//                        {1, 2}, {3, 4}
//                },
//                {
//                        {5, 6}, {7, 8}
//                }
//        };
//
//        System.out.print("6-dan böyük ededler: ");
//
//        for (int[][] matrix : cube) {
//            for (int[] row : matrix) {
//                for (int num : row) {
//                    if (num > 6) {
//                        System.out.print(num + " ");
//                    }
//                }
//            }
//        }

        // --------------------------------------------------------------------

        // Task 13:Verilən int[] array-i artan sıraya görə sort et.
        //         Daha sonra array-in tərs versiyasını çap et.
        // Arrays.sort()
        // methodu istifade etmeyin.
        //         Nümunə:
        // int[] arr = {10, 5, 8, 3, 1};
        // Gözlənilən çıxış:
        // Tərsinə sort edilmiş array: 10 8 5 3 1


        int[] arr = {10, 5, 8, 3, 1};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.print("Tersine sort edilmish array: ");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }


        scanner.close();

    }
}