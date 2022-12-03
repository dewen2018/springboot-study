package com.dewen.codeBlock;

import java.time.DayOfWeek;

/**
 * @author dewen
 * @date 2022/12/2 20:33
 */
public class Examples {
    public record Person(String name, String address) {


    }

    public static void main(String[] args) {
        // Text Blocks
        String textBlock = """
                Hello, this is a
                multi-line
                text block.
                """;
        System.out.println(textBlock);
        // Switch Expressions
        DayOfWeek day = DayOfWeek.FRIDAY;
        int numOfLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
        };
        System.out.println(numOfLetters);

        // Pattern Matching
        Object obj = "new Object()";
        if (obj instanceof String s) {
            System.out.println(s.toLowerCase());
        }


    }

    // static double getDoubleUsingSwitch(Object o) {
    //     return switch (o) {
    //         case Integer i -> i.doubleValue();
    //         case Float f -> f.doubleValue();
    //         case String s -> Double.parseDouble(s);
    //         default -> 0d;
    //     };
    // }

    // public abstract sealed class Pet permits Dog, Cat {
    // }
}
