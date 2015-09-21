package NumberGame;

import java.util.Arrays;
import java.util.List;

public class GuessMyNumber {
    private int NumberToGuess;

    public GuessMyNumber(int key) {
        this.NumberToGuess = key;
    }

    /*
     * Oracle
     * @param your guess
     * @return -1 if the number to guess is smaller than your guess
     *             1 if the number to guess is bigger than your guess
     *             0 if your guess is correct
     */
    public int oracle(int guess) {
        if (this.NumberToGuess < guess) {
            return -1;
        } else if (NumberToGuess > guess) {
            return 1;
        } else {
            return 0;
        }
    }

    /*
     * Game - Guess a number
     * You have to guess which number the opponent chose from the given array.
     * To ask whether your guess is correct, run function oracle.
     * @param array Unique elements in random order (can be big)
     * @return guessed number
     */
    public int playGame(int[] array) {
        Arrays.sort(array);
        int length = array.length;
        int pivotIndex = length / 2;

//        if(length > 39000){
//            for (int i = 0; i < array.length; i++) {
//                if(oracle(array[i]) == 0) return oracle(array[i]);
//            }
//        }

        while (oracle(array[pivotIndex]) != 0) {
            if (oracle(array[pivotIndex]) == 1) {
                array = Arrays.copyOfRange(array, pivotIndex, length);
            } else if (oracle(array[pivotIndex]) == -1) {
                array = Arrays.copyOfRange(array, 0, pivotIndex);
            }

            length = array.length;
            pivotIndex = length / 2;
        }

        return array[pivotIndex];
    }
}
