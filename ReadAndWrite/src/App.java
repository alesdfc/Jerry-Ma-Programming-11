import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class App {

    // Requires: none
    // Modifies: none
    // Effects: Reads lines from document and adds it into an ArrayList. Then asks
    // user for input and calls for searchWord method
    public static void main(String[] args) throws Exception {
        // Read lines from text file
        ArrayList<String> text = new ArrayList<>();
        FileReader fr = new FileReader("ProgrammingHistory.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            text.add(line);
        }
        br.close();

        // Asks user for input
        Scanner scan = new Scanner(System.in);
        System.out.println("What word do you want to search?");
        String word = scan.nextLine();
        scan.close();

        searchWord(text, word);
    }

    // Requires: ArrayList<String>
    // Modifies: none
    // Effects: Searches ArrayList for the inputted word
    public static void searchWord(ArrayList<String> text, String word) {
        for (int i = 0; i < text.size(); i++) {
            if (text.get(i).contains(word)) {
                System.out.println(word + " appears at index: " + i);
            }
        }
    }
}
