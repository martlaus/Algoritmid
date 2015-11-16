package Graphs;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by mart on 16.11.15.
 */
public class Test {
    public static final int[][] INTS = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0}
    };

    @org.junit.Test
    public void small() {
        int[][] matrix = {{0, 1, 1, 0},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 1, 0}};
        int[] pair = {0, 3};
        Friends.bfs(matrix, pair);
        int[] ans = {0, 1, 3};
        assertArrayEquals(ans, Friends.getConnectionList());
    }


    @org.junit.Test
    public void testBfs() throws Exception {
        Friends.bfs(INTS, new int[]{0, 4});

        assertArrayEquals(new int[]{0, 1, 2, 4}, Friends.getConnectionList());
        assertEquals(3, Friends.getDistance());
    }
}
