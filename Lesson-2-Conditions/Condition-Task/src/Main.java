public class Main {
    public static void main(String[] args) {

        // 1. int x = -7; dəyişənini təyin edin.
        // Proqram ekranda "Mənfi", "Müsbət" və ya "Sıfır" yazsın.(if-else)

//        int x = -7;
//
//        if (x > 0) {
//            System.out.println("Müsbet");
//        } else if (x < 0) {
//            System.out.println("Menfi");
//        } else {
//            System.out.println("Sifir");
//        }

        // -------------------------------------------

        // 2. int a = 15, b = 28; — bu iki ədəddən böyüyünü ekrana çıxarın.
        // Bərabər olduqda "Bərabərdir" yazsın.(if-else)

//        int a = 15, b = 28;
//
//        if (a > b) {
//            System.out.println(a + " böyükdür (a)");
//        } else if (b > a) {
//            System.out.println(b + " böyükdür (b)");
//        } else {
//            System.out.println("Beraberdirler");
//        }

        // --------------------------------------------

        // 3. int n = 12; — ədədin həm cüt/tək, həm də müsbət/mənfi olduğunu ayrıca ekranda göstərin.
        // (İç-içə if istifadə edin.)(if-else)

//        int n = 12;
//
//        if ((n % 2) == 0) {
//            System.out.print("Eded cütdür");
//
//            if (n < 0) {
//                System.out.print(" ve menfidir");
//            } else {
//                System.out.print(" ve müsbetdir");
//            }
//        } else {
//            System.out.print("Eded tekdir");
//
//            if (n < 0) {
//                System.out.print(" ve menfidir");
//            } else {
//                System.out.print(" ve müsbetdir");
//            }
//        }

        // -----------------------------------------------

        // 4. int x = 10, y = 4; char op = '+'; — op dəyişəninə görə toplama, çıxma, vurma və bölmə əməliyyatı aparsın.
        // Sıfıra bölmə halında xəbərdarlıq çıxarsın.(swich-case)


//        int x = 10, y = 4;
//        char op = '/';
//        int result;
//
//        switch (op) {
//            case '+' :
//                result = x + y;
//                System.out.println(result);
//                break;
//            case '-' :
//                result = x - y;
//                System.out.println(result);
//                break;
//            case '*' :
//                result = x * y;
//                System.out.println(result);
//                break;
//            case '/' :
//                if (y != 0 ){
//                    result = x / y;
//                    System.out.println(result);
//                } else {
//                    System.out.println("Eded sifira bolune bilmez!");
//                }
//                break;
//            default :
//                System.out.println("Operator teyin olunmayib!");
//        }

        // -----------------------------------

        // 5. int ay = 11; — ay nömrəsinə görə fəsli ("Yaz", "Yay", "Payız", "Qış") ekrana çıxarın.
        // Birdən çox case eyni nəticəyə aparmalıdır.(swich-case)

        int ay = 7;

        String season = switch (ay) {
            case 12, 1, 2 -> "Qış";
            case 3, 4, 5 -> "Yaz";
            case 6, 7, 8 -> "Yay";
            case 9, 10, 11 -> "Payız";
            default -> "Bele bir ay teyin olunmayib!";
        };

        System.out.println(season);


    }
}
