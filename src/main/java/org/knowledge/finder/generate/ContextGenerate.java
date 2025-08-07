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


    public boolean isSectionHeading(String line) {
        return line.startsWith("# ");
    }


    public void process() {
       
        createContext();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));) {
        	
            String line;
            while ((line = reader.readLine()) != null) {
            	
                if (isSectionHeading(line)) {
                	
                	handleHeading();
                    
                } else {
                	handleLesson();
                } 

            }
        	
            logger.info("File converted successfully!");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error during file conversion: " + e.getMessage(), e);
        }
    }
    
    public void createContext() {
    	
    	logger.info("Starting file conversion from '" + inputFileName + "' to '" + outputFileName + "'");
    	
    }
    
    public void handleLesson() {
    	
    	
    	
    }
    
    public void handleHeading() throws IOException {
    	
    }
    

}

