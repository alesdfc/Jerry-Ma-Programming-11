import java.util.Arrays;

public class Main {
    // Method to print the number of vowels in a string
    static int problemOne(String s) {

        // Variables to store the amount of vowels and the length of the string
        int answer = 0;
        int length = s.length();

        // Checks every character in the string and sees if its a vowel, if it is it
        // will increment the answer by 1
        for (int i = 0; i < length; i++) {
            char character = s.charAt(i);

            // Checks to see if the current character is a vowel (not including 'y' when it
            // is a vowel)
            if (character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u') {
                answer++;
            }
        }
        System.out.println("Number of vowels: " + answer);
        return answer;
    }

    // Method to print the amount of times "bob" shows up in a string
    static int problemTwo(String s) {

        // Variables to store the amount of bobs and the length of the string
        int answer = 0;
        int length = s.length();

        // Checks every character to see if it is "b", if it is, it checks the next 2
        // letters to see if it is "o" and "b". If it is, it increments the answer by 1.
        for (int i = 0; i < length - 2; i++) {
            if (s.charAt(i) == 'b') {
                if (s.charAt(i + 1) == 'o') {
                    if (s.charAt(i + 2) == 'b') {
                        answer++;
                    }
                }
            }
        }
        System.out.println("Number of times bob occurs is: " + answer);
        return answer;
    }

    // Finds the largest alphabetically increasing substring in a string
    static String problemThree(String s) {

        // Variables to compare, find the starting letter of a substring, and to get the
        // length of the string
        int length = s.length();
        int startIndex = 0;
        String longestSubstring = "";
        String temp = "";

        // For loop to iterate through the string
        for (int i = 1; i < length; i++) {

            // If the current letter comes next in alphabetical order to the previous letter
            if (s.charAt(i) >= s.charAt(i - 1)) {

                // If the current letter is the end of the string, make it a substring
                if (i == length - 1) {
                    temp = s.substring(startIndex, i + 1);

                    // If the length of an old string is smaller, then set the larger substring as
                    // the smaller
                    if (longestSubstring.length() < temp.length()) {
                        longestSubstring = temp;
                    }
                }
            }

            else { // Previous letter is not in alphabetical order

                // Variables to set the value of the ending index and set the value of temp to
                // compare lengths
                temp = s.substring(startIndex, i);

                // If the length of an old string is smaller, then set the larger substring as
                // the smaller
                if (longestSubstring.length() < temp.length()) {
                    longestSubstring = temp;
                }
                startIndex = i;
            }
        }
        System.out.println("Longest substring in alphabetical order is: " + longestSubstring);
        return longestSubstring;
    }
}
