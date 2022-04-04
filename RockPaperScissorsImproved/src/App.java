import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Prints the text to prompt the user to pick an option
        System.out.println(
                "Choose rock paper or scissors.  'r' for rock, 'p' for paper and 's' for scissors. To exit choose 'x'");

        // Keeps track of the wins and losses
        int wins = 0;
        int losses = 0;
        Scanner scan = new Scanner(System.in);

        while (true) {

            // Prints information such as wins/losses, a border, and prompts the player for
            // their selection
            System.out.println("**********************************************");
            System.out.println("Wins: " + wins + "   Losses: " + losses);
            System.out.println("Players Choice:");

            // Gives the user the ability to pick an option
            String choice = scan.next();

            // Randomly generates an option for the computer by using the random function
            int random = (int) (Math.random() * 3);
            String computerChoice = "";

            final String rock = "rock";
            final String paper = "paper";
            final String scissors = "scissors";
            final String winMsg = "You Win!";

            // Translate the user input into either rock paper or scissors to make the
            // result understandable and turns it into a form shared with the computer
            if (choice.equals("r")) {
                choice = rock;
            } else if (choice.equals("p")) {
                choice = paper;
            } else if (choice.equals("s")) {
                choice = scissors;
            } else if (choice.equals("x")) {
                System.out.println("Thank you for playing!");
                break;
            } else { // user inputs something invalid
                System.out.println("Invalid selection please play again.");
                break;
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
                wins++;
            } else if (computerChoice.equals(paper) && choice.equals(scissors)) {
                System.out.println(winMsg);
                wins++;
            } else if (computerChoice.equals(scissors) && choice.equals(rock)) {
                System.out.println(winMsg);
                wins++;
            } else { // anything that isn't a tie or a win is a loss
                System.out.println("You Lose!");
                losses++;
            }

            // This prints out the user selected option and the computer's randomly
            // generated option
            System.out.println("Computer Choice: " + computerChoice + "  Player choice: " + choice);

        }
        scan.close();
    }
}
