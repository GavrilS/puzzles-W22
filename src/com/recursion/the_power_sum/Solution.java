package com.recursion.the_power_sum;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    /*
     * Complete the 'powerSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER X
     *  2. INTEGER N
     */

    public static int powerSum(int X, int N) {
        // Write your code here
        int currentCheckValue = 1;
        boolean flag = false;
        List<Integer> possibleCombinations = new ArrayList<Integer>();
        List<Integer> possibleResults = new ArrayList<Integer>();

        while(!flag) {
            double powerResult = Math.pow(currentCheckValue, N);
            if (powerResult > X) {
                break;
            } else if (powerResult == X) {
                possibleResults.add(currentCheckValue);
                System.out.println("Current value outside of recursive function is " + String.valueOf(currentCheckValue));
                flag = true;
            } else {
                possibleCombinations.add(currentCheckValue);
                currentCheckValue++;
            }
        }
//        List<List<Integer>> workingSolutions = new ArrayList<List<Integer>>();
        List<Integer> workingSolutions = new ArrayList<Integer>();
        checkPossibleSums(X, possibleCombinations, 0, 0, 0, workingSolutions, N);
        return possibleResults.size() + workingSolutions.size();
    }

    public static void checkPossibleSums(int X, List<Integer> possibleCombinations, int startIndex, int currentIndex, int currentCount,
                                        List<Integer> workingSolutions, int N) {
        if (currentCount == X) {
            System.out.printf("Current index is %s and current value is %s", String.valueOf(currentIndex), String.valueOf(currentCount));
            workingSolutions.add(1);
            return;
        }

        if (currentCount > X) {
            return;
        }

        for (int i = currentIndex; i < possibleCombinations.size(); i++) {
            currentCount += Math.pow(possibleCombinations.get(i), N);
            checkPossibleSums(X, possibleCombinations, 0, i++, currentCount, workingSolutions, N);
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int X = Integer.parseInt(bufferedReader.readLine().trim());

        int N = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.powerSum(X, N);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
        System.out.println(result);
//        bufferedWriter.close();
    }
}
