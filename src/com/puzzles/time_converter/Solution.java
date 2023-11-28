package com.puzzles.time_converter;
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
     * Complete the 'timeConversion' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String timeConversion(String s) {
        // Write your code here
        if(!s.startsWith("12") && s.contains("PM") || s.contains("pm")) {
            String[] time_parts = s.split(":");
            int time = Integer.valueOf(time_parts[0]);
            int new_time = time + 12;
            s = String.valueOf(new_time) + ":" + time_parts[1] + ":" + time_parts[2];
        }

        if(s.startsWith("24")) {
            s = s.replaceFirst("24", "00");
        } else if(s.startsWith("12") && (s.contains("AM") || s.contains("am"))) {
            s = s.replaceFirst("12", "00");
        }
        s = s.substring(0, s.length() - 2);
        return s;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.timeConversion(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

