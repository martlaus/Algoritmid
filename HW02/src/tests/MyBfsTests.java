package tests;

import org.junit.Test;
import tsp.TSP;

import static org.junit.Assert.assertEquals;

/**
 * Created by mart on 22.11.15.
 */
public class MyBfsTests {

    @Test
    public void small() {

        int[][] arr = {{0, 6, 7, 9},
                {8, 0, 9, 4},
                {5, 4, 0, 3},
                {9, 6, 7, 0}};

        int[] res = TSP.bfs(arr);
        int len = 0;
        for (int i = 0; i < res.length - 1; i++) {
            len += arr[res[i]][res[i + 1]];
        }
        System.out.println("pikkus " + len);
        assertEquals(22, len);

    }
}
