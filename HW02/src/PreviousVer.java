import tsp.Node;

import java.util.*;

public class PreviousVer {

    public static Node rootNode;
    public static ArrayList<Node> nodes = new ArrayList();
    public static int[][] adjMatrix;//Edges will be represented as adjacency Matrix
    public static int size;
    public static List<Node> visited = new ArrayList<>();
    static int minLength = Integer.MAX_VALUE;
    static int[] res;


    private static Node getUnvisitedChildNode(Node n) {
        int index = n.getColumnIndex();
        int j = 0;
        while (j < adjMatrix.length) {
            int spot = index * adjMatrix.length + j;
            Node node = nodes.get(spot);
            if (!visited.contains(node) && node.getValue() != 0 && n.getRowIndex() != node.getColumnIndex()) {

                return node;
            }
            j++;
        }
        return null;
    }

    //DFS traversal of a tree is performed by the dfs() function

    /* Depth-first search */
    public static int[] dfs(int[][] adjacencyMatrix) {
        res = new int[adjacencyMatrix.length + 1];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] < min) {
                    min = adjacencyMatrix[i][j];
                }

                int spot = i * adjacencyMatrix.length + j;
                nodes.add(spot, new Node(adjacencyMatrix[i][j], i, j));
            }
        }
        rootNode = nodes.get(0);
        adjMatrix = adjacencyMatrix;

        Stack s = new Stack();
        s.push(rootNode);
        visited.add(rootNode);
        rootNode.setVisited(true);
        printNode(rootNode);
        loopNodes(s);

        clearNodes();

        return res;
    }

    private static void loopNodes(Stack s) {
        while (!s.isEmpty()) {
            Node n = (Node) s.peek();
            Node child = getUnvisitedChildNode(n);
            if (child != null) {
                child.setRoadLength(n.getRoadLength() + child.getValue());
                child.visited = true;
                printNode(child);
                s.push(child);
                visited.add(child);
                loopNodes(s);
            } else {
                int length;
                int index = n.getColumnIndex();
                int spot = index * adjMatrix.length;

                length = n.getRoadLength() + nodes.get(spot).getValue();

                if (length < minLength) {
                    minLength = length;
                    res[adjMatrix.length] = 0;
                    System.out.println("branchi löpp , length: " + minLength);
                }
                int removable = ((Node) s.peek()).getColumnIndex();
                res[s.size()] = removable;
                s.pop();

                removable = ((Node) s.peek()).getColumnIndex();
                res[s.size()] = removable;
                s.pop();
            }
        }
    }


    //Utility methods for clearing visited property of node
    private static void clearNodes() {
        int i = 0;
        while (i < size) {
            Node n = nodes.get(i);
            n.visited = false;
            i++;
        }
    }

    //Utility methods for printing the node's label
    private static void printNode(Node n) {
        System.out.println(n.getId() + " ");
    }

    /* Best first search */
    public static int[] bfs(int[][] adjacencyMatrix) {
        return null;
    }

    //BFS traversal of a tree is performed by the bfs() function
    public void bfs() {

        //BFS uses Queue data structure
        Queue q = new LinkedList();
        q.add(rootNode);
        printNode(rootNode);
        rootNode.visited = true;
        while (!q.isEmpty()) {
            Node n = (Node) q.remove();
            Node child = null;
            while ((child = getUnvisitedChildNode(n)) != null) {
                child.visited = true;
                printNode(child);
                q.add(child);
            }
        }
        //Clear visited property of nodes
        clearNodes();
    }

    //This method will be called to make connect two nodes
    public void connectNode(Node start, Node end) {
        if (adjMatrix == null) {
            size = nodes.size();
            adjMatrix = new int[size][size];
        }

        int startIndex = nodes.indexOf(start);
        int endIndex = nodes.indexOf(end);
        adjMatrix[startIndex][endIndex] = 1;
        adjMatrix[endIndex][startIndex] = 1;
    }

}