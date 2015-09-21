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
        boolean sorted = false;
        int length = array.length;
        if(length > 100)
        for (int i = 0; i < 100; i++) {
            if(array[i] < array[i+1]) sorted = true;
        }
        if(!sorted){
            Arrays.sort(array);
        }
        int pivotIndex = length / 2;

        while (oracle(array[pivotIndex]) != 0) {
            if (oracle(array[pivotIndex]) == 1) {
                pivotIndex = pivotIndex + 1;

            } else if (oracle(array[pivotIndex]) == -1) {
                pivotIndex = pivotIndex - 1;
            }

        }

        return array[pivotIndex];
    }
}
