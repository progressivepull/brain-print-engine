package org.knowledge.finder.data.LessonsDataTest;

import org.junit.jupiter.api.Test;
import org.knowledge.finder.data.LessonsData;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@code LessonsData.splitSubSection()}.
 * These tests validate parsing behavior for extracting whole and decimal values
 * from subsection strings like "3.7", "4.12", etc.
 */
class SplitSubSectionTest {

	/**
	 * Unit test for {@link LessonsData#splitSubSection(DecimalPoint)} using {@code DecimalPoint.WHOLE}.
	 * <p>
	 * This test verifies that the method correctly extracts the whole number portion
	 * from a subsection string formatted as "whole.decimal".
	 * <p>
	 * Given the input "3.7", the expected whole value is {@code 3}.
	 * The test asserts that the returned value matches this expectation.
	 */
	@Test
	void testSplitSubSectionWhole() {
	    // Initialize LessonsData with a test project name
	    LessonsData data = new LessonsData("TestProject");

	    // Provide a subsection string in "whole.decimal" format
	    data.setSubSection("3.7");

	    // Extract the whole number portion (expected: 3)
	    int whole = data.splitSubSection(LessonsData.DecimalPoint.WHOLE);

	    // Validate that the extracted whole part matches expectation
	    assertEquals(3, whole, "Expected whole part to be 3 from subsection '3.7'");
	}

	/**
	 * Unit test for {@link LessonsData#splitSubSection(DecimalPoint)} using {@code DecimalPoint.DECIMAL}.
	 * <p>
	 * This test verifies that the method correctly extracts the decimal portion
	 * from a subsection string formatted as "whole.decimal".
	 * <p>
	 * Given the input "4.12", the expected decimal value is {@code 12}.
	 * The test asserts that the returned value matches this expectation.
	 */
    @Test
    void testSplitSubSectionDecimal() {
        LessonsData data = new LessonsData("TestProject");

        // Set subsection to "4.12" and extract the decimal part
        data.setSubSection("4.12");
        int decimal = data.splitSubSection(LessonsData.DecimalPoint.DECIMAL);

        // Expect the decimal part to be 12
        assertEquals(12, decimal, "Decimal part should be 12");
    }

    /**
	 * Unit test for {@link LessonsData#splitSubSection(DecimalPoint)} using {@code DecimalPoint.RETURN_NOTHING}.
	 * <p>
	 * This test verifies that the method returns a sentinel value when instructed
	 * to not extract any part of the subsection string.
	 * <p>
	 * Given the input "2.5", the expected return value is {@code -1} (indicating no extraction).
	 * The test asserts that the returned value matches this expectation.
	 */
    @Test
    void testSplitSubSectionReturnNothing() {
        LessonsData data = new LessonsData("TestProject");

        // Set subsection to "2.5" but request RETURN_NOTHING
        data.setSubSection("2.5");
        int result = data.splitSubSection(LessonsData.DecimalPoint.RETURN_NOTHING);

        // Expect -1 as a sentinel value for no extraction
        assertEquals(-1, result, "Should return -1 for RETURN_NOTHING");
    }


    /**
     * Unit test for {@link LessonsData#splitSubSection(DecimalPoint)} that verifies exception handling
     * when the subsection format is invalid.
     * <p>
     * This test ensures that the method throws an {@link IllegalArgumentException} when provided
     * with a malformed subsection string that does not follow the expected "whole.decimal" pattern.
     * <p>
     * Given the input {@code "invalid_format"} and a request for {@code DecimalPoint.WHOLE},
     * the method is expected to throw an exception with a message indicating the format issue.
     * The test asserts both the exception type and the presence of a descriptive error message.
     */
    @Test
    void testSplitSubSectionThrowsIllegalArgumentException() {
    	
    	// Create a new LessonsData instance with a sample project name
    	LessonsData data = new LessonsData("TestProject");

    	// Set an invalid subsection string that doesn't match the expected "whole.decimal" format
    	data.setSubSection("invalid_format");

    	// Assert that calling splitSubSection with DecimalPoint.WHOLE throws an IllegalArgumentException
    	IllegalArgumentException exception = assertThrows(
    	    IllegalArgumentException.class,
    	    () -> data.splitSubSection(LessonsData.DecimalPoint.WHOLE),
    	    "Expected splitSubSection to throw IllegalArgumentException for invalid format"
    	);

    	// Verify that the exception message contains the expected format warning
    	assertTrue(
    	    exception.getMessage().contains("subSection is not in expected format: invalid_format"),
    	    "Exception message should indicate the formatting issue"
    	);

    }
}
