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
    Room room;
}

class Room {
    private List<Cell> cells = new ArrayList<Cell>();
    
    public List<Cell> getCells() {
        return cells;
    }
    
    void add(Cell c) {
        cells.add(c);
    }
    
    int size() {
        return cells.size();
    }
}

class Wall {
    Cell cell;
    char side;
}

class castle {
    private static int N, M;
    private static Cell[][] grid;
    private static List<Room> rooms = new ArrayList<Room>();
    private static int currentMaxSizeAfterMerge = 0;
    private static List<Wall> candidateWalls = new ArrayList<Wall>();
    
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
        out.println(currentMaxSizeAfterMerge);
        
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
    
    public static boolean isInsideGrid(int n, int m) {
        return (n >= 0 && n < N && m >= 0 && m < M);
    }

    public static void process(Room r, Cell c, int direction, int n, int m) {
        if (!hasWall(c.value, direction) && !grid[n][m].visited) {
            visit(r, grid[n][m]);
        } else if (hasWall(c.value, direction) && isInsideGrid(n, m)) {
            // add wall (could be between cells of the same room, since it just mean that the two cells
            // are not *directly* connected, but they might be connected through another path
            
            // TODO think about how to represent a wall
        }
    }
    
    public static void visit(Room r, Cell c) {
        c.visited = true;
        r.add(c);
        c.room = r;
        process(r, c, NORTH, c.n - 1, c.m);
        process(r, c, SOUTH, c.n + 1, c.m);
        process(r, c, EAST, c.n, c.m + 1);
        process(r, c, WEST, c.n, c.m - 1);
    }
}
