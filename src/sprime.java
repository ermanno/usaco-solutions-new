
/*
ID: moser.el
LANG: JAVA
TASK: sprime
 */

import java.io.*;
import java.util.*;

class sprime {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

        int N = Integer.parseInt(f.readLine());

        List<Integer> primes = generatePrimesOfLength(N);
        Collections.sort(primes);
        for (int prime : primes)
            out.println(prime);
        
        out.close();
    }

    private static List<Integer> generatePrimesOfLength(int length) {
        if (length == 1)
            return new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7));

        List<Integer> primesOfDesiredLength = new ArrayList<Integer>();
        List<Integer> possibleEndings = new ArrayList<Integer>(Arrays.asList(1, 3, 7, 9));
        List<Integer> smallerPrimes = generatePrimesOfLength(length - 1);
        
        for (int prime : smallerPrimes) {
            for (int i : possibleEndings) {
                int candidatePrime = prime * 10 + i;
                if (isPrime(candidatePrime))
                    primesOfDesiredLength.add(candidatePrime);
            }
        }
        
        return primesOfDesiredLength;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
