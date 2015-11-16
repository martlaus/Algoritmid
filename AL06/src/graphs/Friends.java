package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by mart on 16.11.15.
 */
public class Friends {
    static int[] myPair;
    static List<List<Integer>> totalList = new ArrayList<>();
    static int[] list;

    public static void bfs(int[][] adjacencyMatrix, int[] pair) {
        myPair = pair;
        Queue<Integer> q = breadth_first_search(adjacencyMatrix, pair);
        list = new int[q.size()+1];
        list[0] = pair[0];
        int len = q.size();
        for(int i = 1; i <= len; i++) {
            //System.out.println(q.peek());
            list[i] = q.remove();
        }
    }

    public static int getDistance() {
        return list.length;
    }

    public static int[] getConnectionList() {


        return list;
    }

    public static Queue<Integer> breadth_first_search(int[][] matrix, int[] pair) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> friends = new ArrayList<>();

        int v = pair[0];
        // Visit root.
        queue.add(v);
        outer:
        while (!queue.isEmpty()) {
            v = queue.peek();
            // Visit each child.
            for (int i = 0; i < matrix.length; i++) {
                int value = matrix[v][i];
                if (value != 0) {
                    if (i == pair[1]) {
                        //System.out.println("end?");
                        //System.out.println("v " + v + " i " + i + " matrix " + matrix[v][i]);

                        friends.add(i);
                        queue.add(i);
                        break outer;
                    } else {

                        //System.out.println("v " + v + " i " + i + " matrix " + matrix[v][i]);
                        friends.add(i);
                        queue.remove();
                        queue.add(i);
                    }


                }
            }

            totalList.add(friends);
            friends.clear();
        }

        //System.out.println("list ready");
        return queue;
    }
}
