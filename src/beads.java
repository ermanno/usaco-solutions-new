
/*
ID: moser.el
LANG: JAVA
TASK: beads
 */

import java.io.*;
import java.util.*;

class beads {

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

		int length = Integer.parseInt(f.readLine());
		char[] beads = f.readLine().toCharArray();
		int maxLength = 0;
		for (int i = 0; i < length; i++) {
			int position = i;
			boolean isWhite = beads[position] == 'w';
			int tempMax = 0;
			char color = beads[position];
			while (tempMax < length && (isWhite || beads[position] == color || beads[position] == 'w')) {
				if (isWhite && beads[position] != 'w') {
					isWhite = false;
					color = beads[position];
				}
				tempMax += 1;
				position = previous(length, position);
			}

			position = next(length, i);
			isWhite = beads[position] == 'w';
			color = beads[position];
			while (tempMax < length && (isWhite || beads[position] == color || beads[position] == 'w')) {
				if (isWhite && beads[position] != 'w') {
					isWhite = false;
					color = beads[position];
				}
				tempMax += 1;
				position = next(length, position);
			}

			maxLength = Math.max(maxLength, tempMax);
		}

		out.println(maxLength);
		out.close();
	}

	private static int previous(int length, int index) {
		return (index <= 0) ? length - 1 : index - 1;
	}

	private static int next(int length, int index) {
		return (index >= length - 1) ? 0 : index + 1;
	}

}
