package org.knowledge.finder.regular.expression;

import java.util.regex.*;

public class MatchVideoDuration {
    public static void main(String[] args) {
        String input = "3m 27s video";
        String pattern = "^(?:(\\d+)m\\s*)?(\\d+)s\\s+video$";

        boolean isMatch = Pattern.matches(pattern, input);

        if (isMatch) {
            System.out.println("✅ Match found for: " + input);
        } else {
            System.out.println("❌ No match for: " + input);
        }
    }
}
