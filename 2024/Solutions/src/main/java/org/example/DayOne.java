package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayOne {

    /*
        Approach for part one
        We need to find the distance between values in each list after they have been
        sorted.  To start we will take the input and store the ints in two separate lists
        before iterating through them and updating our running sum.

        Approach for part two
        We need to count the occurrence of the same number in our right list and multiply the amount
        of occurrences by the number each time it appears in the left list.  Since the lists are
        already sorted we can iterate through the left one and for each unique number that appears
        count how many times it appears in the right list before iterating the left list and updating
        our new running sum.  After going over a full number we will move on to the next number
        present in the left list.
     */

    public static void main(String[] args) {
        ///Users/uv9380gh/GitClones/AdventOfCode/2024/Input

        // initializing left and right array lists
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();

        int runningSum = 0; // Part one answer

        try {
            // Store problem input file for use
            File file = new File("/Users/uv9380gh/GitClones/AdventOfCode/2024/Input/DayOneInput.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] numbers = line.split(" ");
                int leftInt = Integer.parseInt(numbers[0]);
                int rightInt = Integer.parseInt(numbers[numbers.length - 1]);
                leftList.add(leftInt);
                rightList.add(rightInt);
            }

            // Close scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        }

        Collections.sort(leftList);
        Collections.sort(rightList);

        for (int i = 0; i < leftList.size(); i++) {
            runningSum += Math.abs(rightList.get(i) - leftList.get(i));
        }

        System.out.println("The total distance between the two lists is: " + runningSum);

        int similarityScore = 0;// Part two answer
        Map<Integer, Integer> rightMap = new HashMap<Integer, Integer>();

        //iterate over right list and generate a map of each distinct values and its occurrences
        for (int val : rightList) {
            if (rightMap.containsKey(val)) {
                rightMap.put(val, rightMap.get(val) + 1);
            } else {
                rightMap.put(val, 1);
            }
        }

        for (int val : leftList) {
            if (rightMap.containsKey(val)) {
                similarityScore += val * rightMap.get(val);
            }
        }
        System.out.println("The similarity score of the two lists is: " + similarityScore);
    }
}