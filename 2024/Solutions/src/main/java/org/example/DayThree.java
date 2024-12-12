package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {

    /*
        Approach for part one
        We need to build a string that consists of "mul(#,#)".  Our numbers can be between one and three digits long.
        Missing any characters will cause the string building to fail and to restart with the next valid starting
        character.  After getting a valid string we get its product and add it to our running sum.

        Approach for part two
        We need to watch for do() and don't() and to then activate a flag to either add to runSum or to not add based on
        the do and don't functions.
     */

    public static void main (String[] args) throws IOException {
        partOne(fileReader());
        partTwo(fileReader());
    }

    public static String fileReader () throws IOException {
        Path filePath = Path.of("/Users/uv9380gh/GitClones/AdventOfCode/2024/Input/DayThreeInput.txt");
        return String.join("", Files.readAllLines(filePath));
    }

    public static void partOne (String input) {
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(input);
        int runSum = 0;

        while (matcher.find()) {
            int firstInt = Integer.parseInt(matcher.group(1));
            int secondInt = Integer.parseInt(matcher.group(2));

            runSum += firstInt * secondInt;
        }

        System.out.println("The sum of part one is " + runSum);
    }

    public static void partTwo (String input) {
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(input);
        boolean addVal = true;
        int runSum = 0;

        while (matcher.find()) {
            if (matcher.group().equals("do()")) {
                addVal = true;
            } else if (matcher.group().equals("don't()")) {
                addVal = false;
            } else if (addVal) {
                int firstInt = Integer.parseInt(matcher.group(1));
                int secondInt = Integer.parseInt(matcher.group(2));

                runSum += firstInt * secondInt;
            }
        }
        System.out.println("The sum of part two is " + runSum);
    }

}
