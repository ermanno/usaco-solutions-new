
/*
ID: moser.el
LANG: JAVA
TASK: barn1
 */

import java.io.*;
import java.util.*;

class barn1 {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		
		StringTokenizer st = new StringTokenizer(f.readLine());
		int maxNumOfBoards = Integer.parseInt(st.nextToken());
		int numOfStalls = Integer.parseInt(st.nextToken());
		int numOfCows = Integer.parseInt(st.nextToken());
		
		// indexes in asceding order
		List<Integer> occupiedStalls = new ArrayList<Integer>(numOfCows);
		for (int i = 0; i < numOfCows; i++) {
			occupiedStalls.add(Integer.parseInt(f.readLine()));
		}
		Collections.sort(occupiedStalls);
		
		// jump sizes in descending order
		List<Integer> jumpSizes = new ArrayList<Integer>();
		for (int i = 0; i < numOfCows - 1; i++) {
			int currentStallIndex = occupiedStalls.get(i);
			int nextStallIndex = occupiedStalls.get(i + 1);
			int jumpSize = nextStallIndex - currentStallIndex - 1;
			if (jumpSize > 0)
				jumpSizes.add(jumpSize);
		}
		Collections.sort(jumpSizes);
		Collections.reverse(jumpSizes);		
		
		int firstIndex = occupiedStalls.get(0);
		int lastIndex = occupiedStalls.get(occupiedStalls.size() - 1);
		int minNumOfBlockedStalls = lastIndex - firstIndex + 1;
		
		int numOfSplits = Math.min(maxNumOfBoards - 1, jumpSizes.size());
		for (int i = 0; i < numOfSplits; i++) {
			minNumOfBlockedStalls -= jumpSizes.get(i);
		}
		
		out.println(minNumOfBlockedStalls);
		
		out.close();
	}
}
