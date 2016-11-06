
/*
ID: moser.el
LANG: JAVA
TASK: combo
 */

import java.io.*;
import java.util.*;

class Combination {
    private int[] numbers = new int[3];

    public Combination(int first, int second, int third) {
        numbers[0] = first;
        numbers[1] = second;
        numbers[2] = third;
    }

    public int[] getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object obj) {
        Combination other = (Combination) obj;
        for (int i = 0; i < 3; i++) {
            if (numbers[i] != other.numbers[i])
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return numbers[0] * 100 + numbers[1] * 10 + numbers[2];
    }
}

class combo {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("combo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

        N = Integer.parseInt(f.readLine());

        StringTokenizer st = new StringTokenizer(f.readLine());
        Combination johnCombo = new Combination(Integer.parseInt(st.nextToken()),
                                                Integer.parseInt(st.nextToken()),
                                                Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(f.readLine());
        Combination masterCombo = new Combination(Integer.parseInt(st.nextToken()),
                                                  Integer.parseInt(st.nextToken()),
                                                  Integer.parseInt(st.nextToken()));

        // generate the various combinations
        List<List<Integer>> johnOptions = getOptions(johnCombo);
        List<List<Integer>> masterOptions = getOptions(masterCombo);

        Set<Combination> possibleCombos = new HashSet<Combination>();
        for (int first : johnOptions.get(0)) {
            for (int second : johnOptions.get(1)) {
                for (int third : johnOptions.get(2)) {
                    possibleCombos.add(new Combination(first, second, third));
                }
            }
        }
        for (int first : masterOptions.get(0)) {
            for (int second : masterOptions.get(1)) {
                for (int third : masterOptions.get(2)) {
                    possibleCombos.add(new Combination(first, second, third));
                }
            }
        }

        out.println(possibleCombos.size());

        out.close();
    }

    private static List<List<Integer>> getOptions(Combination combination) {
        int[] numbers = combination.getNumbers();
        List<List<Integer>> combinations = new ArrayList<List<Integer>>();
        for (int i = 0; i < 3; i++) {
            List<Integer> optionsForDial = new ArrayList<Integer>();
            optionsForDial.add(previous(previous(numbers[i])));
            optionsForDial.add(previous(numbers[i]));
            optionsForDial.add(numbers[i]);
            optionsForDial.add(next(numbers[i]));
            optionsForDial.add(next(next(numbers[i])));
            combinations.add(optionsForDial);
        }
        return combinations;
    }

    public static int previous(int number) {
        return (number == 1) ? N : number - 1;
    }

    public static int next(int number) {
        return (number == N) ? 1 : number + 1;
    }
}
