import java.util.LinkedList;
import java.util.List;

public class Task3NthPrimeNumber {

    public static void main(String[] args) {

        int wantedPrimeNumberPosition = 10001;
        List<Integer> allPrimes = allPrimeNumbersToWantedOne(wantedPrimeNumberPosition);
        int wantedNumber = allPrimes.get(allPrimes.size() - 1);
        System.out.println(wantedNumber);

    }

    static List<Integer> allPrimeNumbersToWantedOne(int wantedPrimeNumberPosition) {
        List<Integer> primeNumbers = new LinkedList<>();
        primeNumbers.add(2);
        int countOfFoundPrimeNumbers = 1;
        int currentNumber = 3;
        boolean isPrime = false;
        while (countOfFoundPrimeNumbers < wantedPrimeNumberPosition) {
            for (Integer primeNumber : primeNumbers) {
                if (currentNumber % primeNumber == 0) {
                    isPrime = false;
                    break;
                } else {
                    isPrime = true;
                }
            }
            if (isPrime) {
                countOfFoundPrimeNumbers++;
                primeNumbers.add(currentNumber);
                currentNumber++;
            } else {
                currentNumber++;
            }

        }
        return primeNumbers;
    }
}
