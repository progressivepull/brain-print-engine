package org.knowledge.finder.regular.expression;

import java.util.regex.*;

/**
 * The {@code VideoDurationMatcher} class demonstrates regular expression matching
 * and extraction for video duration strings in the format "Xm Ys video" or "Xs video".
 * <p>
 * It provides a static main method to test the pattern and print the extracted minutes and seconds values.
 * </p>
 */
public class VideoDurationMatcher {
    /**
     * Default constructor for {@code VideoDurationMatcher}.
     * <p>
     * This constructor is not intended to be used directly, as the class is designed
     * to operate via its static {@code main} method only.
     * </p>
     */
    public VideoDurationMatcher() {
        // No initialization required
    }

    /**
     * Entry point for testing video duration regex matching and extraction.
     * <p>
     * This method applies a regular expression to sample input strings and prints
     * the extracted minutes and seconds values if a match is found.
     * </p>
     *
     * @param args Command-line arguments (not used in this demonstration)
     */
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