/*
ID: moser.el
LANG: JAVA
TASK: wormhole
 */

import java.io.*;
import java.util.*;

class Node {
    int x, y;
    Node linkedNode;
    Node nodeToTheRight;
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class wormhole {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

        int N = Integer.parseInt(f.readLine());
        List<Node> nodes = new ArrayList<Node>();
        HashMap<Integer, List<Node>> nodesToTheRightByYValue = new HashMap<Integer, List<Node>>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            Node newNode = new Node();
            newNode.x = Integer.parseInt(st.nextToken());
            newNode.y = Integer.parseInt(st.nextToken());
            
            if (!nodesToTheRightByYValue.containsKey(newNode.y)) {
                List<Node> nodesOnYLine = new ArrayList<Node>();
                nodesOnYLine.add(newNode);
                nodesToTheRightByYValue.put(newNode.y, nodesOnYLine);
            } else {
                nodesToTheRightByYValue.get(newNode.y).add(newNode);
            }
            
            nodes.add(newNode);
        }
        
        for (List<Node> nodesOnYLine : nodesToTheRightByYValue.values()) {
            nodesOnYLine.sort(new Comparator<Node>() {

                @Override
                public int compare(Node o1, Node o2) {
                    return o1.x - o2.x;
                }
                
            });
            
            for (int i = 0; i < nodesOnYLine.size() - 1; i++) {
                Node n1 = nodesOnYLine.get(i);
                Node n2 = nodesOnYLine.get(i + 1);
                n1.nodeToTheRight = n2;
            }
        }
        
        Node startingNode = nodes.get(0);
        int result = countSetOfPairingsWithCycle(nodes, startingNode);
        out.println(result);
        
        out.close();
    }

    /**
     * I assume that the starting node does not have a linkedNode
     * @param nodes
     * @param startingNode
     * @return
     */
    private static int countSetOfPairingsWithCycle(List<Node> nodes, Node startingNode) {
        int numOfSetOfPairingsWithCycle = 0;
        
        for (Node n : nodes) {
            if (n != startingNode && n.linkedNode == null) {
                link(startingNode, n);
                Node nextStartingNode = findNextStartingNode(nodes);           
                if (nextStartingNode == null) {
                    // we have finished
                    int result = isCycle(nodes) ? 1 : 0;
                    unlink(startingNode, n);
                    return result;
                }
                numOfSetOfPairingsWithCycle += countSetOfPairingsWithCycle(nodes, nextStartingNode);
                unlink(startingNode, n);
            }
        }
        
        return numOfSetOfPairingsWithCycle;
    }

    private static boolean isCycle(List<Node> nodes) {
        for (Node n : nodes) {
            if (isCycleStartingFromPos(nodes, n))
                return true;
        }
        return false;
    }

    private static boolean isCycleStartingFromPos(List<Node> nodes, Node pos) {
        for (int i = 0; i < nodes.size(); i++) {
            pos = pos.linkedNode.nodeToTheRight;
            if (pos == null)
                return false;
        }
        return true;
    }

    private static void unlink(Node n1, Node n2) {
        n1.linkedNode = null;
        n2.linkedNode = null;
    }

    private static Node findNextStartingNode(List<Node> nodes) {
        for (Node n : nodes)
            if (n.linkedNode == null)
                return n;
        return null;
    }

    private static void link(Node n1, Node n2) {
        n1.linkedNode = n2;
        n2.linkedNode = n1;
    }

}
