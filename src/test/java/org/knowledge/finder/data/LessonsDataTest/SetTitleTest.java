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
