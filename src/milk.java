
/*
ID: moser.el
LANG: JAVA
TASK: milk
 */

import java.io.*;
import java.util.*;

class Farmer {
    public int pricePerUnit;
    public int numberOfUnits;

    public Farmer(int pricePerUnit, int numberOfUnits) {
        this.pricePerUnit = pricePerUnit;
        this.numberOfUnits = numberOfUnits;
    }
}

class milk {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int desiredAmountOfMilk = Integer.parseInt(st.nextToken());
        int numberOfFarmers = Integer.parseInt(st.nextToken());

        List<Farmer> farmers = new ArrayList<Farmer>(numberOfFarmers);

        for (int i = 0; i < numberOfFarmers; i++) {
            st = new StringTokenizer(f.readLine());
            farmers.add(new Farmer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(farmers, new Comparator<Farmer>() {

            @Override
            public int compare(Farmer o1, Farmer o2) {
                return o1.pricePerUnit - o2.pricePerUnit;
            }

        });

        int price = 0;
        for (Farmer farmer : farmers) {
            if (desiredAmountOfMilk <= 0)
                break;

            int amountToBuy = (farmer.numberOfUnits > desiredAmountOfMilk) ? desiredAmountOfMilk : farmer.numberOfUnits;
            desiredAmountOfMilk -= amountToBuy;
            price += amountToBuy * farmer.pricePerUnit;
        }

        out.println(price);

        out.close();
    }
}
