package greedytsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by mart on 2.11.15.
 */
public class GreedyTSP {

    /* Greedy search */
    public static int[] greedySolution(int[][] adjacencyMatrix) {
        List<Integer> resultList = new ArrayList<>();

        int numberOfNodes;
        Stack<Integer> stack = new Stack<>();

        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        int min;
        boolean minFlag = false;
        System.out.print(0 + "\t");
        resultList.add(0);

        while (!stack.isEmpty()) {
            element = stack.peek();
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
                stack.push(dst);
                System.out.print((dst - 1) + "\t");
                resultList.add(dst - 1);
                minFlag = false;
                continue;
            }
            stack.pop();
        }

        resultList.add(0);
        System.out.print(0 + "\t");
        return convertIntegers(resultList);
    }

    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next();
        }
        return ret;
    }
}


