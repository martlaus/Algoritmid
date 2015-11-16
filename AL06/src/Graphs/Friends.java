package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by mart on 16.11.15.
 */
public class Friends {
    private static ArrayList<Integer> shortestPath;

    public static void bfs(int[][] adjacencyMatrix, int[] pair) {
        Queue<ArrayList<Integer>> queue = new LinkedList<>();

        ArrayList<Integer> start = new ArrayList<>();

        start.add(pair[0]);

        queue.add(start);

        while (!queue.isEmpty()) {
            ArrayList<Integer> node = queue.remove();

            for (ArrayList<Integer> child : getNodesSubNodes(adjacencyMatrix, node)) {
                if (child.get(0) == pair[0] && child.get(child.size() - 1) == pair[1]) {
                    shortestPath = child;
                    return;
                }

                queue.add(child);
            }
        }
    }

    public static int getDistance() {
        if (getShortestPath() != null) {

            return getShortestPath().length - 1;
        }
        return 0;
    }

    public static int[] getShortestPath() {
        if (shortestPath == null)
            return null;

        return shortestPath.stream().filter(i -> i != null).mapToInt(i -> i).toArray();
    }

    public static ArrayList<ArrayList<Integer>> getNodesSubNodes(int[][] matrix, List<Integer> node) {
        int lastFriend = node.get(node.size() - 1);
        int[] friends = matrix[lastFriend];

        ArrayList<ArrayList<Integer>> children = new ArrayList<>();

        for (int i = 0; i < friends.length; i++) {
            if (friends[i] == 1 && !node.contains(i)) {
                ArrayList<Integer> newNode = new ArrayList<>(node);
                newNode.add(i);

                children.add(newNode);
            }
        }

        return children;
    }

}
