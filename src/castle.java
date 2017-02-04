/*
ID: moser.el
LANG: JAVA
TASK: castle
 */

import java.io.*;
import java.util.*;

class Cell {
    int n, m;
    int value;
    boolean visited;
}

class Room {
    private List<Cell> cells = new ArrayList<Cell>();
    
    void add(Cell c) {
        cells.add(c);
    }
    
    int size() {
        return cells.size();
    }
}

class castle {
    private static int N, M;
    private static Cell[][] grid;
    private static List<Room> rooms = new ArrayList<Room>();
    public static final int NORTH = 2;
    public static final int SOUTH = 8;
    public static final int EAST = 4;
    public static final int WEST = 1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("castle.in"));
        PrintWriter out = new PrintWriter(new FileWriter("castle.out"));

        StringTokenizer st = new StringTokenizer(f.readLine());
        M = Integer.valueOf(st.nextToken());
        N = Integer.valueOf(st.nextToken());

        grid = new Cell[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(f.readLine());
            for (int m = 0; m < M; m++) {
                grid[n][m] = new Cell();
                grid[n][m].value = Integer.valueOf(st.nextToken());
                grid[n][m].n = n; grid[n][m].m = m;
            }
        }
    
        createComponents();
        
        out.println(rooms.size());
        out.println(maxRoomSize());
        
        f.close();
        out.close();
    }
    
    public static int maxRoomSize() {
        int maxSize = 0;
        for (Room r : rooms) {
            maxSize = Math.max(maxSize, r.size());
        }
        return maxSize;
    }
    
    public static void createComponents() {
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (!grid[n][m].visited) {
                    Room r = new Room();
                    visit(r, grid[n][m]);
                    rooms.add(r);
                }
            }
        }
    }

    public static boolean hasWall(int encodedWalls, int direction) {
        int res = (encodedWalls & direction);
        return res == direction;
    }

    /**
     * Here I rely on the fact that I have "walls" rounding off the grid, so I cannot go outside the border.
     * @param c
     */
    public static void visit(Room r, Cell c) {
        c.visited = true;
        r.add(c);
        if (!hasWall(c.value, NORTH) && !grid[c.n - 1][c.m].visited) {
            visit(r, grid[c.n - 1][c.m]);
        }
        if (!hasWall(c.value, SOUTH) && !grid[c.n + 1][c.m].visited) {
            visit(r, grid[c.n + 1][c.m]);
        }
        if (!hasWall(c.value, EAST) && !grid[c.n][c.m + 1].visited) {
            visit(r, grid[c.n][c.m + 1]);
        }
        if (!hasWall(c.value, WEST) && !grid[c.n][c.m - 1].visited) {
            visit(r, grid[c.n][c.m - 1]);
        }
    }
}
