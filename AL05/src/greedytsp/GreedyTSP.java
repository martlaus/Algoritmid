package greedytsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mart on 2.11.15.
 */
public class GreedyTSP {

    private static int numberOfNodes;
    private static List<Integer> list = new ArrayList<Integer>();

    /* Greedy search */
    public static int[] greedySolution(int[][] adjacencyMatrix) {

        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        list.add(0);
        int element;
        int dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;

        while (!list.isEmpty()) {
            element = list.get(0);

            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes) {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0) {
                    if (min > adjacencyMatrix[element][i]) {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag) {
                visited[dst] = 1;
                list.add(dst);
                minFlag = false;
                continue;
            }
            list.remove(list.size() - 1);
        }


        return visited;
    }
}


