package tsp;

import java.util.ArrayList;
import java.util.List;

public class TSP {
    private static int[] lowestValues;
    static int maximum = 0;
    static boolean firstBranch = true;

    public static int[] dfs(int[][] adjacencyMatrix) {
        int[] res = new int[adjacencyMatrix.length + 1];

        lowestValues = new int[adjacencyMatrix.length];
        //get every row min
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] < min && adjacencyMatrix[i][j] != 0) {
                    min = adjacencyMatrix[i][j];
                }
            }
            lowestValues[i] = min;
            maximum += min;

        }


        List<Node> nodes = shortPath(adjacencyMatrix, 0, 0);
        res[0] = 0;
        //System.out.println("result: ");
        int k = 1;
        for (Node node : nodes) {

            res[k] = node.getRowIndex();
            //System.out.println("col " + node.columnIndex + " row " +node.getRowIndex() + " len " +node.getValue());
            k++;
        }
        return res;
    }


    public static List<Node> shortPath(int[][] adjacencyMatrix, int start, int end) {
        int n = adjacencyMatrix.length;
        boolean[] visited = new boolean[n];
        visited[start] = true;
        int len = getLikelyLowestBound(adjacencyMatrix);
        Node first = new Node(adjacencyMatrix[0][0], 0, 0, null);
        return dfs(adjacencyMatrix, start, end, visited, 1, len, first);
    }

    private static List<Node> dfs(int[][] matrix, int start, int end, boolean[] visited, int count, int len, Node parent) {
        List<Node> arr = null;

        //recursive stop
        if (count == matrix.length) {
            firstBranch = false;
            //System.out.println("branch end: " + start + "->" + end + " len: " + matrix[start][end]);
            Node node = new Node(matrix[start][end], start, end, parent);
            //maximum = node.getLengthToTop();
            node.setRoadLength(matrix[start][end]);
            List<Node> nodes = new ArrayList<>();
            nodes.add(node);
            return nodes;
        }
        int n = matrix.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            List<Node> nodes;
            if (!visited[i]) {
                visited[i] = true;
                Node node = new Node(matrix[start][i], start, i, parent);

                //prune branches
                //int lenToTop = node.getLengthToTop();
                int bound = bound(start, matrix) + node.getValue();
                //System.out.println("add node: " + start + "->" + i + " len: " + matrix[start][i] + " bound: " + (bound - ((bound * 45) / 100)));
                boolean toPruneOrNotToPrune = maximum >= (bound - ((bound * 5) / 100)) || firstBranch;
                if (toPruneOrNotToPrune) {

                    nodes = dfs(matrix, i, 0, visited, count + 1, len, node);
                    parent.setChildren(nodes);
                    int dfs = nodes.get(nodes.size() - 1).getRoadLength();
                    int other = dfs + matrix[start][i];
                    if (other <= min) {
                        node.setRoadLength(other);
                        nodes.add(node);
                        min = other;

                        arr = nodes;

                    } else {
                        nodes.remove(nodes.size() - 1);
                    }
                } else {
                    System.out.println("pruning max: " + maximum + " bound: " + bound);

                }


                visited[i] = false;
            }
        }
        //System.out.println("dfs return min: " + min);

        return arr;
    }

    private static int bound(int start, int[][] matrix) {
        int res = 0;
        for (int i = start; i < matrix.length; i++) {
            res += lowestValues[i];
        }
        return res;
    }

    private static int getLikelyLowestBound(int[][] adjacencyMatrix) {
        int rows = 0;
        if (adjacencyMatrix.length < 10) {
            rows = adjacencyMatrix.length - 1;
        } else {
            rows = 10;
        }
        int len = 0;
        for (int i = 0; i < rows; i++) {

            int min = Integer.MAX_VALUE;
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] < min && adjacencyMatrix[i][j] != 0) {
                    min = adjacencyMatrix[i][j];
                }
            }
            len += min;
        }
        if (rows == 10) {
            len = (len / rows) * (adjacencyMatrix.length - 10); //tuuni

        }
        return len / (adjacencyMatrix.length - 1);
    }

    /* Best first search */
    public static int[] bfs(int[][] adjacencyMatrix) {
        return null;
    }

}