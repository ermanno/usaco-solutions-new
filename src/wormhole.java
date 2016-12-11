/*
ID: moser.el
LANG: JAVA
TASK: wormhole
 */

import java.io.*;
import java.util.*;

class WormholeNode {
    int x, y;
    WormholeNode linkedNode;
    WormholeNode nodeToTheRight;
    
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
        List<WormholeNode> nodes = new ArrayList<WormholeNode>();
        HashMap<Integer, List<WormholeNode>> nodesToTheRightByYValue = new HashMap<Integer, List<WormholeNode>>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            WormholeNode newNode = new WormholeNode();
            newNode.x = Integer.parseInt(st.nextToken());
            newNode.y = Integer.parseInt(st.nextToken());
            
            if (!nodesToTheRightByYValue.containsKey(newNode.y)) {
                List<WormholeNode> nodesOnYLine = new ArrayList<WormholeNode>();
                nodesOnYLine.add(newNode);
                nodesToTheRightByYValue.put(newNode.y, nodesOnYLine);
            } else {
                nodesToTheRightByYValue.get(newNode.y).add(newNode);
            }
            
            nodes.add(newNode);
        }
        
        for (List<WormholeNode> nodesOnYLine : nodesToTheRightByYValue.values()) {
            nodesOnYLine.sort(new Comparator<WormholeNode>() {

                @Override
                public int compare(WormholeNode o1, WormholeNode o2) {
                    return o1.x - o2.x;
                }
                
            });
            
            for (int i = 0; i < nodesOnYLine.size() - 1; i++) {
                WormholeNode n1 = nodesOnYLine.get(i);
                WormholeNode n2 = nodesOnYLine.get(i + 1);
                n1.nodeToTheRight = n2;
            }
        }
        
        WormholeNode startingNode = nodes.get(0);
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
    private static int countSetOfPairingsWithCycle(List<WormholeNode> nodes, WormholeNode startingNode) {
        int numOfSetOfPairingsWithCycle = 0;
        
        for (WormholeNode n : nodes) {
            if (n != startingNode && n.linkedNode == null) {
                link(startingNode, n);
                WormholeNode nextStartingNode = findNextStartingNode(nodes);           
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

    private static boolean isCycle(List<WormholeNode> nodes) {
        for (WormholeNode n : nodes) {
            if (isCycleStartingFromPos(nodes, n))
                return true;
        }
        return false;
    }

    private static boolean isCycleStartingFromPos(List<WormholeNode> nodes, WormholeNode pos) {
        for (int i = 0; i < nodes.size(); i++) {
            pos = pos.linkedNode.nodeToTheRight;
            if (pos == null)
                return false;
        }
        return true;
    }

    private static void unlink(WormholeNode n1, WormholeNode n2) {
        n1.linkedNode = null;
        n2.linkedNode = null;
    }

    private static WormholeNode findNextStartingNode(List<WormholeNode> nodes) {
        for (WormholeNode n : nodes)
            if (n.linkedNode == null)
                return n;
        return null;
    }

    private static void link(WormholeNode n1, WormholeNode n2) {
        n1.linkedNode = n2;
        n2.linkedNode = n1;
    }

}
