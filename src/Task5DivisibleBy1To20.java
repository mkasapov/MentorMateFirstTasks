public class Task5DivisibleBy1To20 {
    public static void main(String[] args) {
        final int n = 500000000;

        System.out.println(smallestCorrectLowerThanN(n));
        System.out.println(largestCorrectLowerThanN(n));


    }

    static int smallestCorrectLowerThanN(int n) {
        int smallestCorrectNumber = 0;
        boolean isCorrect = true;

        for (int i = 2520; i <= n; i += 2) {
            for (int divisor = 1; divisor <= 20; divisor++) {
                if (i % divisor != 0) {
                    isCorrect = false;
                    break;
                }
                isCorrect = true;
            }
            if (isCorrect) {
                smallestCorrectNumber = i;
                break;
            }
        }
        return smallestCorrectNumber;
    }

    static int largestCorrectLowerThanN(int n) {
        int largestCorrectNumber = 0;
        boolean isCorrect = true;

        for (int i = n - 1; i >= 2520; i--) {
            for (int divisor = 1; divisor <= 20; divisor++) {
                if (i % divisor != 0) {
                    isCorrect = false;
                    break;
                }
                isCorrect = true;
            }
            if (isCorrect) {
                largestCorrectNumber = i;
                break;
            }
        }
        return largestCorrectNumber;
    }
}
