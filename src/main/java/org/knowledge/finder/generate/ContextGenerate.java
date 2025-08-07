package org.knowledge.finder.generate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.knowledge.finder.data.LessonsData;

public class ContextGenerate {
    private static final Logger logger = Logger.getLogger(ContextGenerate.class.getName());

    private String inputFileName;
    private String outputFileName;
    private String projectName;
    
    private File projectDir;
    private File inputFile;

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
    	
    	createDir();
    	
		/*
		 * createContext();
		 * 
		 * try ( BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		 * BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName)) )
		 * { String line; while ((line = reader.readLine()) != null) { if
		 * (isSectionHeading(line)) { handleHeading(line, writer); } else {
		 * handleLesson(line, writer); } } logger.info("File converted successfully!");
		 * } catch (IOException e) { logger.log(Level.SEVERE,
		 * "Error during file conversion: " + e.getMessage(), e); }
		 */
    }
    
    public void createDir() {
    	
        projectDir = new File(projectName.replace(" ", "_"));
        if (!projectDir.exists()) {
            boolean created = projectDir.mkdirs();
            if (!created) {
                logger.severe("Failed to create project directory: " + projectName);
                return;
            }
        }

        inputFile = new File(projectDir, inputFileName); 
    	
    }

    public void createContext() {
    	
    	
    	
        logger.info("Starting file conversion from '" + inputFileName + "' to '" + outputFileName + "' in project '" + projectName + "'");
        File projectDir = new File(projectName);
        if (!projectDir.exists()) {
            boolean created = projectDir.mkdirs();
            if (!created) {
                logger.severe("Failed to create directory: " + projectName);
            }
        }
    }

    public void handleLesson(String line, BufferedWriter writer) throws IOException {
        // Implement lesson line processing logic as needed
        writer.write(line);
        writer.newLine();
    }

    public void handleHeading(String line, BufferedWriter writer) throws IOException {
        // Implement heading line processing logic as needed
        writer.write(line);
        writer.newLine();
    }
}