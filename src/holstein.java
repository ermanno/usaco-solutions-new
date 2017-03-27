
/*
ID: moser.el
LANG: JAVA
TASK: holstein
 */

import java.io.*;
import java.util.*;

class holstein {
    private static int numVitamins;
    private static int numFeeds;
    private static int[] vitamins;
    private static int[][] feeds;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
        PrintWriter out = new PrintWriter(new FileWriter("holstein.out"));

        numVitamins = Integer.parseInt(f.readLine());
        vitamins = new int[numVitamins];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < numVitamins; i++) {
            vitamins[i] = Integer.parseInt(st.nextToken());
        }
        
        numFeeds = Integer.parseInt(f.readLine());
        feeds = new int[numFeeds][numVitamins];
        for (int i = 0; i < numFeeds; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < numVitamins; j++) {
                feeds[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        
        
        f.close();
        out.close();
    }
    
    public static boolean isBetter(boolean[] newSolution, int costNewSolution, boolean[] oldSolution, int costOldSolution) {
        int solutionLength = newSolution.length;

        if (costNewSolution < costOldSolution) return true;
        if (costNewSolution > costOldSolution) return false;
        
        for (int i = 0; i < solutionLength; i++) {
            if (newSolution[i] && !oldSolution[i]) {
                return true;
            } else if (!newSolution[i] && oldSolution[i]) {
                return false;
            }
        }
        return false; // they are the same solution, we don't change
    }
}
