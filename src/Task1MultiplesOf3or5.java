public class Task1MultiplesOf3or5 {

    public static void main(String[] args) {

        int limit = 1000;

        System.out.println(sumOfDivisibleBy3or5(limit));
    }

    static int sumOfDivisibleBy3or5(int limit) {
        int sumOfDivisibleBy3Or5 = 0;

        for (int i = 0; i < limit; i++) {
            if (i % 3 == 0 || i % 5 == 0)
                sumOfDivisibleBy3Or5 += i;
        }
        return sumOfDivisibleBy3Or5;
    }
}
