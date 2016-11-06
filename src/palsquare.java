
/*
ID: moser.el
LANG: JAVA
TASK: palsquare
 */

import java.io.*;

class palsquare {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));

        int base = Integer.parseInt(f.readLine());

        for (int i = 1; i <= 300; i++) {
            String convertedSquare = convertToBase(i * i, base);
            if (isPalindromic(convertedSquare))
                out.println(convertToBase(i, base) + " " + convertedSquare);
        }

        System.out.println(convertToBase(3 * 3, 2));

        out.close();
    }

    private static String convertToBase(int number, int base) {
        if (number == 0)
            return "";

        return convertToBase(number / base, base) + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()[number % base];
    }

    private static boolean isPalindromic(String number) {
        for (int i = 0; i < number.length() / 2; i++) {
            if (number.charAt(i) != number.charAt(number.length() - i - 1))
                return false;
        }
        return true;
    }
}
