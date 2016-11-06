
/*
ID: moser.el
LANG: JAVA
TASK: namenum
 */

import java.io.*;
import java.util.*;

class namenum {
    private static List<String> validNames = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
        BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

        String name;
        while ((name = dict.readLine()) != null) {
            validNames.add(name);
        }

        String brandNumber = f.readLine();

        boolean isAtLeastOneValidName = false;
        for (String validName : validNames) {
            if (isValidNameForBrandNumber(validName, brandNumber)) {
                if (!isAtLeastOneValidName)
                    isAtLeastOneValidName = true;
                out.println(validName);
            }
        }

        if (!isAtLeastOneValidName)
            out.println("NONE");

        out.close();
    }

    public static boolean isValidNameForBrandNumber(String name, String number) {
        String brandNumberCorrespondingToName = "";
        for (char c : name.toCharArray()) {
            if (c == 'A' || c == 'B' || c == 'C')
                brandNumberCorrespondingToName += "2";
            if (c == 'D' || c == 'E' || c == 'F')
                brandNumberCorrespondingToName += "3";
            if (c == 'G' || c == 'H' || c == 'I')
                brandNumberCorrespondingToName += "4";
            if (c == 'J' || c == 'K' || c == 'L')
                brandNumberCorrespondingToName += "5";
            if (c == 'M' || c == 'N' || c == 'O')
                brandNumberCorrespondingToName += "6";
            if (c == 'P' || c == 'R' || c == 'S')
                brandNumberCorrespondingToName += "7";
            if (c == 'T' || c == 'U' || c == 'V')
                brandNumberCorrespondingToName += "8";
            if (c == 'W' || c == 'X' || c == 'Y')
                brandNumberCorrespondingToName += "9";
        }

        if (brandNumberCorrespondingToName.equalsIgnoreCase(number))
            return true;
        else
            return false;
    }

}