package org.knowledge.finder.regular.expression;

import java.util.regex.*;

public class VideoDurationMatcher {
    public static void main(String[] args) {
        String[] inputs = {
            "10m 25s video",
            "1m 5s video",
            "27s video",
            "15s video"
        };

        String pattern = "^(?:(\\d+)m\\s*)?(\\d+)s\\s+video$";
        Pattern compiledPattern = Pattern.compile(pattern);

        for (String input : inputs) {
            Matcher matcher = compiledPattern.matcher(input);

            if (matcher.find()) {
                String minutes = matcher.group(1); // May be null
                String seconds = matcher.group(2);

                System.out.println("🎬 Input: " + input);
                System.out.println("⏱ Minutes: " + (minutes != null ? minutes : "0"));
                System.out.println("⏱ Seconds: " + seconds);
                System.out.println("---------------------------");
            } else {
                System.out.println("❌ No match for: " + input);
            }
        }
    }
}

