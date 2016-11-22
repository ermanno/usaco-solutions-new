
/*
ID: moser.el
LANG: JAVA
TASK: milk3
 */

import java.io.*;
import java.util.*;

class milk3 {

    private static int[] capacities = new int[3];
    private static int[] buckets = new int[3];
    private static boolean[][][] visited;
    private static Set<Integer> C_Values = new HashSet<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        for (int i = 0; i < 3; i++) {
            capacities[i] = Integer.parseInt(st.nextToken());
        }
        buckets[2] = capacities[2];
        visited = new boolean[capacities[0] + 1][capacities[1] + 1][capacities[2] + 1];

        search();

        List<Integer> C_ValuesList = new ArrayList<Integer>(C_Values);
        Collections.sort(C_ValuesList);
        String result = "";
        for (int value : C_ValuesList) {
            result = result + " " + value;
        }
        out.println(result.trim());

        out.close();
    }

    public static void search() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j)
                    continue;

                int quantity = Math.min(capacities[j] - buckets[j], buckets[i]);
                if (quantity == 0)
                    continue;
                
                buckets[i] -= quantity;
                buckets[j] += quantity;

                if (visited[buckets[0]][buckets[1]][buckets[2]]) {
                    buckets[i] += quantity;
                    buckets[j] -= quantity;
                    continue;
                }
                
                visited[buckets[0]][buckets[1]][buckets[2]] = true;

                if (buckets[0] == 0)
                    C_Values.add(buckets[2]);

                search();

                buckets[i] += quantity;
                buckets[j] -= quantity;
            }
        }
    }

}
