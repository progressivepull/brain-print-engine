package org.knowledge.finder.regular.expression;

import java.util.regex.*;

/**
 * The {@code MatchVideoDuration} class demonstrates regular expression matching
 * for video duration strings in the format "Xm Ys video" (e.g., "3m 27s video").
 * <p>
 * It provides a static main method to test the pattern and print whether a match is found.
 * </p>
 */
public class MatchVideoDuration {
    /**
     * Default constructor for {@code MatchVideoDuration}.
     * <p>
     * This constructor is not intended to be used directly, as the class is designed
     * to operate via its static {@code main} method only.
     * </p>
     */
    public MatchVideoDuration() {
        // No initialization required
    }

    /**
     * Entry point for testing video duration regex matching.
     * <p>
     * This method applies a regular expression to a sample input string and prints
     * whether the input matches the expected video duration format.
     * </p>
     *
     * @param args Command-line arguments (not used in this demonstration)
     */
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