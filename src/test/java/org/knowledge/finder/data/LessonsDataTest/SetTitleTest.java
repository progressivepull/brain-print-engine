package org.knowledge.finder.data.LessonsDataTest;

import org.knowledge.finder.data.LessonsData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class SetTitleTest {
	
	private LessonsData data;
	
    @BeforeEach
    public void setUp() {
    	data = new LessonsData("TEST_FILES");
    }
    
    
    /**
     * Tests the {@code setTitle} method of the {@code data} object to ensure it:
     * <ul>
     *   <li>Correctly increments the section count.</li>
     *   <li>Stores the formatted title in the {@code titles} map.</li>
     *   <li>Sets the {@code isNewSection} flag to {@code true}.</li>
     * </ul>
     *
     * <p>Initial state is verified to have no titles. After calling {@code setTitle("# Introduction")},
     * the method checks that the title map contains one entry with the expected formatted title
     * and that the section flag is updated appropriately.</p>
     */
    @Test
    public void testSetTitleIncrementsSectionAndStoresTitle() {
        // Initial state
        assertEquals(0, data.getTitles().size());

        // Call setTitle
        data.setTitle("# Introduction");

        // Verify section incremented
        assertEquals(1, data.getTitles().size());

        // Verify title stored correctly
        Map<Integer, String> titles = data.getTitles();
        assertEquals("# 1. Introduction", titles.get(1));

        // Verify isNewSection flag
        assertTrue(data.isNewSection());
    }

    /**
     * Verifies that multiple calls to {@code setTitle()} correctly increment the section counter
     * and store each title with the appropriate numbering.
     * <p>
     * This test ensures:
     * <ul>
     *   <li>Each call to {@code setTitle()} adds a new entry to the {@code titles} map.</li>
     *   <li>Titles are formatted with incremented section numbers (e.g., "# 1. Overview", "# 2. Details").</li>
     *   <li>The final size of the {@code titles} map reflects the number of calls made.</li>
     * </ul>
     * <p>
     * Console output is included for manual inspection of map size and title formatting.
     */
    @Test
    public void testSetTitleMultipleCalls() {
        data.setTitle("# Overview");
        data.setTitle("# Details");

        int size = data.getTitles().size();
        System.out.println("Size of the Title map: " + size);

        assertEquals(2, data.getTitles().size());

        System.out.println("Element 1 of the Title map is : " + data.getTitles().get(1));
        assertEquals("# 1. Overview", data.getTitles().get(1));

        System.out.println("Element 2 of the Title map is : " + data.getTitles().get(2));
        assertEquals("# 2. Details", data.getTitles().get(2));
    }

	
	

}
