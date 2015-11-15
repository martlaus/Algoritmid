package tsp;

import java.util.ArrayList;
import java.util.List;

public class TSP {

    public static int[] dfs(int[][] adjacencyMatrix) {
        int[] res = new int[adjacencyMatrix.length + 1];
        List<Node> nodes = shortPath(adjacencyMatrix, 0, 0);
        res[0] = 0;
        System.out.println("result: ");
        int i = 1;
        for (Node node : nodes) {

            res[i] = node.getRowIndex();
            //System.out.println("col " + node.columnIndex + " row " +node.getRowIndex() + " len " +node.getValue());
            i++;
        }
        return res;
    }

    // O(n!)
    public static List<Node> shortPath(int[][] d, int start, int end) {
        int n = d.length;
        boolean[] visited = new boolean[n];
        visited[start] = true;
        return dfs(d, 0, 0, visited, 1);

    }

    private static List<Node> dfs(int[][] d, int start, int end, boolean[] visited, int count) {
        List<Node> arr = null;
        if (count == d.length) {
            //System.out.println("branch end: " + start + "->" + end + " len: " + d[start][end]);
            Node node = new Node(d[start][end], start, end);
            node.setRoadLength(d[start][end]);
            List<Node> nodes = new ArrayList<>();
            nodes.add(node);
            return nodes;
        }
        int n = d.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            List<Node> nodes;
            if (!visited[i]) {
                visited[i] = true;
                Node node = new Node(d[start][i], start, i);
                //System.out.println("add node: " + start + "->" + i + " len: " + d[start][i]);
                //stack.push(node);
                nodes = dfs(d, i, 0, visited, count + 1);
                int dfs = nodes.get(nodes.size() - 1).getRoadLength();
                int other = dfs + d[start][i];
                if (other <= min) {
                    node.setRoadLength(other);
                    nodes.add(node);
                    //stack.pop();
                    min = other;

                    arr = nodes;

                } else {
                    nodes.remove(nodes.size() - 1);
                }
                visited[i] = false;
            }
        }
        // System.out.println("dfs return min: " + min);

        return arr;
    }


    /* Best first search */
    public static int[] bfs(int[][] adjacencyMatrix) {
        return null;
    }

}