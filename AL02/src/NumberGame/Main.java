package NumberGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mart on 21.09.15.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting game...");

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 40000; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        list.toArray(new Integer[list.size()]);

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }


        GuessMyNumber guessMyNumber = new GuessMyNumber(37856);
        System.out.println(guessMyNumber.playGame(array));
    }


//        if (oracle(array[pivotIndex]) == 0) {
//            return array[pivotIndex];
//        } else if (oracle(array[pivotIndex]) == 1) {
//            playGame(Arrays.copyOfRange(array, pivotIndex, length));
//        } else if (oracle(array[pivotIndex]) == -1) {
//            playGame(Arrays.copyOfRange(array, 0, pivotIndex));
//        }

}
