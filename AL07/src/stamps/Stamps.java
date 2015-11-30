package stamps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by mart on 30.11.15.
 */
public class Stamps {

    public ArrayList<Integer> findstamps(int sum, int[] stamps) {
        int[] m = new int[sum+1];
        int[] v = new int[sum+1];


        Arrays.sort(stamps);

        for (int i = 0; i < sum+1; i++) {
            m[i] = Integer.MAX_VALUE;

            for (Integer j : stamps) {
                if ((i >= j) && (m[i] > m[i - j] + 1)) {
                    m[i] = m[i - j] + 1;
                    v[i] = j;
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        int n = sum;
        while (n > 0) {
            System.out.print(v[n] + ",");
            res.add(v[n]);
            n = n - v[n];
        }

        return res;
    }


}
