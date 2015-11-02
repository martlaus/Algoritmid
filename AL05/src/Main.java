import greedytsp.GreedyTSP;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting....");
        int[][] res = {{0, 21, 33, 22, 45},
                {10, 0, 316, 56, 76},
                {7, 18, 0, 45, 32},
                {17, 28, 34, 0, 28},
                {57, 18, 39, 67, 0}
        };

        System.out.println("the citys are visited as follows");
        GreedyTSP.greedySolution(res);
    }
}
