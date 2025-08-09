package org.knowledge.finder.generate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList; // imports the ArrayList class
import java.util.List; // imports the List interface

import org.knowledge.finder.data.LessonsData;

public class ContextGenerate {
    private static final Logger logger = Logger.getLogger(ContextGenerate.class.getName());

    private String inputFileName;
    private String outputFileName;
    private String projectName;
    
    private Path directoryProject;	
    
    private LessonsData lessonsData;
    
    private List<String> folderNamesList = new ArrayList<>();
    
    private String lessonFolderName;
    
    private String contextLinkPage;

    public ContextGenerate(String inputFileName, String outputFileName, String projectNameWithDash,LessonsData lessonsData, String lessonFolderName,String contextLinkPage) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.projectName = projectNameWithDash;
        this.lessonsData =  lessonsData;
        this.lessonFolderName = lessonFolderName;
        this.contextLinkPage = contextLinkPage;
    }

    public String getInputFileName() {return inputFileName;}
    public void setInputFileName(String inputFileName) {this.inputFileName = inputFileName;}

    public String getOutputFileName() {return outputFileName;}
    public void setOutputFileName(String outputFileName) {this.outputFileName = outputFileName;}

    public String getProjectName() {return projectName;}
    public void setProjectName(String projectName) {this.projectName = projectName;}
    
    
    public Path getDirectoryProject() {return directoryProject;}
	public void setDirectoryProject(Path directoryProject) {this.directoryProject = directoryProject;}

	public LessonsData getLessonsData() {return lessonsData;}
	public void setLessonsData(LessonsData lessonsData) {this.lessonsData = lessonsData;}

	public boolean isSectionHeading(String line) {
        return line.startsWith("# ");
    }

    public void process() {
    	
    	  createDir();
    	  Path contextFile = directoryProject.resolve("context.md");
    	
		  
		  try ( BufferedReader reader = new BufferedReader(new FileReader(this.inputFileName ));
		  BufferedWriter writer = new BufferedWriter(new FileWriter( contextFile.toFile() )) )
		  { 
			  
			  String line; 
			  while ((line = reader.readLine()) != null) { 
				  
				  if(isSectionHeading(line)) { 
					 lessonsData.setTitle(line); 
			      }else {
			    	 lessonsData.setLessons(line);
			      }
		      }
			  
			  lessonsData.done();			  

			  //lessonsData.printLessonsCount();
			  //lessonsData.printLessons();
			  //lessonsData.printTitles();

			  for(int sectionNumber=1; sectionNumber < lessonsData.getLessonsCount().size(); sectionNumber++) {
				  
				  handleHeading(writer,sectionNumber);
				  handleLesson(writer, sectionNumber);
				  
			  }
				

			  
			  //logger.info("File converted successfully!");

		  } catch  (IOException e) { 
			   logger.log(Level.SEVERE, "Error during file conversion: " + e.getMessage(), e); 
		  }
		  
		  createSubfoldersAndMarkdownFiles();
		  
		  
		 
    }
    
    public void createDir() {
    	
        directoryProject = Paths.get(projectName);

        try {
            Files.createDirectories(directoryProject);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println("Project Directory created: " + directoryProject.toAbsolutePath());
    	
    }
    
    public void handleHeading(BufferedWriter writer, Integer sectionNumber) throws IOException {
    	
    	folderNamesList.add(this.lessonFolderName + sectionNumber);
    	
    	String line = lessonsData.getTitles().get(sectionNumber);

        writer.write(line + "\n \r");
    }

    public void handleLesson(BufferedWriter writer, Integer sectionNumber) throws IOException {
    	
    	
    	int subSectionCount = lessonsData.getLessonsCount().get(sectionNumber);
    	
    	for(int subSectionNumber=1; subSectionNumber < subSectionCount; subSectionNumber++) {
    		
    		String line = lessonsData.getLessons().get(sectionNumber+"."+subSectionNumber);
        	String markDownLine = "* [ " + line.replace("_", " ") +" ]( ./"+lessonFolderName + sectionNumber +"/"+ line.replace(" ", "_") + ".md )";
        	
            writer.write(markDownLine+ "\n \r");
    	}

    }
    
    public void createSubfoldersAndMarkdownFiles() {
     

        for (String folderName : folderNamesList) {
        	Path subfolderPath = directoryProject.resolve(folderName);
        	
        	try {
                Files.createDirectories(subfolderPath);
                System.out.println("Subfolder created: " + subfolderPath.toAbsolutePath());
                
            	System.out.println("Folder Name: ****" + folderName);
            	
            	int sectionNumber = Integer.parseInt(folderName.replace(this.lessonFolderName, ""));
            	
            	System.out.println(" Lesson Number : ****" +  sectionNumber);
            	
            	int subSectionCount = lessonsData.getLessonsCount().get(sectionNumber);
            	
            	for(int subSectionNumber=1; subSectionNumber <= subSectionCount; subSectionNumber++) {
            		
            		String line = lessonsData.getLessons().get(sectionNumber+"."+subSectionNumber);
            		
            		System.out.println(line);
            		
                    String fileName = line.replace(" ", "_") + ".md";
                    Path markdownFilePath = subfolderPath.resolve(fileName);
                    String fileContent = "# " + fileName.replace("_", " ") + " \n \r \n \r " + contextLinkPage;

                    // Use try-with-resources for BufferedWriter and FileWriter
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(markdownFilePath.toFile()))) { 
                        writer.write(fileContent);
                        System.out.println("Markdown file created and content written using BufferedWriter: " + markdownFilePath.toAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            	}
                
                
        	}catch (IOException e) {
                e.printStackTrace();
            }
        	
        }
    }


}