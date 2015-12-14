package radar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Radar {
    
    private char[][] maze;
    private int x,y,d;
    
    public Radar(String fileName) throws IOException {
        readMaze(fileName);
        initializeStartAndFinish();
    }
    
    /**
     * 
     * @return The dimension of maze
     */
    public int getDimension() {
        return d;
    }
    
    /**
     *  current position in the maze
     * @return 
     */
    public int[] getPosition() {
        return new int[] {x,y};
    }
    
    /**
     * 
     * @return Node 
     */
    public Node getSurroundings() {
        Node s = new Node();
        
        if(y==0) s.north = '#';
        else s.north = maze[x][y-1];
        if(x == 0) s.west = '#';
        else s.west = maze[x-1][y];
        s.current = maze[x][y];
        if(x == maze.length-1) s.east = '#';
        else s.east = maze[x+1][y];
        if(y == maze[x].length-1) s.south = '#';
        else s.south = maze[x][y+1];
        
        return s;
    }
    
    /**
     * 
     * @param direction (N - north, E - east, S - south, W - west) 
     * @return
     */
    public boolean move(char direction) {
        if (direction == 'N' && check(x,y-1)) { y = y - 1; return true; }
        if (direction == 'E' && check(x+1,y)) { x = x + 1; return true; }
        if (direction == 'S' && check(x,y+1)) { y = y + 1; return true; }
        if (direction == 'W' && check(x-1,y)) { x = x - 1; return true; }
        return false;
    }

    private boolean check(int x, int y) {
        if (x >= d || x < 0 || y >= d || y < 0) return false;
        if (!Character.isDigit(maze[x][y])) {
            if (!(maze[x][y] == 'B' || maze[x][y] == 'T')) return false;
        }
        return true;
    }
    
    private void readMaze(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/" + fileName));
        
        d = Integer.parseInt(reader.readLine());
        d = 2*d + 1;
        
        this.maze = new char[d][d];
        
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            for (int j = 0; j < line.length(); j++) {
                maze[j][i] = line.charAt(j);
            }
            i++;
        }
        reader.close();        
    }
    
    private void initializeStartAndFinish() {
        for (int i = maze.length - 1; i >= 0; --i) {
            for (int j = maze[i].length - 1; j >= 0; --j) {
                if (maze[i][j] == 'B') {
                    x = i; y = j;
                    return;
                }
            }
        }
    }
    
}