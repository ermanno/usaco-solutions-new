
/*
ID: moser.el
LANG: JAVA
TASK: gift1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

class gift1 {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

        int numOfFriends = Integer.parseInt(f.readLine());

        LinkedHashMap<String, Integer> friends = new LinkedHashMap<String, Integer>(numOfFriends);
        for (int i = 0; i < numOfFriends; i++) {
            friends.put(f.readLine(), 0);
        }

        String giver = "";
        while ((giver = f.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int initialAmount = Integer.parseInt(st.nextToken());
            int numOfReceivers = Integer.parseInt(st.nextToken());

            if (numOfReceivers == 0)
                continue;

            int amountPerReceiver = initialAmount / numOfReceivers;
            for (int i = 0; i < numOfReceivers; i++) {
                String receiver = f.readLine();
                friends.put(receiver, friends.get(receiver) + amountPerReceiver);
                friends.put(giver, friends.get(giver) - amountPerReceiver);
            }
        }

        for (Map.Entry<String, Integer> friend : friends.entrySet()) {
            out.println(friend.getKey() + " " + friend.getValue());
        }

        out.close();
    }
}
