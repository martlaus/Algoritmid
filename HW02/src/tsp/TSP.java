package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TSP {
    private static int best = Integer.MAX_VALUE;
    private static int[] lowestValues;

    public static int[] dfs(int[][] adjacencyMatrix) {
        if (adjacencyMatrix.length > 9) {
            best = greedy(adjacencyMatrix);
        }

        int[] res = new int[adjacencyMatrix.length + 1];
        lowestValues = new int[adjacencyMatrix.length];
        //get every row min
        getEveryRowMin(adjacencyMatrix);

        List<Node> nodes = shortPath(adjacencyMatrix);
        int k = 0;
        for (int i = nodes.size() - 1; i >= 0; i--) {
            Node node = nodes.get(i);
            res[k] = node.getRowIndex();
            k++;
        }
        res[res.length - 1] = 0;

        return res;
    }

    private static void getEveryRowMin(int[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] < min && adjacencyMatrix[i][j] != 0) {
                    min = adjacencyMatrix[i][j];
                }
            }
            lowestValues[i] = min;
        }
    }

    private static List<Node> shortPath(int[][] adjacencyMatrix) {
        int n = adjacencyMatrix.length;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        Node first = new Node(adjacencyMatrix[0][0], 0, 0, null);
        return dfs(adjacencyMatrix, 0, 0, visited, 1, first);
    }

    private static List<Node> dfs(int[][] matrix, int start, int end, boolean[] visited, int count, Node parent) {
        List<Node> arr = null;

        //recursive stop
        if (count == matrix.length) {
            Node node = new Node(matrix[start][end], start, end, parent);
            //printPath(node);

            if (node.getRoadLength() < best) {
                best = node.getRoadLength();
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

                //printPath(node);

                //prune branches
                int calculatedBound = bound(node.getVisitedRows()) + node.getRoadLength();
                if (calculatedBound <= best) {
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
                }

                visited[i] = false;
            }
        }

        return arr;
    }

    private static void printPath(Node node) {
        System.out.print("Path: ");
        node.printPath();
        System.out.println("");
    }

    static int bound(List<Integer> visited) {
        int res = 0;
        for (int i = 0; i < lowestValues.length; i++) {
            if (visited == null || !visited.contains(i)) {
                res += lowestValues[i];
            }
        }
        return res;
    }

    /* Best first search */
    public static int[] bfs(int[][] adjacencyMatrix) {
        //setup
        if (adjacencyMatrix.length > 9) {
            best = greedy(adjacencyMatrix);
        }
        lowestValues = new int[adjacencyMatrix.length];
        Node winner = null;
        getEveryRowMin(adjacencyMatrix);

        //algorithm
        Queue<Node> queue = new PriorityQueue<>(10, (node1, node2) -> {
            int i1 = node1.getBound();
            int i2 = node2.getBound();
            if (i1 < i2) {
                return -1;
            } else if (i1 > i2) {
                return 1;
            } else return 0;
        });
        queue.add(new Node(adjacencyMatrix[0][0], 0, 0, null));

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.getBound() <= best) {
                for (Node child : getNodesSubNodes(adjacencyMatrix, node)) {
                    if (child.getParents().size() == (adjacencyMatrix.length - 1)) { // solution found
                        int col = child.getColumnIndex();
                        Node lastNode = new Node(adjacencyMatrix[col][0], col, 0, child);
                        if (lastNode.getRoadLength() <= best) {
                            best = lastNode.getRoadLength();
                            winner = lastNode;
                        }
                    }

                    if (child.getBound() <= best) { // promising child
                        queue.add(child);
                    }
                }
            }
        }
        List<Integer> res;
        if (winner != null) {
            res = winner.getParents();
            res.add(winner.getColumnIndex());

        } else {
            res = new ArrayList<>();
            res.add(0);
            res.add(0);
        }

        return res.stream().mapToInt(i -> i).toArray();
    }


    private static ArrayList<Node> getNodesSubNodes(int[][] matrix, Node node) {
        int lastFriend = node.getColumnIndex();
        int[] friends = matrix[lastFriend];

        ArrayList<Node> children = new ArrayList<>();

        for (int i = 0; i < friends.length; i++) {
            List<Integer> visited = node.getVisitedRows();
            if (visited == null) visited = new ArrayList<>();
            if (friends[i] != 0 && !visited.contains(i)) {
                children.add(new Node(friends[i], lastFriend, i, node));
            }
        }

        return children;
    }


    public static int greedy(int[][] matrix) {
        List<Integer> visited = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        int start = 0;
        int tempStart = 0;
        visited.add(0);
        int res = 0;

        while (visited.size() < matrix.length) {
            for (int j = 0; j < matrix.length; j++) {
                if (!visited.contains(j) && matrix[start][j] < minValue) {
                    minValue = matrix[start][j];
                    tempStart = j;
                }
            }
            start = tempStart;
            visited.add(tempStart);
            res += minValue;
            minValue = Integer.MAX_VALUE;
        }

        res += matrix[visited.get(visited.size() - 1)][0];

        return res;
    }
}