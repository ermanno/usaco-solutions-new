
/*
ID: moser.el
LANG: JAVA
TASK: test
 */

import java.io.*;
import java.util.*;

class test {
    public static void main(String[] args) throws IOException {
        // much faster than RandomAccessFile: why?
        BufferedReader f = new BufferedReader(new FileReader("test.in"));

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));

        // much faster than readLine/split: why?
        StringTokenizer st = new StringTokenizer(f.readLine());
        int i1 = Integer.parseInt(st.nextToken());
        int i2 = Integer.parseInt(st.nextToken());

        out.println(i1 + i2);
        out.close();
    }
}
