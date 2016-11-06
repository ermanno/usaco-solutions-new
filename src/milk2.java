
/*
ID: moser.el
LANG: JAVA
TASK: milk2
 */

import java.io.*;
import java.util.*;

class Interval {
    int beginning;
    int end;

    public Interval(int beginning, int end) {
        this.beginning = beginning;
        this.end = end;
    }

    public Interval(Interval i) {
        this.beginning = i.beginning;
        this.end = i.end;
    }

    public int getLength() {
        return end - beginning;
    }
}

class milk2 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

        int N = Integer.parseInt(f.readLine());
        List<Interval> intervals = new ArrayList<Interval>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            Interval interval = new Interval(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            intervals.add(interval);
        }

        intervals.sort(new Comparator<Interval>() {

            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.beginning - o2.beginning;
            }

        });

        Interval maxWorking = new Interval(intervals.get(0));
        Interval currentWorking = new Interval(intervals.get(0));
        Interval maxNotWorking = new Interval(0, 0);

        for (int i = 1; i < N; i++) {
            Interval tempInt = intervals.get(i);

            // if the interval begins after the current interval
            if (currentWorking.end < tempInt.beginning) {
                // we have an interruption
                Interval interruption = new Interval(currentWorking.end, tempInt.beginning);

                // it could be longer than the last one
                if (maxNotWorking.getLength() < interruption.getLength())
                    maxNotWorking = new Interval(interruption);

                // the new interval is the current one, furthermore it could
                // already be longer than the max one
                currentWorking = new Interval(tempInt);
                if (maxWorking.getLength() < tempInt.getLength())
                    maxWorking = new Interval(tempInt);

                continue;
            }
            // if the interval does not begin after the current one, but ends
            // after the current one
            else if (tempInt.end > currentWorking.end) {
                currentWorking.end = tempInt.end;

                // we could be longer than the previous max working
                if (currentWorking.getLength() > maxWorking.getLength())
                    maxWorking = new Interval(currentWorking);
            }
        }

        out.println(maxWorking.getLength() + " " + maxNotWorking.getLength());

        out.close();
    }
}
