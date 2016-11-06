
/*
ID: moser.el
LANG: JAVA
TASK: crypt1
 */

import java.io.*;
import java.util.*;

class crypt1 {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

        int N = Integer.parseInt(f.readLine());
        List<Integer> digits = new ArrayList<Integer>();
        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < N; i++) {
            digits.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> listOfTwoDigitNumbers = generateNumbersOfLength(2, digits);
        List<Integer> listOfThreeDigitNumbers = generateNumbersOfLength(3, digits);
        List<Integer> listOfFourDigitNumbers = generateNumbersOfLength(4, digits);

        int numOfSolutions = 0;
        for (int abc : listOfThreeDigitNumbers) {
            for (int de : listOfTwoDigitNumbers) {
                int e = de % 10;
                int d = de / 10;

                int abce = abc * e;
                if (!listOfThreeDigitNumbers.contains(abce))
                    continue;

                int abcd = abc * d;
                if (!listOfThreeDigitNumbers.contains(abcd))
                    continue;

                int sum = abcd * 10 + abce;
                if (!listOfFourDigitNumbers.contains(sum))
                    continue;

                numOfSolutions++;
            }
        }

        out.println(numOfSolutions);

        out.close();
    }

    public static List<Integer> generateNumbersOfLength(int n, List<Integer> digits) {
        if (n == 1)
            return digits;

        List<Integer> listOfGeneratedNums = new ArrayList<Integer>();
        for (int numOfLengthNMinus1 : generateNumbersOfLength(n - 1, digits)) {
            for (int digit : digits) {
                listOfGeneratedNums.add(numOfLengthNMinus1 * 10 + digit);
            }
        }
        return listOfGeneratedNums;
    }

}
