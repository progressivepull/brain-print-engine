package org.knowledge.finder.data;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code LessonsData} class is responsible for storing and managing lesson
 * structures (titles, subsections, and counts) for a project.
 * <p>
 * It organizes lessons into sections and subsections with numbering like:
 * <pre>
 * # 1. Title
 * 1.1 Lesson
 * 1.2 Lesson
 * # 2. Title
 * 2.1 Lesson
 * ...
 * </pre>
 * <p>
 * This class supports:
 * <ul>
 *   <li>Automatic numbering of sections and subsections</li>
 *   <li>Tracking lesson counts per section</li>
 *   <li>Sanitization of input (spaces → underscores)</li>
 *   <li>Debug printing of titles, lessons, and counts</li>
 * </ul>
 */
public class LessonsData {

    /** The name of the project (sanitized for safe usage). */
    private String projectName;

    /** Stores section numbers mapped to their corresponding titles. */
    private final Map<Integer, String> titles = new HashMap<>();

    /** Stores subsections mapped to their lesson content. */
    private final Map<String, String> lessons = new HashMap<>();

    /** Current section counter. */
    private Integer section = 0;

    /** Current subsection pointer (e.g., "1.1", "2.3"). */
    private String subSection = "0.1";

    /** Indicates whether a new section was just added. */
    boolean isNewSection = false;

    public boolean isNewSection() {return isNewSection;}
	public void setNewSection(boolean isNewSection) {this.isNewSection = isNewSection;}

	/** Stores the number of lessons per section (section → count). */
    private Map<Integer, Integer> lessonsCount = new HashMap<>();

    /** Current "whole" section number parsed from subsection. */
    private int whole;

    /** Current "decimal" subsection number parsed from subsection. */
    private int decimal;

    /**
     * Enumeration for controlling how the subsection counter should increment.
     * <ul>
     *   <li>{@code WHOLE} → Increment section number, reset subsection to 1</li>
     *   <li>{@code DECIMAL} → Increment subsection number only</li>
     * </ul>
     */
    public enum DecimalPoint {
        WHOLE,
        DECIMAL
    }

    /**
     * Constructs a new {@code LessonsData} instance for the given project.
     *
     * @param projectName the name of the project (will be sanitized)
     */
    public LessonsData(String projectName) {
        this.projectName = sanitize(projectName);
    }

    /** @return map of lessons count (section → number of lessons) */
    public Map<Integer, Integer> getLessonsCount() { return lessonsCount; }

    /** @param lessonsCount map of lessons count to set */
    public void setLessonsCount(Map<Integer, Integer> lessonsCount) { this.lessonsCount = lessonsCount; }

    /** @return the project name */
    public String getProjectName() { return projectName; }

    /** @param projectName sets the project name (will be sanitized) */
    public void setProjectName(String projectName) { this.projectName = sanitize(projectName); }

    /**
     * Adds a new section title.
     * <p>
     * Automatically increments the section number and prefixes the title with it.
     *
     * @param titleSection raw section title (e.g., "# Introduction")
     */
    public void setTitle(String titleSection) {
        section++;
        // Add section number before the title
        String title = sanitize(titleSection).replace("#", "# " + section + ".");
        titles.put(section, title.replace("_", " "));
        isNewSection = true;
    }

    /** @return map of section titles */
    public Map<Integer, String> getTitles() { return titles; }

    /**
     * Adds a lesson entry under the current section.
     * <p>
     * If a new section was just created, increments to the next "whole" before adding lessons.
     *
     * @param lessonContent raw lesson text
     */
    public void setLessons(String lessonContent) {
        // If it's a new section, reset subsection to start (X.1)
        if (isNewSection) {
            increaseSubSection(DecimalPoint.WHOLE);
            isNewSection = false;
        }

        // Format lesson with subsection numbering
        String lesson = subSection + " " + sanitize(lessonContent);
        lessons.put(subSection, lesson);

        // Increment for next lesson (X.Y+1)
        increaseSubSection(DecimalPoint.DECIMAL);
    }

    /** @return map of lessons (subsection → lesson text) */
    public Map<String, String> getLessons() { return lessons; }

    /**
     * Parses the current subsection string (e.g., "2.3") into {@code whole} and {@code decimal}.
     * Splits on the dot and updates the fields.
     */
    private void getSubSection() {
        String[] parts = subSection.split("\\.");
        if (parts.length == 2) {
            whole = Integer.parseInt(parts[0]);
            decimal = Integer.parseInt(parts[1]);
        } else {
            System.out.println("Input not in expected format."); 
            // ⚠️ Better: throw IllegalArgumentException instead of printing
        }
    }

    /**
     * Increments the subsection depending on the given {@link DecimalPoint}.
     *
     * @param decimalPoint specifies whether to increment section (WHOLE) or subsection (DECIMAL)
     */
    private void increaseSubSection(DecimalPoint decimalPoint) {
        getSubSection();

        if (decimalPoint == DecimalPoint.DECIMAL) {
            // Just increase subsection (e.g., 1.1 → 1.2)
            decimal++;
        } else if (decimalPoint == DecimalPoint.WHOLE) {
            // Save lesson count for finished section
            lessonsCount.put(whole, decimal - 1);
            // Move to next section and reset subsection
            whole++;
            decimal = 1;
        } else {
            // ⚠️ Consider throwing IllegalArgumentException
        }

        // Update subsection string
        subSection = whole + "." + decimal;
    }

    /**
     * Marks the end of lesson input and finalizes the last section's lesson count.
     */
    public void done() {
        lessonsCount.put(whole, decimal - 1);
    }

    /**
     * Sanitizes a string by:
     * <ul>
     *   <li>Replacing spaces with underscores</li>
     *   <li>Trimming whitespace</li>
     *   <li>Handling null inputs safely</li>
     * </ul>
     *
     * @param input raw string
     * @return sanitized string
     */
    private String sanitize(String input) {
        return input == null ? "" : input.trim().replace(" ", "_");
    }

    /**
     * Prints section titles to the console (for debugging).
     */
    public void printTitles() {
        System.out.println("TITLES _________________");
        for (Map.Entry<Integer, String> entry : titles.entrySet()) {
            System.out.println("KEY[" + entry.getKey() + "]: VALUE " + entry.getValue());
        }
    }

    /**
     * Prints lessons (subsections) to the console (for debugging).
     */
    public void printLessons() {
        System.out.println("LESSONS _________________");
        for (Map.Entry<String, String> entry : lessons.entrySet()) {
            System.out.println("KEY[" + entry.getKey() + "]: VALUE " + entry.getValue());
        }
    }

    /**
     * Prints the count of lessons per section to the console (for debugging).
     */
    public void printLessonsCount() {
        System.out.println("LESSONS COUNT _________________");
        for (Map.Entry<Integer, Integer> entry : lessonsCount.entrySet()) {
            if (entry.getKey() > 0) {
                System.out.println("KEY[" + entry.getKey() + "]:  VALUE " + entry.getValue());
            }
        }
    }
}
