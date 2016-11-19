
/*
ID: moser.el
LANG: JAVA
TASK: ariprog
 */

import java.io.*;
import java.util.*;

class ariprog {
    public static List<Integer> sortedBisquares;
    public static int progressionLength;
    public static boolean[] isBisquare;
    public static PrintWriter out;
    public static boolean atLeastOne = false;
    
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        
        progressionLength = Integer.parseInt(f.readLine());
        int upperBoundBisquares = Integer.parseInt(f.readLine());
        
        isBisquare = new boolean[2 * upperBoundBisquares * upperBoundBisquares + 1];
        sortedBisquares = generateSortedBisquares(upperBoundBisquares);
        computeSolution();
        
        out.close();
    }
    
    public static List<Integer> generateSortedBisquares(int upperBoundBisquares) {
        Set<Integer> bisquares = new HashSet<Integer>();
        for(int p = 0; p <= upperBoundBisquares; p++) {
            for(int q = 0; q <= upperBoundBisquares; q++) {
                int number = p*p + q*q;
                bisquares.add(number);
                isBisquare[number] = true;
            }
        }
        List<Integer> sortedBisquares = new ArrayList<Integer>(bisquares);
        Collections.sort(sortedBisquares);
        return sortedBisquares;
    }
    
    public static void computeSolution() {
        int smallestBisquare = sortedBisquares.get(0);
        int biggestBisquare = sortedBisquares.get(sortedBisquares.size() - 1);
        for (int diff = 1; 
             (progressionLength - 1) * diff + smallestBisquare <= biggestBisquare;
             diff++) {
            for (int base : sortedBisquares) {
                if ((progressionLength - 1) * diff + base <= biggestBisquare)
                    search(base, diff, 0);
            }
        }
        if (!atLeastOne) {
            out.println("NONE");
        }
    }
    
    public static void search(int base, int diff, int depth) {
        int number = base + diff * depth;
        if (!isBisquare[number]) {
            return;
        }
        // the number is bisquare, we continue
        if (depth == progressionLength - 1) {
            atLeastOne = true;
            out.println(base + " " + diff);
            return;
        } else {
            search(base, diff, depth + 1);
        }
    }
}
