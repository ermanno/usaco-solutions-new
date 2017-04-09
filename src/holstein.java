
/*
ID: moser.el
LANG: JAVA
TASK: holstein
 */

import java.io.*;
import java.util.*;

class holstein {

    static int numVitamins;
    static int vitaminRequirements[];
    static int numFeeds;
    static int feeds[][];
    static List<Integer> tempSolution = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
        PrintWriter out = new PrintWriter(new FileWriter("holstein.out"));

        numVitamins = Integer.parseInt(f.readLine());
        vitaminRequirements = new int[numVitamins];
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < numVitamins; i++) {
            vitaminRequirements[i] = Integer.parseInt(st.nextToken());
        }
        numFeeds = Integer.parseInt(f.readLine());
        feeds = new int[numFeeds][numVitamins];
        for (int i = 0; i < numFeeds; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < numVitamins; j++) {
                feeds[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        searchForSolution(new ArrayList<Integer>(), 0);

        out.print(tempSolution.size());
        for (int elem : tempSolution) {
            out.print(" " + (elem + 1));
        }
        out.println();

        f.close();
        out.close();
    }

    public static void searchForSolution(List<Integer> solution, int pos) {
        if (pos == numFeeds) {
            Collections.sort(solution);
            if (isSolution(solution) && isBetter(solution, tempSolution)) {
                tempSolution = copy(solution);
            }
        } else {
            // take
            solution.add(pos);
            searchForSolution(solution, pos + 1);

            // don't take
            solution.remove(solution.size() - 1);
            searchForSolution(solution, pos + 1);
        }
    }

   
    
    private static List<Integer> copy(List<Integer> solution) {
        List<Integer> copy = new ArrayList<Integer>(solution.size());
        copy.addAll(solution);
        return copy;
    }

    private static boolean isBetter(List<Integer> newSolution, List<Integer> oldSolution) {
        if (oldSolution.isEmpty())
            return true;
        if (newSolution.size() < oldSolution.size())
            return true;
        if (newSolution.size() > oldSolution.size())
            return false;
        for (int i = 0; i < newSolution.size(); i++) {
            if (newSolution.get(i) < oldSolution.get(i))
                return true;
            if (newSolution.get(i) > oldSolution.get(i))
                return false;
        }
        return false; // if we exit here, the solutions are the same, therefore
                      // the new one is not strictly "better"
    }

    private static boolean isSolution(List<Integer> solution) {
        int[] vitamins = new int[numVitamins];
        for (Integer element : solution) {
            addToSolution(vitamins, feeds[element]);
        }
        for (int i = 0; i < numVitamins; i++) {
            if (vitaminRequirements[i] > vitamins[i])
                return false;
        }
        return true;
    }

    private static void addToSolution(int[] vitamins, int[] feed) {
        for (int i = 0; i < numVitamins; i++) {
            vitamins[i] += feed[i];
        }
    }
}