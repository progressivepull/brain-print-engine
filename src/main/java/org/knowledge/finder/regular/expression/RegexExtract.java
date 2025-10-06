package org.knowledge.finder.regular.expression;

import java.util.regex.*;

/**
 * The {@code RegexExtract} class demonstrates regular expression extraction
 * for seconds from a video duration string in the format "Xs video" (e.g., "27s video").
 * <p>
 * It provides a static main method to test the pattern and print the extracted seconds value.
 * </p>
 */
public class RegexExtract {
    /**
     * Default constructor for {@code RegexExtract}.
     * <p>
     * This constructor is not intended to be used directly, as the class is designed
     * to operate via its static {@code main} method only.
     * </p>
     */
    public RegexExtract() {
        // No initialization required
    }

    /**
     * Entry point for testing video duration regex extraction.
     * <p>
     * This method applies a regular expression to a sample input string and prints
     * the extracted seconds value if a match is found.
     * </p>
     *
     * @param args Command-line arguments (not used in this demonstration)
     */
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