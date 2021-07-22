import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        // Prompts the user to type in text and a shift key to encode
        System.out.println(
                "Type in the text you would like encoded in the Caesar Cipher. Only alphabet characters are encoded");
        String text = scan.nextLine();
        System.out.println("Type in the # of shifts you would like to make");
        int shift = scan.nextInt();
        scan.close();

        // String variable to be used later to form the encoded string
        String string = "";

        // Makes clear to the user that the following will be their encoded message
        System.out.println("Here is your encoded message:");

        // This whole piece figures out where on ASCII the letters belong to one by one
        // It splits them into capitals and lowercase letters through their ASCII values
        // It uses modulo to shift appropriately and then converts it back into a
        // character
        // Then it collects the characters to form a string then prints it
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            int ascii = letter;
            if (ascii >= 65 && ascii < 91) {
                int encode = Math.floorMod(ascii - 65 + shift, 26) + 65;
                char placeholder = (char) encode;
                string += placeholder;
            } else if (ascii >= 97 && ascii < 123) {
                int encode = Math.floorMod(ascii - 97 + shift, 26) + 97;
                char placeholder = (char) encode;
                string += placeholder;
            } else {// Adds spaces and special characters without encoding
                string += letter;
            }
        }

        // Prints out the result of the encoding
        System.out.println(string);
    }
}
