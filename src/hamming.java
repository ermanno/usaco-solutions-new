import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
ID: moser.el
LANG: JAVA
TASK: hamming
*/

class hamming {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int numberOfCodewords = Integer.parseInt(st.nextToken());
        int bitsInCodeword = Integer.parseInt(st.nextToken());
        int hammingDistance = Integer.parseInt(st.nextToken());

        ArrayList<Integer> codewords = new ArrayList<Integer>();
        codewords.add(0);
        for (int i = 0; i < numberOfCodewords; i++) {
            for (int candidateCodeword = 0;
                 candidateCodeword < (1 << bitsInCodeword);
                 candidateCodeword++) {
                if(isValidCodeword(bitsInCodeword, hammingDistance, codewords, candidateCodeword)) {
                    codewords.add(candidateCodeword);
                }
            }
        }
        
        for (int i = 1; i <= numberOfCodewords; i++) {
            out.print(codewords.get(i - 1));
            if (i % 10 == 0 || i == numberOfCodewords) {
                out.println();
            } else {
                out.print(" ");
            }
        }
        
        f.close();
        out.close();
    }

    private static boolean isValidCodeword(int bitsInCodeword,
                                           int hammingDistance,
                                           ArrayList<Integer> codewords,
                                           int candidate) {
        for (int codeword : codewords) {
            if (calculateHammingDistance(candidate, codeword, bitsInCodeword) < hammingDistance) {
                return false;
            }
        }
        return true;
    }
    
    private static int calculateHammingDistance(int a, int b, int bitsInCodeword) {
        int difference = 0;
        for (int i = 0; i < bitsInCodeword; i++) {
            if (((1 << i) & a) != ((1 << i) & b)) {
                difference++;
            }
        }
        return difference;
    }
}
