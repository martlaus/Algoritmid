package tsp;

import java.util.ArrayList;
import java.util.List;

public class TSP {
    static int maximum = Integer.MAX_VALUE;
    private static int[] lowestValues;

    public static int[] dfs(int[][] adjacencyMatrix) {
        maximum = Integer.MAX_VALUE;

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
        }

        List<Node> nodes = shortPath(adjacencyMatrix, 0, 0);
        res[0] = 0;
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
        Node first = new Node(adjacencyMatrix[0][0], 0, 0, null);
        return dfs(adjacencyMatrix, start, end, visited, 1, first);
    }

    private static List<Node> dfs(int[][] matrix, int start, int end, boolean[] visited, int count, Node parent) {
        List<Node> arr = null;

        //recursive stop
        if (count == matrix.length) {
            //System.out.println("branch end: " + start + "->" + end + " len: " + matrix[start][end]);
            Node node = new Node(matrix[start][end], start, end, parent);

//            System.out.print("Path: ");
//            node.printPath();
//            System.out.println("");

            if (node.getLengthToTop() < maximum) {
                maximum = node.getLengthToTop();
            }
            node.setRoadLength(matrix[start][end]);
            List<Node> nodes = new ArrayList<>();
            nodes.add(node);
            return nodes;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            List<Node> nodes;
            if (!visited[i]) {
                visited[i] = true;
                Node node = new Node(matrix[start][i], start, i, parent);

//                System.out.print("Path: ");
//                node.printPath();
//                System.out.println("");

                //prune branches
                int calculatedBound = bound(node.getVisitedRows()) + node.getLengthToTop();
                //System.out.println("add node: " + start + "->" + i + " len: " + matrix[start][i] + " bound: " + (bound - ((bound * 45) / 100)));
                if (calculatedBound <= maximum) {
                    nodes = dfs(matrix, i, 0, visited, count + 1, node);
                    if (nodes != null && nodes.size() != 0) {
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
                    }
                } else {
                    //System.out.println("pruning max: " + maximum + " calcbound: " + calculatedBound + " tippu " + node.getLengthToTop() + " bound " + bound(node.getVisitedRows()));
                }

                visited[i] = false;
            }
        }
        //System.out.println("dfs return min: " + min);

        return arr;
    }

    private static int bound(List<Integer> visited) {
        int res = 0;
        for (int i = 0; i < lowestValues.length; i++) {
            if (!visited.contains(i)) {
                res += lowestValues[i];
            }
        }
        return res;
    }

    /* Best first search */
    public static int[] bfs(int[][] adjacencyMatrix) {


        return null;
    }

}