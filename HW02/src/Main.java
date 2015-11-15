import tsp.TSP;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting");

        int[][] res = {{0, 21, 33, 22, 45},
                {78, 0, 316, 28, 18},
                {67, 45, 0, 45, 32},
                {38, 12, 56, 0, 28},
                {71, 17, 45, 35, 0}
        };
        int[][] d = {{0, 6, 7, 9},
                {8, 0, 9, 4},
                {5, 4, 0, 3},
                {9, 6, 7, 0}};


        int[] result = TSP.dfs(d);
        System.out.print("\n end result: ");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
