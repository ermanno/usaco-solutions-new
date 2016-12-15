
/*
ID: moser.el
LANG: JAVA
TASK: numtri
 */

import java.io.*;
import java.util.*;

class Tree {
    int value;
    int sum;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

class numtri {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

        int numOfRows = Integer.parseInt(f.readLine());
        int solution = calculateSolution(f, numOfRows);
        
        out.println(solution);

        out.close();
    }

    private static int calculateSolution(BufferedReader f, int numOfRows) throws IOException {
        int globalSum = 0;
        
        // Create the "root" of the "tree"
        int number = Integer.parseInt(f.readLine());
        Tree t = new Tree();
        t.value = number;
        t.sum = t.value;
        
        // Add the root to the queue
        Queue<Tree> queue = new LinkedList<Tree>();
        
        queue.add(t);

        for (int i = 2; i <= numOfRows; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());

            // As the first element in the i-th row, it is the left child of the first node of the "previous row"
            Tree parent = queue.remove();
            t = new Tree();
            t.value = Integer.parseInt(st.nextToken());
            t.sum = parent.sum + t.value;
            
            queue.add(t);

            // We then look ad the other nodes, barring the last: they are the right child of the current parent,
            // and the left child of the next parent, the sibling of the current parent
            for (int j = 2; j <= i - 1; j++) {
                t = new Tree();
                t.value = Integer.parseInt(st.nextToken());
                t.sum = parent.sum + t.value;

                parent = queue.remove();
                t.sum = Math.max(t.sum, parent.sum + t.value);
                
                queue.add(t);
            }
            
            // The last node is the right child of the last node in the previous row
            t = new Tree();
            t.value = Integer.parseInt(st.nextToken());
            t.sum = parent.sum + t.value;
            
            queue.add(t);
        }

        // We have left in the queue the leaves, the max is one of their sums
        for (Tree tr : queue) {
            globalSum = Math.max(tr.sum, globalSum);
        }

        return globalSum;
    }

}
