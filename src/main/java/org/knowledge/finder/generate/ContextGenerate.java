package org.knowledge.finder.generate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.knowledge.finder.data.LessonsData;


public class ContextGenerate {
	private static final Logger logger = Logger.getLogger(ContextGenerate.class.getName());
	
    private String inputFileName;
    private String outputFileName;
    private String projectName;
    
    private int sectionCounter = 1;
    private int subsectionCounter = 1;
    
   
    /**
     * Constructs a FileConverter with specified input and output file names.
     * @param inputFile  the input file path
     * @param outputFile the output file path
     * @param projectAuthorName  is the name of the Author and Folder where all folder and files are located
     */
    public ContextGenerate(String inputFileName, String outputFileName, String projectName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.projectName = projectName;
    }
    
  
	public String getInputFileName() {return inputFileName;}
	public void setInputFileName(String inputFileName) {this.inputFileName = inputFileName;}

	public String getOutputFileName() {return outputFileName;}
	public void setOutputFileName(String outputFileName) {this.outputFileName = outputFileName;}
	
    public String getProjectName() {return projectName;}
	public void setProjectName(String projectName) {this.projectName = projectName;}

	/**
     * Determines if the line is a section heading.
     * @param line the line to check
     * @return true if it's a section heading, false otherwise
     */
    public boolean isSectionHeading(String line) {
        return line.startsWith("# ");
    }



	/**
     * Starts the file conversion process.
     */
    public void convert() {
        logger.info("Starting file conversion from '" + inputFileName + "' to '" + outputFileName + "'");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
        	
            String line;
            while ((line = reader.readLine()) != null) {
            	
                if (isSectionHeading(line)) {
                	
                	String prefixLabel = line.replace("#", "#" + sectionCounter + "." );
                	
                	writer.write( prefixLabel + "\n \r");
                	sectionCounter++;
                    
                } else {
                	processLesson(line, writer);
                } 

            }
        	
            logger.info("File converted successfully!");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error during file conversion: " + e.getMessage(), e);
        }
    }
    
    public void processLesson(String line, BufferedWriter writer) {
    	
    	
    	subsectionCounter++;
    	
    }
    

}

