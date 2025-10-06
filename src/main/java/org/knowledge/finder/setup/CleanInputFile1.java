package org.knowledge.finder.setup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The {@code CleanInputFile} class provides a utility to sanitize raw input text
 * by filtering out unwanted lines based on specific patterns.
 * <p>
 * It reads from an unclean input file, removes lines that match exclusion criteria
 * (e.g., lines ending with "s video", starting with "•", or equal to "Save"),
 * and writes the cleaned content to a new output file.
 * </p>
 *
 * <h2>Use Case:</h2>
 * <ul>
 *   <li>Preprocessing scraped or copied webpage content before lesson generation.</li>
 *   <li>Removing UI artifacts, bullet points, or irrelevant media references.</li>
 * </ul>
 *
 * <h2>Filtering Rules:</h2>
 * <ul>
 *   <li>Exclude lines that end with {@code "s video"}.</li>
 *   <li>Exclude lines that start with a bullet {@code "•"}.</li>
 *   <li>Exclude lines that exactly match {@code "Save"}.</li>
 * </ul>
 *
 * <p>
 * All excluded lines are printed to the console for manual inspection.
 * </p>
 *
 * @author John Smith
 * @version 1.0
 */
public class CleanInputFile1 {

    /**
     * Entry point for the file cleaning utility.
     * <p>
     * Reads from {@code "webpage-unclean.txt"}, filters out unwanted lines,
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

                boolean excludeSuffix =
                    line.endsWith("s video") ||
                    line.startsWith("•") ||
                    line.equals("Save");

                // Do not write excluded lines to the output file
                if (excludeSuffix) {
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
