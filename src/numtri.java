
/*
ID: moser.el
LANG: JAVA
TASK: numtri
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class NumtriNode {
    NumtriNode left, right;
    int value;
    int largestSum;

    public NumtriNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}

class numtri {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

        int numOfRows = Integer.parseInt(f.readLine());

        NumtriNode root = buildTree(f, numOfRows);

        int largestSum = searchSolution(root);

        out.println(largestSum);
        
        out.close();
    }

    private static int searchSolution(NumtriNode root) {
        Queue<NumtriNode> nodes = new LinkedBlockingQueue<NumtriNode>();
        int largestSum = 0;
        root.largestSum = root.value;
        nodes.add(root);
        while (!nodes.isEmpty()) {
            NumtriNode n = nodes.remove();
            if (n.left == null && n.right == null) {
                // if we are a leaf we update the "global" largest sum
                largestSum = Math.max(largestSum, n.largestSum);
            } else {
                // if we are not a leaf, we update the largest sum of our children
                n.left.largestSum = Math.max(n.left.largestSum, n.left.value + n.largestSum);
                n.right.largestSum = Math.max(n.right.largestSum, n.right.value + n.largestSum);
                nodes.add(n.left);
                nodes.add(n.right);
            }
        }
        return largestSum;
    }

    private static NumtriNode buildTree(BufferedReader f, int numOfRows) throws IOException {
        Queue<NumtriNode> nodes = new LinkedBlockingQueue<NumtriNode>();
        NumtriNode root = new NumtriNode(Integer.parseInt(f.readLine()));
        nodes.add(root);
        NumtriNode right = null;
        for (int row = 2; row <= numOfRows; row++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            while (st.hasMoreTokens()) {
                NumtriNode parent = nodes.remove();
                if (right != null) {
                    parent.left = right;
                } else {
                    parent.left = new NumtriNode(Integer.parseInt(st.nextToken()));
                    nodes.add(parent.left);
                }
                parent.right = right = new NumtriNode(Integer.parseInt(st.nextToken()));
                nodes.add(parent.right);
            }
            right = null;
        }
        return root;
    }

    /**
     * Prints the "tree" rooted at *root* in a breath-first mode.
     * @param root
     */
    private static void printTree(NumtriNode root) {
        Queue<NumtriNode> nodes = new LinkedBlockingQueue<NumtriNode>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            NumtriNode n = nodes.remove();
            System.out.println(n.value);
            if (n.left != null) nodes.add(n.left);
            if (n.right != null) nodes.add(n.right);
        }
    }

}
