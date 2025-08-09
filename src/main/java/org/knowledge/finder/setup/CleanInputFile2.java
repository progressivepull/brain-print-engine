package org.knowledge.finder.setup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.regex.*;

public class CleanInputFile2 {

	public static void main(String[] args) {
        // Set input and output file paths
        String inputFile = "webpage-unclean.txt";
        String outputFile = "webpage.txt";
        
        System.out.println("_______________________________________________");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
            	
				/*
				 * boolean excludeSuffix = line.endsWith("s video") || line.startsWith("•") ||
				 * line.equals("Save") ;
				 */
            	String pattern = "^\\d+s video$";
            	
            	 boolean isMatch = Pattern.matches(pattern, line);
            	 
                 String pattern1 = "^(?:(\\d+)m\\s*)?(\\d+)s\\s+video$";

                 boolean isMatch1 = Pattern.matches(pattern1, line);
            	 
				 boolean excludeSuffix = 
				 line.matches("\\s*") ||
				 line.equals("Save") ;
            	
            	// DO not write this line to the file
            	if(isMatch || excludeSuffix || isMatch1) {
            		System.out.println( line );
            	}else {

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

