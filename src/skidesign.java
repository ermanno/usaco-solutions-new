
/*
ID: moser.el
LANG: JAVA
TASK: skidesign
 */

import java.io.*;
import java.util.*;

class skidesign {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
        
        int N = Integer.parseInt(f.readLine());
        List<Integer> hillHeights = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            hillHeights.add(Integer.parseInt(f.readLine()));
        }
        
        int minCost = calculateMinCost(hillHeights);
        
        out.println(minCost);
        out.close();
    }

    private static int calculateMinCost(List<Integer> hillHeights) {
        int minCost = Integer.MAX_VALUE;
        
        for (int i = 1; i <= 100 - 17; i++) {
            minCost = Math.min(minCost, calculateCost(hillHeights, i));
        }
        
        return minCost;
    }
    
    private static int calculateCost(List<Integer> hillHeights, int i) {
        int cost = 0;
        for (int height : hillHeights) {
            cost += calculateCost(height, i);
        }
        return cost;
    }
    
    private static int calculateCost(int height, int i) {
        int start = i;
        int end = i + 17;
        
        if (height < start)
            return (start - height) * (start - height);
        if (height > end)
            return (end - height) * (end - height);
        return 0;
    }
    
    
}
