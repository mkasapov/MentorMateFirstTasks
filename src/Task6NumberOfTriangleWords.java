import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Task6NumberOfTriangleWords {


    public static void main(String[] args) {

        File file = new File("C:\\WorkFiles\\words.txt");
        List<String> allWords = readAllWords(file);
        List<Integer> wordsToNumbers = turnWordsIntoNumbers(allWords);
        int result = countOfTriangleWords(wordsToNumbers);
        System.out.println(result);

    }

    static int findNthTriangleNumber(int n) {
        return (int) (0.5 * (n) * (n + 1));
    }

    static List<String> readAllWords(File fileName) {

        String word;
        List<String> words = new LinkedList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((word = reader.readLine()) != null) {
                words.add(word);
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File with the given name does not exist: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Reading from this file was interrupted or closing operation failed: " + e.getMessage());
        }

        return words;

    }

    static List<Integer> turnWordsIntoNumbers(List<String> words) {
        char currentChar;
        List<Integer> wordNumbers = new ArrayList<>();
        int wordNumber;
        for (String word : words) {
            wordNumber = 0;
            for (int i = 0; i < word.length(); i++) {
                currentChar = word.charAt(i);
                int charAscii = currentChar;
                wordNumber += charAscii;
            }
            wordNumbers.add(wordNumber);

        }
        return wordNumbers;
    }

    static boolean checkIfTriangleNumberWord(int wordIntoNumber) {

        int n = 0;
        while (wordIntoNumber > findNthTriangleNumber(n)) {
            n++;
        }
        if (wordIntoNumber == findNthTriangleNumber(n))
            return true;
        return false;

    }

    static int countOfTriangleWords(List<Integer> wordsNumbers) {
        int countOfTriangleWords = 0;
        for (Integer number : wordsNumbers) {
            if (checkIfTriangleNumberWord(number))
                countOfTriangleWords++;
        }
        return countOfTriangleWords;
    }
}
