package org.knowledge.finder.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StringUtilityTest {

    @Test
    void shouldKeepLettersAndNumbers() {
        String input = "Java123Spring";
        String expected = "Java123Spring";

        assertEquals(expected,
                StringUtility.removeSpecialCharacters(input));
    }

    @Test
    void shouldKeepSpaces() {
        String input = "Java Spring Boot";
        String expected = "Java Spring Boot";

        assertEquals(expected,
                StringUtility.removeSpecialCharacters(input));
    }

    @Test
    void shouldKeepPeriods() {
        String input = "LESSON 1.1";
        String expected = "LESSON 1.1";

        assertEquals(expected,
                StringUtility.removeSpecialCharacters(input));
    }

    @Test
    void shouldKeepUnderscores() {
        String input = "Java_Spring_Boot";
        String expected = "Java_Spring_Boot";

        assertEquals(expected,
                StringUtility.removeSpecialCharacters(input));
    }

    @Test
    void shouldRemoveSpecialCharacters() {
        String input = "Java@Spring#Boot!";
        String expected = "JavaSpringBoot";

        assertEquals(expected,
                StringUtility.removeSpecialCharacters(input));
    }


    @Test
    void shouldReturnEmptyStringWhenInputContainsOnlySpecialCharacters() {
        String input = "@#$%^&*!";

        assertEquals("",
                StringUtility.removeSpecialCharacters(input));
    }

    @Test
    void shouldPreserveEmptyString() {
        assertEquals("",
                StringUtility.removeSpecialCharacters(""));
    }
    
    @Test
    void shouldKeepHyphens() {
        String input = "LESSON-1 - Java-Spring-Boot";
        String expected = "LESSON-1 - Java-Spring-Boot";

        assertEquals(expected,
                StringUtility.removeSpecialCharacters(input));
    }
    
}

