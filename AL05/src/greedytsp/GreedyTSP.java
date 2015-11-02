package greedytsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mart on 2.11.15.
 */
public class GreedyTSP {
    static List<Integer> list = new ArrayList<>();

    /* Greedy search */
    public static int[] greedySolution(int[][] adjacencyMatrix) {
        int numberOfNodes;
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        int[] answer = new int[numberOfNodes + 2];

        visited[0] = 0;
        list.add(0);
        int element, dst = 0, i;
        int min;
        boolean minFlag = false;
        int j = 1;
        answer[0] = 0;

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
                visited[dst] = dst;
                list.add(dst);
                answer[j] = dst;
                j++;
                minFlag = false;
                continue;
            }
            list.remove(list.size() - 1);
        }

        answer[j] = 0;
        return answer;
    }

}


