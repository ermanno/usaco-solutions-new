
/*
ID: moser.el
LANG: JAVA
TASK: friday
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class friday {
	
	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

		int N = Integer.parseInt(f.readLine());
		int[] week = new int[7];
		
		int counter = 2 + 13 - 1;
		for (int year = 1900; year < 1900 + N; year++) {
			for (int month = 1; month <= 12; month++) {
				week[counter % 7]++;
				counter = nextCounter(counter, month, year);
			}
		}
		
		for (int i = 0; i <= 5; i++) {
			out.print(week[i] + " ");
		}
		out.println(week[6]);
		
		out.close();
	}
	
	private static int nextCounter(int counter, int month, int year) {
		if (has30Days(month))
			return counter + 30;
		
		if (month == 2) {
			if (isLeapYear(year))
				return counter + 29;
			else
				return counter + 28;
		}
		
		return counter + 31;
	}

	private static boolean has30Days(int month) {
		if (month == 9 || // september
			month == 4 || // april
			month == 6 || // june
			month == 11 // november
			)
			return true;
		else
			return false;
	}
	
	private static boolean isLeapYear(int year) {
		return year % 4 == 0 && (year % 400 == 0 || year % 100 != 0);
	}
}
