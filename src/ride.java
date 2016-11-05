/*
ID: moser.el
LANG: JAVA
TASK: ride
 */

import java.io.*;
import java.util.*;

class ride {
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ride.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

		String comet = f.readLine();
		String group = f.readLine();
		
		if (hash(comet) == hash(group))
			out.println("GO");
		else
			out.println("STAY");
		
		out.close();
	}

	private static int hash(String name) {
		int base = 'A' - 1;
		int product = 1;
		for (char c : name.toCharArray()) {
			product *= (c - base);
		}
		return product % 47;
	}
}
