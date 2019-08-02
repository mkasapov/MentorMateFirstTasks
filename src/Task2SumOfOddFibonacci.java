public class Task2SumOfOddFibonacci {

    public static void main(String[] args) {

        int n = 2000000;
        System.out.println(getSumOfOddTerms(n));

    }

    static int getFibonacci(int n) {
        if (n == 0 || n == 1)
            return 1;

        int first = 0;
        int second = 1;
        int nth = 1;

        for (int i = 2; i <= n; i++) {
            nth = first + second;
            first = second;
            second = nth;
        }
        return nth;
    }

    static int getSumOfOddTerms(int n) {
        int sumOfOddValues = 0;
        int i = 0;
        while (getFibonacci(i) <= n) {
            int currentFibonacci = getFibonacci(i);
            if (currentFibonacci % 2 == 0) {
                sumOfOddValues += currentFibonacci;
            }
            i++;
        }
        return sumOfOddValues;
    }
}
