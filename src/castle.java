/*
ID: moser.el
LANG: JAVA
TASK: castle
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

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

class Wall implements Comparable<Wall> {
    Cell from;
    Cell to;
    char side;
    
    @Override
    public String toString() {
        return (from.n + 1) + " " + (from.m + 1) + " " + side;
    }

    @Override
    public int compareTo(Wall w) {
        if (this.from.m < w.from.m) {
            return +1;
        } else if (this.from.m == w.from.m) {
            if (this.from.n < w.from.n) {
                return +1;
            } else if (this.from.n == w.from.n) {
                if (this.side == 'N' && w.side == 'E')
                    return +1;
                else if (this.side == w.side) {
                    return 0; // can only happen if we compare an element with itself
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}

class castle {
    private static int N, M;
    private static Cell[][] grid;
    private static List<Room> rooms = new ArrayList<Room>();
    private static List<Wall> walls = new ArrayList<Wall>();
    private static List<Wall> candidateWalls = new ArrayList<Wall>();
    private static int currentMaxSizeAfterMerge = 0;
    
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
        Wall wallToRemove = removeOneWall();
        out.println(candidateWalls);
        out.println(rooms.size());
        out.println(maxRoomSize());
        out.println(currentMaxSizeAfterMerge);
        out.println(wallToRemove);
        
        f.close();
        out.close();
    }
    
    private static Wall removeOneWall() {
        for (Wall w : walls) {
            if (w.from.room != w.to.room) {
                int sum = w.from.room.size() + w.to.room.size();
                if (sum == currentMaxSizeAfterMerge) {
                    candidateWalls.add(w);
                } else if (sum > currentMaxSizeAfterMerge) {
                    currentMaxSizeAfterMerge = sum;
                    candidateWalls.clear();
                    candidateWalls.add(w);
                }
            }
        }
        Collections.sort(candidateWalls);
        return candidateWalls.get(candidateWalls.size() - 1);
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

    public static void exploreInDirection(Room r, Cell c, int direction, int n, int m) {
        if (!hasWall(c.value, direction) && !grid[n][m].visited) {
            visit(r, grid[n][m]);
        }
    }
    
    public static void addWallInDirection(Cell c, int direction, int n, int m) {
        if (hasWall(c.value, direction) && isInsideGrid(n, m)) {
            Wall w = new Wall();
            w.from = c;
            w.to = grid[n][m];
            w.side = (direction == NORTH) ? 'N' : 'E';
            walls.add(w);
        }
    }
    
    public static void visit(Room r, Cell c) {
        c.visited = true;
        r.add(c);
        c.room = r;
        exploreInDirection(r, c, NORTH, c.n - 1, c.m);
        addWallInDirection(c, NORTH, c.n - 1, c.m);
        exploreInDirection(r, c, SOUTH, c.n + 1, c.m);
        exploreInDirection(r, c, EAST, c.n, c.m + 1);
        addWallInDirection(c, EAST, c.n, c.m + 1);
        exploreInDirection(r, c, WEST, c.n, c.m - 1);
    }
}
