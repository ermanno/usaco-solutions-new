
/*
ID: moser.el
LANG: JAVA
TASK: pprime
 */

import java.io.*;
import java.util.*;

class pprime {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        List<Integer> palindromicPrimes = getPalindromicPrimesBetweenTwoNumbers(start, end);
        Collections.sort(palindromicPrimes);
        
        for (Integer palindromicPrime : palindromicPrimes) {
            out.println(palindromicPrime);
        }
        
        out.close();
    }
    
    private static List<Integer> getPalindromicPrimesBetweenTwoNumbers(int start, int end) {
        int smallestNumOfDigits = String.valueOf(start).length();
        int biggestNumOfDigits = String.valueOf(end).length();
        
        List<Integer> palindromicPrimes = new ArrayList<Integer>();
        for (int i = smallestNumOfDigits; i <= biggestNumOfDigits; i++) {
            List<Integer> palindromes = generatePalindromes(i);
            for (int palindrome : palindromes) {
                if (palindrome >= start && palindrome <= end && isPrime(palindrome))
                    palindromicPrimes.add(palindrome);
            }
        }
        
        return palindromicPrimes;
    }
    
    private static boolean isPrime(int number) {
        for (int i = 3; i <= Math.sqrt(number); i++) { // I start from 3, since we won't be returning numbers divisible by 2
            if (number % i == 0)
                return false;
        }
        return true;
    }

    private static List<Integer> generatePalindromes(int numOfDigits) {
        List<Integer> palindromesToReturn = new ArrayList<Integer>();        
        
        // base case
        if (numOfDigits == 1)
            return new ArrayList<Integer>(Arrays.asList(5, 7)); // I return primes
                
        // recursion
        List<String> smallerPalindromes = generatePalindromicInnerString(numOfDigits - 2);
        for (String smallerPalindrome : smallerPalindromes) {
            for (int i = 1; i < 10; i = i + 2) { // I skip even numbers
                String newPalindrome = i + smallerPalindrome+ i;
                palindromesToReturn.add(Integer.parseInt(newPalindrome));
            }
        }
        
        return palindromesToReturn;
    }
    
    private static List<String> generatePalindromicInnerString(int numOfDigits) {
        List<String> palindromicInnerStrings = new ArrayList<String>();
        
        // base cases
        if (numOfDigits == 0)
            return new ArrayList<String>(Arrays.asList(""));
        
        if (numOfDigits == 1)
            return new ArrayList<String>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        
        // recursion
        List<String> smallerPalindromicSubstrings = generatePalindromicInnerString(numOfDigits - 2);
        for (String smallerPalindromicSubstring : smallerPalindromicSubstrings) {
            for (int i = 0; i < 10; i++) {
                palindromicInnerStrings.add(i + smallerPalindromicSubstring + i);
            }
        }
        
        return palindromicInnerStrings;
    }
}
