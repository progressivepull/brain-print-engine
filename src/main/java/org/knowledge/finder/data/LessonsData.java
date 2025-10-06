package org.knowledge.finder.data;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code LessonsData} class is responsible for storing and managing lesson
 * structures (titles, subsections, and counts) for a project.
 * <p>
 * It organizes lessons into sections and subsections with numbering like:
 * <pre>
 * # Title 1
 * Lesson 1.1
 * Lesson 1.2
 * # Title 2
 * Lesson 2.1 
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

	/**
	 * The sanitized name of the project, suitable for safe usage in file names,
	 * URLs, or identifiers. This value is typically derived from user input and
	 * cleaned to remove unsafe characters.
	 */
	private String projectName;

	/**
	 * A mapping of section numbers to their corresponding formatted titles.
	 * Each entry represents a major section in the project, with the key being
	 * the section number (e.g., 1, 2) and the value being the title string
	 * (e.g., "# 1. Introduction").
	 */
	private final Map<Integer, String> titles = new HashMap<>();

	/**
	 * A mapping of subsection identifiers to their lesson content.
	 * Keys follow a hierarchical format (e.g., "1.1", "2.3") representing
	 * the subsection's position, and values contain the associated lesson text.
	 */
	private final Map<String, String> lessons = new HashMap<>();

	/**
	 * Tracks the current section number in the project.
	 * This counter is incremented whenever a new section is added via
	 * {@code setTitle()} or similar methods.
	 */
	private Integer section = 0;

	/**
	 * Represents the current subsection pointer in the format "X.Y",
	 * where X is the section number and Y is the subsection index.
	 * Used to organize and reference lesson content hierarchically.
	 */
	private String subSection = "0.1";

	/**
	 * Flag indicating whether a new section was recently added.
	 * This is typically set to {@code true} after a call to {@code setTitle()},
	 * and can be used to trigger formatting or layout changes.
	 */
	boolean isNewSection = false;
	/**
	 * Tracks the number of lessons within each section.
	 * The key represents the section number (e.g., 1, 2), and the value indicates
	 * how many lessons are currently associated with that section.
	 * Useful for generating lesson indices and validating content completeness.
	 */
	private Map<Integer, Integer> lessonsCount = new HashMap<>();

	/**
	 * Represents the integer part of the current subsection identifier.
	 * Parsed from the {@code subSection} string (e.g., "2.3" → whole = 2),
	 * this value helps organize content hierarchically by major section.
	 */
	private int whole;
	
	

	/**
	 * Retrieves the current subsection identifier.
	 * <p>
	 * The subsection is typically formatted as "X.Y", where:
	 * <ul>
	 *   <li><strong>X</strong> represents the section number</li>
	 *   <li><strong>Y</strong> represents the lesson index within that section</li>
	 * </ul>
	 * This value is used to organize lesson content hierarchically.
	 *
	 * @return the current subsection string (e.g., "2.3")
	 */
	public String getSubSection() {
	    return subSection;
	}

	/**
	 * Sets the current subsection identifier.
	 * <p>
	 * The expected format is "X.Y", where X is the section number and Y is the lesson index.
	 * This method updates the internal pointer used for lesson organization.
	 *
	 * @param subSection the subsection string to set (e.g., "3.1")
	 */
	public void setSubSection(String subSection) {
	    this.subSection = subSection;
	}
	
	


	/**
	 * Represents the fractional part of the current subsection identifier.
	 * Parsed from the {@code subSection} string (e.g., "2.3" → decimal = 3),
	 * this value helps track the position of a lesson within its section.
	 */
	private int decimal;


    /**
     * Enumeration for controlling how the subsection counter should increment.
     * <ul>
     *   <li>{@code WHOLE} → Increment section number, reset subsection to 1</li>
     *   <li>{@code DECIMAL} → Increment subsection number only</li>
     * </ul>
     */
    public enum DecimalPoint {
        /**
         * Increment section number, reset subsection to 1.
         */
        WHOLE,
        /**
         * Increment subsection number only.
         */
        DECIMAL,
        /**
		 * When you want to update both the whole and decimal values from the subSection, but not return any value.
		 */
        RETURN_NOTHING
    }

    /**
     * Constructs a new {@code LessonsData} instance for the given project.
     *
     * @param projectName the name of the project (will be sanitized)
     */
    public LessonsData(String projectName) {
        this.projectName = replaceSpacesWithUnderline(projectName);
    }

    /**
     * Returns the mapping of section numbers to their corresponding lesson counts.
     * Each entry represents how many lessons are present in a given section.
     *
     * @return a map of section numbers to lesson counts
     */
    public Map<Integer, Integer> getLessonsCount() {
        return lessonsCount;
    }

    /**
     * Sets the mapping of section numbers to their corresponding lesson counts.
     * This replaces the current {@code lessonsCount} map with the provided one.
     *
     * @param lessonsCount a map of section numbers to lesson counts
     */
    public void setLessonsCount(Map<Integer, Integer> lessonsCount) {
        this.lessonsCount = lessonsCount;
    }

    /**
     * Retrieves the sanitized project name.
     * This name is safe for use in identifiers, file paths, or display.
     *
     * @return the sanitized project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the project name and applies sanitization to ensure safe usage.
     * Sanitization may remove or replace unsafe characters.
     *
     * @param projectName the raw project name to sanitize and store
     */
    public void setProjectName(String projectName) {
        this.projectName = replaceSpacesWithUnderline(projectName);
    }

    /**
     * Indicates whether a new section was recently added.
     * Useful for triggering layout or formatting changes.
     *
     * @return {@code true} if a new section was added; {@code false} otherwise
     */
    public boolean getIsNewSection() {
        return isNewSection;
    }
    

    /**
     * Sets the flag indicating whether a new section was recently added.
     *
     * @param isNewSection {@code true} to mark a new section; {@code false} otherwise
     */
    public void setIsNewSection(boolean isNewSection) {
        this.isNewSection = isNewSection;
    }
    
    /**
     * Returns the mapping of section numbers to their corresponding formatted titles.
     * Each entry represents a major section in the project.
     *
     * @return map of section titles
     */
    public Map<Integer, String> getTitles() { return titles; }
    
    /**
     * Returns the mapping of subsection identifiers to their lesson content.
     * Keys follow a hierarchical format (e.g., "1.1", "2.3") representing
     * the subsection's position, and values contain the associated lesson text.
     *
     * @return map of lessons (subsection → lesson text)
     */
    public Map<String, String> getLessons() { return lessons; }

    /**
     * Adds a new section title.
     * <p>
     * Automatically increments the section number and prefixes the title with it.
     * <p>
     * Takes <b>titleSection</b> changes to "# 1. Introduction" by adding "1."
     *
     * @param titleSection raw section title (e.g., "# Introduction")
     */
    public void setTitle(String titleSection) {
        section++;
        // Add section number before the title
        String title = titleSection.replace("#", "# " + section + ".");
        titles.put(section, title);
        isNewSection = true;
    }



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
        String lesson = subSection + " " + replaceSpacesWithUnderline(lessonContent);
        lessons.put(subSection, lesson);

        // Increment for next lesson (X.Y+1)
        increaseSubSection(DecimalPoint.DECIMAL);
    }




    /**
	 * Splits the current subsection string into its whole and decimal parts.
	 * <p>
	 * The subsection is expected to be in the format "X.Y", where:
	 * <ul>
	 *   <li><strong>X</strong> is the whole part (section number)</li>
	 *   <li><strong>Y</strong> is the decimal part (lesson index)</li>
	 * </ul>
	 * This method parses these parts and updates the internal {@code whole} and {@code decimal} fields.
	 *
	 * @param decimalPoint specifies which part to return: WHOLE, DECIMAL, or RETURN_NOTHING
	 * @return the requested part as an integer, or -1 if RETURN_NOTHING is specified
	 */
    public int splitSubSection(DecimalPoint decimalPoint) {
        String[] parts = subSection.split("\\.");
        if (parts.length == 2) {
            whole = Integer.parseInt(parts[0]);
            decimal = Integer.parseInt(parts[1]);
        } else {
            throw new IllegalArgumentException("subSection is not in expected format: " + subSection);
        }
        
        if (decimalPoint == DecimalPoint.WHOLE) {
            return whole;
        } else if (decimalPoint == DecimalPoint.DECIMAL) {
            return decimal;
        }
        return -1;
    }

    /**
     * Increments the subsection depending on the given {@link DecimalPoint}.
     *
     * @param decimalPoint specifies whether to increment section (WHOLE) or subsection (DECIMAL)
     */
    private void increaseSubSection(DecimalPoint decimalPoint) {
    	splitSubSection(DecimalPoint.RETURN_NOTHING);

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
     * Replace spaces with underline in a string by:
     * <ul>
     *   <li>Replacing spaces with underline</li>
     *   <li>Trimming whitespace</li>
     *   <li>Handling null inputs safely</li>
     * </ul>
     *
     * @param input raw string
     * @return string with underline replacing the spaces in the string
     */
	
	public String replaceSpacesWithUnderline(String input) { 
		  return input == null ? "" :input.trim().replace(" ", "_"); 
	}
	
	/**
	 * Replace underscores with spaces in a string by:
	 * <ul>
	 *   <li>Replacing underscores with spaces</li>
	 *   <li>Trimming leading and trailing whitespace</li>
	 *   <li>Handling null inputs safely</li>
	 * </ul>
	 *
	 * @param input raw string
	 * @return string with spaces replacing the underscores in the string
	 */
	public String replaceUnderlineWithSpaces(String input) {
	    return input == null ? "" : input.trim().replace("_", " ");
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