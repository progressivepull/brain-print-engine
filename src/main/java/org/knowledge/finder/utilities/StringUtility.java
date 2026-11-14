package org.knowledge.finder.utilities;

public class StringUtility {
	
	public static String removeSpecialCharacters(String input) {
	    if (input == null) {
	        return null;
	    }

	    return input.replaceAll("[^a-zA-Z0-9 ._-]", "");
	}

}
