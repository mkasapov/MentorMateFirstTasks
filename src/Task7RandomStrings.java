import java.util.*;

public class Task7RandomStrings {
    public static void main(String[] args) {

        int numberOfWords = 2;

        // N = ?
        int wordLength = 50000;

        //Print the generated random Words
        String[] words = generateRandomWords(numberOfWords, wordLength);
        String firstWord = words[0];
        String secondWord = words[1];
        Arrays.stream(words).forEach(System.out::println);


//        FIRST TASK - Check if the words have letter 'g'

//        System.out.printf("Number of words to have letter 'g' = %d:%n",wordsWithGivenChar(words,'g').length);
//        Arrays.stream(wordsWithGivenChar(words,'g')).forEach(w -> System.out.println(w));


//        SECOND TASK - Print all characters that appear in both words, N=20
//
//        Set<Character> matchingChars = getMatchingLetters(firstWord,secondWord);
//        for (char matchingChar : matchingChars) {
//            System.out.println(matchingChar);
//        }

        //THIRD TASK - Calculate execution time and find longest substrings and their starting indices

        long startTime = System.nanoTime();
        List<String> longestNotFilteredStrings = longestSubstrings(firstWord, secondWord);
        List<String> filteredAndSortedStrings = finalSortedAndFilteredList(longestNotFilteredStrings);
        System.out.println("Longest common substrings:");
        filteredAndSortedStrings.forEach(System.out::println);

        List<Integer> indicesOfFirstWordSubstrings = indicesOfAllSubstrings(firstWord, filteredAndSortedStrings);
        List<Integer> indicesOfSecondWordSubstrings = indicesOfAllSubstrings(secondWord, filteredAndSortedStrings);

        System.out.println();
        System.out.println("Indices of longest substrings in the first word:");
        indicesOfFirstWordSubstrings.forEach(System.out::println);
        System.out.println();
        System.out.println("Indices of longest substrings in the second word:");
        indicesOfSecondWordSubstrings.forEach(System.out::println);


        long endTime = System.nanoTime();
        double durationInMillis = (endTime - startTime) / 1000000;
        System.out.println();
        System.out.printf("Computation time in seconds: %.2f\n", durationInMillis / 1000);

    }

    static String[] generateRandomWords(int numberOfWords, int wordLength) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[wordLength]; // word length
            //select random number of chars from a word to be turned to upper case
            int randomNumberOfUppercaseLetters = random.nextInt(wordLength);
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) (('a' + random.nextInt(26)));
            }
            int randomIndexToUpper;
            //select random indices of chars and if they are to lower turn them to upper
            for (int index = 0; index < randomNumberOfUppercaseLetters; index++) {
                randomIndexToUpper = random.nextInt(wordLength);
                if ((int) word[randomIndexToUpper] > 96) {
                    word[randomIndexToUpper] = (char) (word[randomIndexToUpper] - 32);
                }
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    static boolean doesWordContainGivenChar(String word, char letter) {

        if (word.indexOf(letter) >= 0)
            return true;
        return false;
    }

    static String[] wordsWithGivenChar(String[] words, char letter) {

        String[] filteredStringArr = Arrays.stream(words).filter(w -> w.toLowerCase().indexOf(letter) >= 0).toArray(String[]::new);
        return filteredStringArr;
    }

    static Set<Character> getMatchingLetters(String word, String otherWord) {
        Set<Character> matchingChars = new HashSet<>();
        char[] firstWordChars = word.toCharArray();
        for (char letter : firstWordChars) {
            if (doesWordContainGivenChar(otherWord, letter)) {
                matchingChars.add(letter);
            }
        }
        return matchingChars;
    }

    static List<String> longestSubstrings(String firstStr, String secondStr) {
        List<String> longestSubstrings = new ArrayList<>();

        int currentMaxLength = 0;
        StringBuilder currentStr = new StringBuilder();

        int i = 0;
        int currentStartIndex = 0;
        while (i < firstStr.length()) {

            currentStr.append(firstStr.charAt(i));

            if (secondStr.contains(currentStr.toString().toString())) {
                i++;
            } else {
                if (currentStr.length() > 1 && currentStr.length() - 1 >= currentMaxLength) {
                    longestSubstrings.add(currentStr.toString().substring(0, currentStr.length() - 1));
                    currentMaxLength = currentStr.length() - 1;
                }
                currentStr.setLength(0);
                currentStartIndex++;
                i = currentStartIndex;
            }
        }
        longestSubstrings.add(currentStr.toString());
        System.out.println();
        return longestSubstrings;

    }

    static List<String> finalSortedAndFilteredList(List<String> notSortedList) {
        notSortedList.sort(Comparator.comparing(String::length));
        List<String> finalListWithLongestStrings = new ArrayList<>();
        for (String str : notSortedList) {
            String oneLongestStr = notSortedList.get(notSortedList.size() - 1);
            int lengthOfLongestStr = oneLongestStr.length();
            if (str.length() == lengthOfLongestStr) {
                finalListWithLongestStrings.add(str);
            }
        }
        Collections.sort(finalListWithLongestStrings);
        return finalListWithLongestStrings;
    }

    static int startIndexOfSubstring(String word, String subStr) {
        return word.indexOf(subStr);
    }

    static List<Integer> indicesOfAllSubstrings(String word, List<String> substrings) {
        List<Integer> indices = new ArrayList<>();
        for (String substring : substrings) {
            indices.add(startIndexOfSubstring(word, substring));
        }
        return indices;
    }

}
