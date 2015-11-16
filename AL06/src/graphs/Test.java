package graphs;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by mart on 16.11.15.
 */
public class Test {

    @org.junit.Test
    public void small() {
        int[][] matrix = {{0,1,1,0},
                        {1,0,1,1},
                        {1,1,0,1},
                        {0,1,1,0}};
        int[] pair = {0,3};
        Friends.bfs(matrix, pair);
        int[] ans = {0,1,3};
        assertArrayEquals(ans, Friends.getConnectionList());
    }
}
