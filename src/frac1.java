
/*ID: moser.el
LANG: JAVA
TASK: frac1
 */

import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Fraction implements Comparable<Fraction> {
    private BigInteger numerator;
    private BigInteger denominator;
    
    public BigInteger getNumerator() {
        return numerator;
    }
    
    public BigInteger getDenominator() {
        return denominator;
    }

    Fraction(int numerator, int denominator) {
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.valueOf(denominator);
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
        BigInteger ad = this.numerator.multiply(fraction.denominator);
        BigInteger cb = fraction.numerator.multiply(this.denominator);
        return ad.compareTo(cb);
    }
    
    public boolean isReduced() {
        return this.numerator.gcd(this.denominator).equals(BigInteger.ONE);
    }
    
    @Override
    public boolean equals(Object obj) {
        Fraction fraction = (Fraction) obj;
        return this.denominator == fraction.denominator && this.numerator == fraction.numerator;
    }
}

class frac1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter out = new PrintWriter(new FileWriter("frac1.out"));

        int N = Integer.parseInt(f.readLine());
        List<Fraction> fractions = new ArrayList<Fraction>();
        
        for (int denominator = 1; denominator <= N; denominator++) {
            for (int numerator = 0; numerator <= denominator; numerator++) {
                Fraction fraction = new Fraction(numerator, denominator);
                if (fraction.isReduced())
                    fractions.add(fraction);
            }
        }
        
        Collections.sort(fractions);
        for (Fraction fraction : fractions) {
            out.println(fraction);
        }

        f.close();
        out.close();
    }
}
