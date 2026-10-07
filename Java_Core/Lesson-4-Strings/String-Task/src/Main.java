public class Main {

    // Task 1 method

    public static boolean isPalindrome(String text) {
        String cleanedSentence = text.replaceAll("[^a-zA-Z0-9əöüçıəğşƏÖÜÇİƏĞŞ ]", "");

        for (String word : cleanedSentence.split("\\s+")) {
            if (word.isEmpty()) continue;

            String cleanWord = word.toLowerCase();
            String reversed = new StringBuilder(cleanWord).reverse().toString();

            if (!cleanWord.equals(reversed)) {
                return false;
            }
        }
        return true;
    }

    // --------------------------------------------------------------------------------

    // Task 2 method

    public static boolean isAnagram(String s1, String s2) {
        if (s1.equals(s2)) return false;

        StringBuilder sb = new StringBuilder(s2);

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            int index = sb.indexOf(String.valueOf(c));

            if (index == -1) {
                return false;
            }

            sb.deleteCharAt(index);
        }

        return true;
    }

    // --------------------------------------------------------------------------------

    // Task 3 method

    public static String findPrefix(String[] words) {
        if (words == null || words.length == 0) return "";

        String prefix = words[0];

        for (int i = 1; i < words.length; i++) {
            while (words[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);

                if (prefix.isEmpty()) return "prefiks yoxdur";
            }
        }
        return prefix;
    }

    // -----------------------------------------------------------------------------------

    // Task 4 method

    public static int findLongestSubstring(String s) {
        int maxLength = 0;
        String currentWindow = "";

        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));

            if (currentWindow.contains(c)) {
                currentWindow = currentWindow.substring(currentWindow.indexOf(c) + 1);
            }

            currentWindow += c;

            if (currentWindow.length() > maxLength) {
                maxLength = currentWindow.length();
            }
        }
        return maxLength;
    }

    // ---------------------------------------------------------------------------------------

    // Task 5 methods

    public static String encode(String text) {
        if (text == null || text.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 0; i < text.length(); i++) {
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                count++;
            } else {
                sb.append(text.charAt(i));
                sb.append(count);
                count = 1;
            }
        }
        return sb.toString();
    }

    public static String decode(String text) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char character = text.charAt(i);
            int count = Character.getNumericValue(text.charAt(i + 1));

            for (int j = 0; j < count; j++) {
                sb.append(character);
            }
        }
        return sb.toString();
    }

    // -------------------------------------------------------------------------------

    // Task 6 method

    public static void countCharacters(String text) {
        text = text.toLowerCase();
        int[] counts = new int[26];

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c >= 'a' && c <= 'z') {
                counts[c - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                System.out.println((char) (i + 'a') + " - " + counts[i]);
            }
        }
    }

    // ---------------------------------------------------------------------------

    // Task 7 method

    public static void findWords(String[] words, String prefix) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(prefix)) {
                System.out.println(words[i]);
            }
        }
    }


    // ---------------------------------------------------------------------------------------

    // MAIN FUNCTION (Prints)

    public static void main(String[] args) {


        // Tapşırıq 1: Palindrom Yoxlanışı
        // Verilmiş cümlənin palindrom olub-olmadığını yoxlayan funksiya yaz. Böyük/kiçik hərf, boşluq və durğu işarələrinə fikir vermə.
        // "Ada, sammas madam" true
        // "Salam dünya" false


//        System.out.println(isPalindrome("Ada, sammas madam"));
//        System.out.println(isPalindrome("Salam dünya"));


        // ----------------------------------------------------------------------------------

        // Tapşırıq 2: Anagram Qrupları
        // Söz siyahısı verilib. Bir-birinin anagramı olan sözləri eyni qrupda topla.
        // ["ət", "tə", "kiş", "şik", "ana"]
        // [["ət", "tə"], ["kiş", "şik"], ["ana"]]


//        String[] words = {"ət", "tə", "ət", "kiş", "şik", "ana"};
//        boolean[] used = new boolean[words.length];
//
//        for (int i = 0; i < words.length; i++) {
//            if (used[i]) continue;
//
//            System.out.print("[" + words[i]);
//            used[i] = true;
//
//            for (int j = i + 1; j < words.length; j++) {
//                if (!used[j] && i != j && isAnagram(words[i], words[j])) {
//                    System.out.print(", " + words[j]);
//                    used[j] = true;
//                }
//            }
//            System.out.println("]");
//        }


        // -----------------------------------------------------------------------------

        // Tapşırıq 3: Ən Uzun Ümumi Prefiks
        // Sözlər massivində bütün sözlərin ortaq başlanğıc hissəsini (prefiksini) tap.
        // ["telefon", "televizor", "teleskop"]  -  "tele"
        // ["it", "pişik"] - ""


//        System.out.println("1.array prefix: " + findPrefix(new String[]{"telefon", "televizor", "teleskop"}));
//        System.out.println("2.array prefix: " + findPrefix(new String[]{"it", "pişik"}));

        // ------------------------------------------------------------------------------

        // Tapşırıq 4: Təkrarsız Simvollu Ən Uzun Alt-sətir
        // Stringdə heç bir simvolun təkrarlanmadığı ən uzun ardıcıl hissəni tap.
        // "abcabcbb" -  3 ("abc")
        // "bbbbb" - 1 ("b")

//        System.out.println(findLongestSubstring("abcabcdbb"));
//        System.out.println(findLongestSubstring("bbbbb"));

        // ----------------------------------------------------------------------------------

        // Tapşırıq 5: String Sıxılması (Run-Length Encoding)
        //Ardıcıl təkrarlanan simvolları sayı ilə əvəz edən sıxma funksiyası yaz. Sonra əks əməliyyatı — açma (decode) funksiyasını da yaz.
        //"aaabbc"
        //"a3b2c1"
        //"a3b2c1"
        //"aaabbc"

//        System.out.println(encode("aaabbc"));
//        System.out.println(decode("a3b2c1"));

        // -------------------------------------------------------------------------------------

        //Tapşırıq 6:Verilmiş cümlədə hər bir hərfin neçə dəfə təkrarlandığını tap.
        //Boşluq və xüsusi simvolları nəzərə alma.
        //Nümunə:
        //String sentence = "Java is awesome";
        //Gözlənilən çıxış (sadəcə nümunə):
        //a - 3
        //e - 2
        //i - 1
        //j - 1
        //
        //.....
        //her bir herf nezere alinsin


//        countCharacters("Java is awesome");

        // ---------------------------------------------------------------------------------------

        //Tapşırıq 7: Trie ilə Avtomatik Tamamlama
        //Trie (prefiks ağacı) strukturu qur. Verilmiş prefiksə uyğun lüğətdəki bütün sözləri tapan funksiya yaz.
        //words = ["kitab", "kitabxana", "kino", "kompüter"], prefix = "kit"
        //["kitab", "kitabxana"]


//        String[] words = {"kitab", "kitabxana", "kino", "kompüter"};
//        String prefix = "kit";
//
//        findWords(words, prefix);


    }
}