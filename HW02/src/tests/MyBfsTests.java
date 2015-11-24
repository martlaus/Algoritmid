package tests;

import org.junit.Test;
import tsp.TSP;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void twoCities() {

        int[][] arr = {{0, 5},
                {9, 0}};

        int[] res = TSP.bfs(arr);
        int len = 0;
        for (int i = 0; i < res.length - 1; i++) {
            len += arr[res[i]][res[i + 1]];
        }
        System.out.println("pikkus " + len);
        assertEquals(14, len);

    }

    @Test
    public void twoCities2() {

        int[][] arr = {{0, 9},
                {5, 0}};

        int[] res = TSP.bfs(arr);
        int len = 0;
        for (int i = 0; i < res.length - 1; i++) {
            len += arr[res[i]][res[i + 1]];
        }
        System.out.println("pikkus " + len);
        assertEquals(14, len);

    }

    @Test
    public void eesti10() { //730

        int[][] arr = {{0, 128, 71, 85, 185, 68, 257, 232, 193, 199},
                {128, 0, 91, 62, 58, 81, 161, 108, 151, 97},
                {71, 91, 0, 29, 149, 87, 252, 199, 212, 143},
                {85, 62, 29, 0, 120, 70, 223, 170, 195, 114},
                {185, 58, 149, 120, 0, 117, 107, 50, 98, 85},
                {68, 81, 87, 70, 117, 0, 189, 164, 126, 178},
                {257, 161, 252, 223, 107, 189, 0, 48, 99, 129},
                {232, 108, 199, 170, 50, 164, 48, 0, 148, 84},
                {193, 151, 212, 195, 98, 126, 99, 148, 0, 183},
                {199, 97, 143, 114, 85, 178, 129, 84, 183, 0}};

        int[] res = TSP.bfs(arr);
        int[] expect = new int[]{0, 2, 3, 1, 4, 9, 7, 6, 8, 5, 0};

        int len = 0;
        for (int i = 0; i < res.length - 1; i++) {
            len += arr[res[i]][res[i + 1]];
        }
        System.out.println("pikkus väiksem kui 730 " + len);
        assertTrue(len <= 730);
    }
}
