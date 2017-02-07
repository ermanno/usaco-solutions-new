
/*ID: moser.el
LANG: JAVA
TASK: frac1
 */

import java.io.*;
import java.util.*;

class Fraction implements Comparable<Fraction> {
    private int numerator;
    private int denominator;

    Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public int compareTo(Fraction fraction) {
        // Consider a/b and c/d:
        // a/b -> ad/bd
        // c/d -> cb/bd
        Integer ad = this.numerator * fraction.denominator;
        Integer cb = fraction.numerator * fraction.denominator;
        return ad.compareTo(cb);
    }
}

class frac1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter out = new PrintWriter(new FileWriter("frac1.out"));

        int N = Integer.parseInt(f.readLine());
        List<Fraction> fractions = new ArrayList<Fraction>();
        fractions.add(new Fraction(0, 1));
        fractions.add(new Fraction(1, 1));

        int[] primes = new int[N];

        
        
        Collections.sort(fractions);
        for (Fraction fraction : fractions) {
            out.println(fraction);
        }

        f.close();
        out.close();
    }
}
