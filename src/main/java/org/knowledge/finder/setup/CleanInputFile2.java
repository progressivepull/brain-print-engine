package org.knowledge.finder.setup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;

/**
 * Constructs a new {@code ClassName} instance.
 * <p>
 * This default constructor performs no initialization logic.
 * <p>
 * The {@code CleanInputFile2} class provides a utility for sanitizing raw input text
 * by filtering out lines that match specific exclusion patterns using regular expressions.
 * <p>
 * This tool is designed to clean scraped or copied webpage content before it is used
 * in lesson generation or markdown formatting.
 * </p>
 *
 * <h2>Use Case:</h2>
 * <ul>
 *   <li>Preprocessing unstructured text from web sources.</li>
 *   <li>Removing timestamped video references and UI artifacts.</li>
 *   <li>Preparing clean input for downstream educational content pipelines.</li>
 * </ul>
 *
 * <h2>Filtering Rules:</h2>
 * Lines are excluded if they match any of the following:
 * <ul>
 *   <li>Exact match for {@code "Save"}.</li>
 *   <li>Whitespace-only lines.</li>
 *   <li>Lines matching {@code ^\\d+s video$} (e.g., {@code "12s video"}).</li>
 *   <li>Lines matching {@code ^(?:(\\d+)m\\s*)?(\\d+)s\\s+video$} (e.g., {@code "1m 30s video, 30s video, 2m30s video (no space between m and s), 45s video (extra spaces before "video")"}).</li>
 * </ul>
 *
 * <p>
 * All excluded lines are printed to the console for manual inspection.
 * Cleaned lines are written to {@code "webpage.txt"}.
 * </p>
 *
 * @author John Smith
 * @version 1.0
 */
public class CleanInputFile2 {

    /**
     * Entry point for the file cleaning utility.
     * <p>
     * Reads from {@code "webpage-unclean.txt"}, applies regex-based filtering,
     * and writes the cleaned content to {@code "webpage.txt"}.
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Set input and output file paths
        String inputFile = "webpage-unclean.txt";
        String outputFile = "webpage.txt";

        System.out.println("_______________________________________________");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {

                // Regex patterns for exclusion
                String pattern = "^\\d+s video$";
                boolean isMatch = Pattern.matches(pattern, line);

                String pattern1 = "^(?:(\\d+)m\\s*)?(\\d+)s\\s+video$";
                boolean isMatch1 = Pattern.matches(pattern1, line);

                boolean excludeSuffix =
                    line.matches("\\s*") || // whitespace-only
                    line.equals("Save");    // exact match

                // Skip excluded lines, print them for review
                if (isMatch || excludeSuffix || isMatch1) {
                    System.out.println(line);
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            System.out.println("_______________________________________________");
            System.out.println("Filtering complete. Check " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
