/*
ID: moser.el
LANG: JAVA
TASK: castle
 */

import java.io.*;
import java.util.*;

class room {
    int n, m;
    int value;
    boolean visited;
}

class castle {
    private static int N, M;
    private static room[][] grid;
    private static int numComponents;
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

        grid = new room[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(f.readLine());
            for (int m = 0; m < M; m++) {
                grid[n][m] = new room();
                grid[n][m].value = Integer.valueOf(st.nextToken());
                grid[n][m].n = n; grid[n][m].m = m;
            }
        }
    
        createComponents();
        
        out.println(numComponents);
        
        f.close();
        out.close();
    }
    
    public static void createComponents() {
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (!grid[n][m].visited) {
                    visit(grid[n][m]);
                    numComponents++;
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
     * @param room
     */
    public static void visit(room room) {
        room.visited = true;
        if (!hasWall(room.value, NORTH) && !grid[room.n - 1][room.m].visited) {
            visit(grid[room.n - 1][room.m]);
        }
        if (!hasWall(room.value, SOUTH) && !grid[room.n + 1][room.m].visited) {
            visit(grid[room.n + 1][room.m]);
        }
        if (!hasWall(room.value, EAST) && !grid[room.n][room.m + 1].visited) {
            visit(grid[room.n][room.m + 1]);
        }
        if (!hasWall(room.value, WEST) && !grid[room.n][room.m - 1].visited) {
            visit(grid[room.n][room.m - 1]);
        }
    }
}
