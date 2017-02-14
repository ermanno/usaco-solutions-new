
/*ID: moser.el
LANG: JAVA
TASK: sort3
 */

import java.io.*;
import java.util.*;

class sort3 {
    private static List<Integer> numbers;
    private static int swapCount = 0;
    
    public static void setNumbers(List<Integer> numbers) {
        sort3.numbers = numbers;
    }
    
    public static List<Integer> getNumbers() {
        return numbers;
    }
    
    public static int getSwapCount() {
        return swapCount;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter out = new PrintWriter(new FileWriter("sort3.out"));
        
        int N = Integer.parseInt(f.readLine());
        numbers = new ArrayList<Integer>(N);
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(f.readLine()));
        }
        
        sort(N);
        
        out.println(swapCount);
        
        f.close();
        out.close();
    }

    public static void sort(int N) {
        int posToSwapTo = 0;
        for (int i = 1; i < N; i++) {
            if (numbers.get(i - 1) < numbers.get(i)) {
                swap(i, posToSwapTo);
                posToSwapTo++;
            } else if (numbers.get(i - 1) > numbers.get(i)) {
                posToSwapTo = i;
            }
        }
    }
    
    public static void swap(int from, int to) {
        swapCount++;
        int temp = numbers.get(from);
        numbers.set(from, numbers.get(to));
        numbers.set(to, temp);
    }
}
