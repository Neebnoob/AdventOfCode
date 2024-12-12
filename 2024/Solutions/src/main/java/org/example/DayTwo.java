package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwo {

    /*
        Approach for part one
        Loop through the report list and based on the first two values determine if increasing
        or decreasing.  To find out if it is safe get the difference of the two based on if it
        is increasing or decreasing and find if the sum is greater than 3 or less than 1. If not
        continue for that row.  If row finishes it is safe.

        Approach for part two
        We can have only one safe level, so I will implement a function that will determine if the
        level is enough and then will continue testing incrementing or decrementing.
     */

    public static void main(String[] args) {

        // 2d array for storing the reports
        ArrayList<ArrayList<Integer>> report = new ArrayList<>();

        // timers
        long startTime = 0;
        long endTime = 0;

        try {
            File file = new File("/Users/uv9380gh/GitClones/AdventOfCode/2024/Input/DayTwoInput.txt");
            Scanner scanner = new Scanner(file);
            // generate an array for each row in the .txt
            while (scanner.hasNextLine()) {
                ArrayList<Integer> row = new ArrayList<>();
                String line = scanner.nextLine();
                String[] lineArray = line.split(" ");
                for (String s : lineArray) {
                    row.add(Integer.parseInt(s));
                }
                report.add(row);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }

        startTime = System.nanoTime();
        partOne(report);
        endTime = System.nanoTime();
        System.out.println("Part one took " + (endTime - startTime)/1000000 + "ms.");

        startTime = System.nanoTime();
        partTwo(report);
        endTime = System.nanoTime();
        System.out.println("Part two took " + (endTime - startTime)/1000000 + "ms.");

    }

    public static void partOne (ArrayList<ArrayList<Integer>> report) {
        // answer
        int safeReports = 0;

        for (ArrayList<Integer> row : report) {
            if (row.get(0) > row.get(1)) {
                safeReports += decreasingRow(row);
            } else if (row.get(0) < row.get(1)){
                safeReports += increasingRow(row);
            }
        }

        System.out.println("There are " + safeReports + " safe reports");
    }

    public static void partTwo (ArrayList<ArrayList<Integer>> report) {
        // working parts
        // rows that have first two numbers equal
        // rows

        // answer
        int safeReports = 0;

        for (int i =0 ; i < report.size(); i++) {
            ArrayList<Integer> row = report.get(i);
            if (row.get(0) > row.get(1)) {
                safeReports += dampenerDecrease(row);
            } else if (row.get(0) < row.get(1)){
                safeReports += dampenerIncrease(row);
            } else {
                row.removeFirst();
                report.add(i, row);
            }
        }
        System.out.println("There are " + safeReports + " safe reports");
    }

    private static int increasingRow(ArrayList<Integer> row) {
        int prev = row.getFirst();
        for (int i = 1; i < row.size(); i++) {
            int curr = row.get(i);
            int sum = curr - prev;if (1 > sum || sum > 3) {
                return 0;
            }
            prev = curr;
        }
        return 1;
    }

    private static int decreasingRow(ArrayList<Integer> row) {
        int prev = row.getFirst();
        for (int i = 1; i < row.size(); i++) {
            int curr = row.get(i);
            int sum = prev - curr;
            if (1 > sum || sum > 3) {
                return 0;
            }
            prev = curr;
        }
        return 1;
    }

    private static int dampenerIncrease(ArrayList<Integer> row) {
        int prev = row.getFirst();
        boolean dampened = false;
        for (int i = 1; i < row.size(); i++) {
            int curr = row.get(i);
            int sum = curr - prev;
            if (1 > sum || sum > 3) {
                if (dampened) {
                    return 0;
                } else {
                    dampened = true;
                }
            } else {
                prev = curr;
            }
        }
        return 1;
    }

    private static int dampenerDecrease(ArrayList<Integer> row) {
        int prev = row.getFirst();
        boolean dampened = false;
        for (int i = 1; i < row.size(); i++) {
            int curr = row.get(i);
            int sum = prev - curr;
            if (1 > sum || sum > 3) {
                if (dampened) {
                    return 0;
                } else {
                    dampened = true;
                }
            } else {
                prev = curr;
            }
        }
        return 1;
    }
}
