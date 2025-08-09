package org.knowledge.finder.regular.expression;

import java.util.regex.*;

public class RegexExtract {
    public static void main(String[] args) {
        String input = "27s video";
        String pattern = "^(\\d+)s video$";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);

        if (matcher.find()) {
            String seconds = matcher.group(1); // Captured number
            System.out.println("⏱ Seconds: " + seconds);
        } else {
            System.out.println("❌ No match.");
        }
    }
}
