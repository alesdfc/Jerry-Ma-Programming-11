import java.util.Arrays;

public class Array {

    // This method deletes the last index of an array
    static int[] deleteBack(int[] array) {

        // Stores the length of the array
        int size = array.length;

        // Creates a new array that is 1 smaller
        int[] y = new int[size - 1];

        // Copies over the values from the old array into the new array
        for (int i = 0; i < size - 1; i++) {
            y[i] = array[i];
        }

        // Gives back the result
        return y;
    }

    // This method adds a value to the end of an array
    static int[] add(int[] array, int value) {

        // Gets the size of the array
        int size = array.length;

        // Creates a new array that is 1 larger
        int[] changedArray = new int[size + 1];

        // Copies over the old array's values
        for (int i = 0; i < size; i++) {
            changedArray[i] = array[i];
        }

        // Inputs the wanted value into the newly added index
        changedArray[size] = value;

        // Gives back the result
        return changedArray;
    }

    // This method inserts a value between 2 values inside an array
    static int[] insert(int[] array, int value, int index) {

        // Gets the size of the array
        int size = array.length;

        // Creates a new array that is 1 larger
        int[] changedArray = new int[size + 1];
        if (index >= 0 && index < size + 1) {

            // Copies over the old array's values
            for (int i = 0; i < index; i++) {
                changedArray[i] = array[i];
            }

            // Inserts the value into the array
            changedArray[index] = value;
            for (int i = index; i < size; i++) {
                changedArray[i + 1] = array[i];
            }

            // Returns the array with the inserted value
            return changedArray;
        } else {
            return array;
        }
    }

    public static void main(String[] args) throws Exception {

        System.out.println(Arrays.toString(deleteBack(new int[] { 1, 2, 3, 4, 5 })));
        System.out.println(Arrays.toString(add(new int[] { 1, 2, 3, 4, 5 }, 6)));
        System.out.println(Arrays.toString(insert(new int[] { 1, 2, 3, 4, 5 }, 6, -1)));
    }
}
