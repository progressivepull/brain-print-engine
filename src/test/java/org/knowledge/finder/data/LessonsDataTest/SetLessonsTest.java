package org.knowledge.finder.data.LessonsDataTest;

import org.knowledge.finder.data.LessonsData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SetLessonsTest {
	
	private LessonsData data;
	
    @BeforeEach
    void setUp() {
    	data = new LessonsData("TEST_FILES");
        data.setSubSection("1.0"); // assuming setter exists or default value
        data.setIsNewSection(true); // assuming setter exists
    }

}
