import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Prints the text to prompt the user to pick an option
        System.out.println("Choose rock paper or scissors.  'r' for rock, 'p' for paper and 's' for scissors.");

        // Gives the user the ability to pick an option
        Scanner scan = new Scanner(System.in);
        String choice = scan.next();
        scan.close();

        // Randomly generates an option for the computer by using the random function
        int random = (int) (Math.random() * 3);
        String computerChoice = "";

        // Variables for rock paper and scissors
        String rock = "rock";
        String paper = "paper";
        String scissors = "scissors";
        String winMsg = "You Win!";

        // Translate the user input into either rock paper or scissors to make the
        // result understandable and turns it into a form shared with the computer
        if (choice.equals("r")) {
            choice = rock;
        } else if (choice.equals("p")) {
            choice = paper;
        } else if (choice.equals("s")) {
            choice = scissors;
        } else { // user inputs something invalid
            System.out.println("Invalid selection please play again.");
            return;
        }

        // Translates the randomly generated numbers 0-2 to turn it into a common form
        if (random == 0) {
            computerChoice = rock;
        } else if (random == 1) {
            computerChoice = paper;
        } else if (random == 2) {
            computerChoice = scissors;
        }

        // Represents all the possible outcomes of the rock paper scissors game
        if (computerChoice == choice) {
            System.out.println("Draw!");
        } else if (computerChoice.equals(rock) && choice.equals(paper)) {
            System.out.println(winMsg);
        } else if (computerChoice.equals(paper) && choice.equals(scissors)) {
            System.out.println(winMsg);
        } else if (computerChoice.equals(scissors) && choice.equals(rock)) {
            System.out.println(winMsg);
        } else { // anything that isn't a tie or a win is a loss
            System.out.println("You Lose!");
        }

        // This prints out the user selected option and the computer's randomly
        // generated option
        System.out.println("Computer Choice: " + computerChoice + "  Player choice: " + choice);
    }
}
