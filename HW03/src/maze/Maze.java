package maze;

import java.io.IOException;
import java.util.ArrayList;

import radar.Node;
import radar.Radar;

public class Maze {
    private Radar r;

    private boolean[][] visited;

    private static final Character EAST = 'E';
    private static final Character WEST = 'W';
    private static final Character SOUTH = 'S';
    private static final Character NORTH = 'N';
    private static final Character TREASURE = 'T';
    private static final Character WALL = '#';
    private static final Character START = 'B';

    public Maze(String fileName) {
        try {
            r = new Radar(fileName);
        } catch (IOException e) {
            r = null;
        }
    }

    /**
     * Solve a maze by finding its treasure.
     *
     * @return ArrayList of characters as path to treasure.
     */
    public ArrayList<Character> solve() {
        int dimension = r.getDimension();
        visited = new boolean[dimension][dimension];
        ArrayList<Character> path = new ArrayList<>();

        while (true) {
            Node position = r.getSurroundings();
            int[] coordinates = r.getPosition();
            int x = coordinates[0];
            int y = coordinates[1];
            visited[x][y] = true;

            if (position.current == TREASURE) return path;

            if (canMove(NORTH)) {
                path.add(NORTH);
                r.move(NORTH);
            }
            else if (canMove(EAST)) {
                path.add(EAST);
                r.move(EAST);
            }
            else if (canMove(WEST)) {
                path.add(WEST);
                r.move(WEST);
            }
            else if (canMove(SOUTH)) {
                path.add(SOUTH);
                r.move(SOUTH);
            }

            else {
                while (!canMove()) {
                    goBack(path.get(path.size() - 1));
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    public void goBack(Character direction) {
        switch (direction) {
            case 'N': r.move(SOUTH);
                break;
            case 'S': r.move(NORTH);
                break;
            case 'W': r.move(EAST);
                break;
            case 'E': r.move(WEST);
                break;
            default:
                break;
        }
    }


    public boolean canMove(Character direction) {
        Node position = r.getSurroundings();
        int[] coordinates = r.getPosition();
        int x = coordinates[0];
        int y = coordinates[1];

        if (direction == NORTH && position.north != START && position.north != WALL && !visited[x][y - 1]) return true;
        else if (direction == SOUTH && position.south != START && position.south != WALL && !visited[x][y + 1]) return true;
        else if (direction == EAST && position.east != START && position.east != WALL && !visited[x + 1][y]) return true;
        else if (direction == WEST && position.west != START && position.west != WALL && !visited[x - 1][y]) return true;

        return false;
    }

    public boolean canMove() {
        return canMove(SOUTH) || canMove(EAST) || canMove(WEST) || canMove(NORTH);
    }

//    public static void main(String[] args) {
//        Maze m = new Maze("nm100b.maze");
//        ArrayList<Character> result = m.solve();
//        System.out.println("Result: " + result);
//    }
    
}