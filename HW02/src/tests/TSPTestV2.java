package tsp;

import org.junit.Test;
import tests.TestData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TSPTestV2 {

    @Test
    public void testBfsSmall() throws Exception {
        long startTime = System.currentTimeMillis();

        int[] res = TSP.bfs(TestData.smallMatrix);
        int len = getLen(res, TestData.smallMatrix);

        System.out.println("Result distance should be 405 " + len);
        assertEquals(405, len);

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testBfsSmall took " + duration + " milliseconds");
    }

    @Test
    public void testBfsMedium() throws Exception {
        long startTime = System.currentTimeMillis();

        int[] res = TSP.bfs(TestData.mediumMatrix);

        int len = getLen(res, TestData.mediumMatrix);

        System.out.println("pikkus väiksem kui 772 " + len);
        assertTrue(len <= 772);

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testBfsMedium took " + duration + " milliseconds");
    }

    @Test
    public void testBfsBig() throws Exception {
        long startTime = System.currentTimeMillis();

        int[] res = TSP.bfs(TestData.bigMatrix);

        int len = getLen(res, TestData.bigMatrix);
        System.out.println("pikkus väiksem kui 893 " + len);
        assertTrue(len <= 893);

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testBfsBig took " + duration + " milliseconds");
    }

    @Test
    public void testDfsSmall() throws Exception {
        long startTime = System.currentTimeMillis();

        int[] res = TSP.dfs(TestData.smallMatrix);

        int len = getLen(res, TestData.smallMatrix);

        System.out.println("pikkus väiksem kui 405 " + len);
        assertTrue(len <= 405);

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testDfsSmall took " + duration + " milliseconds");
    }


    @Test
    public void testDfsMedium() throws Exception {
        long startTime = System.currentTimeMillis();

        int[] res = TSP.dfs(TestData.mediumMatrix);

        int len = getLen(res, TestData.mediumMatrix);

        System.out.println("pikkus väiksem kui 772 " + len);
        assertTrue(len <= 772);

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testDfsMedium took " + duration + " milliseconds");
    }


    @Test
    public void testDfsBig() throws Exception {
        long startTime = System.currentTimeMillis();

        int[] res = TSP.dfs(TestData.bigMatrix);

        int len = getLen(res, TestData.bigMatrix);
        System.out.println("pikkus väiksem kui 893 " + len);
        assertTrue(len <= 893);

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("testDfsBig took " + duration + " milliseconds");
    }

    private int getLen(int[] res, int[][] matrix) {
        int len = 0;
        for (int i = 0; i < res.length - 1; i++) {
            len += matrix[res[i]][res[i + 1]];
        }
        return len;
    }
}