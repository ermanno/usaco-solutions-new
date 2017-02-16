
/*ID: moser.el
LANG: JAVA
TASK: sort3
 */

import java.io.*;
import java.util.*;

class sort3 {

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter out = new PrintWriter(new FileWriter("sort3.out"));

        int N = Integer.valueOf(f.readLine());
        int[] numbers = new int[N];
        int ones = 0;
        int twos = 0;
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(f.readLine());
            if (numbers[i] == 1)
                ones++;
            if (numbers[i] == 2)
                twos++;
        }

        int numOfSwaps = minSwaps(numbers, ones, twos);

        out.println(numOfSwaps);

        f.close();
        out.close();
    }

    public static int minSwaps(int[] numbers, int ones, int twos) {
        int N = numbers.length;
        int numOfSwaps = 0;
        for (int i = ones; i < N; i++) {
            // ones ... twos ... threes, we cycle over "twos ... threes"
            // looking for ones
            if (numbers[i] == 1) {
                // we need to swap it, thus we look to the "ones" and search for "twos"
                int posToSwap = 0;
                for (int j = 0; j < ones; j++) {
                    if (numbers[j] == 2) {
                        posToSwap = j;
                        break;
                    } else if (numbers[j] == 3) {
                        posToSwap = j;
                    }
                }
                swap(numbers, i, posToSwap);
                numOfSwaps++;
            }
        }
        
        // we have already places the ones
        for (int i = ones + twos; i < N; i++) {
            if (numbers[i] == 2) {
                numOfSwaps++;
            }
        }
        return numOfSwaps;
    }

    public static void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
