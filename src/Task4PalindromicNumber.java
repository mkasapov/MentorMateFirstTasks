import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4PalindromicNumber {

    public static void main(String[] args) {
        System.out.println(getAllPalindromesReversedOrder().get(0));
    }

    static boolean isPalindromic(int number) {
        int numberToBeChecked = number;
        int lastDigit;
        int reversedNumber = 0;
        while (number > 0) {
            lastDigit = number % 10;
            reversedNumber = (reversedNumber * 10) + lastDigit;
            number = number / 10;
        }

        return numberToBeChecked == reversedNumber;
    }

    static List<Integer> getAllPalindromesReversedOrder() {

        List<Integer> palindromes = new ArrayList<>();
        for (int i = 100; i < 1000; i++) {
            for (int j = 100; j < 1000; j++) {
                int currentProduct = i * j;
                if (isPalindromic(currentProduct)) {
                    palindromes.add(currentProduct);
                }
            }
        }

        palindromes.sort(Collections.reverseOrder());
        return palindromes;
    }


}
