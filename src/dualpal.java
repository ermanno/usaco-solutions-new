
/*
ID: moser.el
LANG: JAVA
TASK: dualpal
 */

import java.io.*;
import java.util.*;

class dualpal {
    private static List<Integer> palindromes = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int counter = 0;
        int number = S + 1;

        while (counter < N) {
            if (hasAtLeastTwoPalindromes(number)) {
                palindromes.add(number);
                counter++;
            }
            number++;
        }

        for (Integer i : palindromes)
            out.println(i);

        out.close();
    }

    private static boolean hasAtLeastTwoPalindromes(int number) {
        int numPalindromic = 0;
        for (int base = 2; base <= 10; base++) {
            String convertedNumber = convertNumToBase(number, base);
            if (isPalindromic(convertedNumber)) {
                numPalindromic++;
            }

            if (numPalindromic >= 2)
                return true;
        }
        return false;
    }

    private static boolean isPalindromic(String number) {
        if (number.charAt(0) == '0' || number.charAt(number.length() - 1) == '0')
            return false;

        for (int i = 0; i < number.length() / 2; i++) {
            if (number.charAt(i) != number.charAt(number.length() - 1 - i))
                return false;
        }

        return true;
    }

    private static String convertNumToBase(int number, int base) {
        if (number == 0)
            return "";

        return convertNumToBase(number / base, base) + number % base;
    }
}
