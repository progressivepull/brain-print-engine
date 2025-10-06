package org.knowledge.finder.data.LessonsDataTest;

import org.knowledge.finder.data.LessonsData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link LessonsData} class focusing on lesson insertion and section management.
 * <p>
 * This test class verifies the correct behavior of lesson storage, subsection incrementing,
 * and section state transitions when interacting with the LessonsData API.
 */
public class SetLessonsTest {
	
	private LessonsData data;
	
	/**
	 * Initializes the test environment before each test case.
	 * <p>
	 * This setup method creates a new {@link LessonsData} instance with the test directory
	 * {@code "TEST_FILES"} and configures its initial state:
	 * <ul>
	 *   <li>Sets the subsection pointer to {@code "1.0"} to simulate the starting position.</li>
	 *   <li>Marks the section as new by setting {@code isNewSection} to {@code true}.</li>
	 * </ul>
	 * <p>
	 * Ensures consistent baseline conditions for all test methods that rely on {@code LessonsData}.
	 */
	@BeforeEach
	void setUp() {
	    data = new LessonsData("TEST_FILES");
	    data.setSubSection("1.0"); // assuming setter exists or default value
	    data.setIsNewSection(true); // assuming setter exists
	}

    /**
     * Default constructor for SetLessonsTest.
     * <p>
     * This constructor is provided for test instantiation purposes. No custom initialization is required.
     */
    public SetLessonsTest() {
        // Default constructor for JUnit test class
    }

    
    /**
     * Tests the {@code setLessons()} method when invoked during a new section.
     * <p>
     * This test verifies that:
     * <ul>
     *   <li>The {@code isNewSection} flag is reset to {@code false} after lesson insertion.</li>
     *   <li>The lesson content is correctly stored in the {@code lessons} map.</li>
     *   <li>The subsection key follows the expected format (e.g., "2.1").</li>
     *   <li>The lesson value includes both the subsection and the input string.</li>
     *   <li>The {@code subSection} is incremented after storing the lesson (e.g., "2.2").</li>
     * </ul>
     * <p>
     * Assumes that {@code increaseSubSection(WHOLE)} sets the subsection to "2.1"
     * prior to lesson insertion.
     */
    @Test
    void testSetLessons_NewSection() {
        String input = "Intro to AI";
        data.setLessons(input);

        // Verify isNewSection is now false
        assertFalse(data.getIsNewSection());

        // Verify lesson is stored correctly
        Map<String, String> lessons = data.getLessons();
        assertEquals(1, lessons.size());

        // Assuming increaseSubSection(WHOLE) sets subSection to "2.1"
        assertTrue(lessons.containsKey("2.1"));
        System.out.println(lessons.get("2.1") );
        assertEquals("2.1 Intro_to_AI", lessons.get("2.1"));

        // Verify subSection incremented again after adding lesson
        assertEquals("2.2", data.getSubSection());
    }


}