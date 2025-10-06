package org.knowledge.finder.generate;

import java.util.logging.Logger;

import org.knowledge.finder.data.LessonsData;

/**
 * The {@code ManagerGenerate} class coordinates the generation of lessons and context files.
 * <p>
 * It acts as a high-level manager, initializing the required data structures and delegating
 * the processing workflow to {@link ContextGenerate}. This class is typically used as the main
 * entry point for generating project content from input files.
 * </p>
 */
public class ManagerGenerate {
	private static final Logger logger = Logger.getLogger(ManagerGenerate.class.getName());
	
	private LessonsData lessonsData;
	
	private ContextGenerate contextGenerate;
	
	/**
	 * Constructs a {@code ManagerGenerate} instance with all required configuration parameters.
	 * <p>
	 * This constructor initializes the lesson data and context generator, preparing the system
	 * for processing input files and generating output content.
	 * </p>
	 *
	 * @param archivefiles    array of file names to be archived after processing
	 * @param archiveDir      directory where archived files will be stored
	 * @param inputFile       input file containing raw lesson or webpage content
	 * @param outputFile      output file for processed context
	 * @param projectName     name of the project (used as root directory)
	 * @param lessonFolderName prefix for lesson folder names
	 * @param contextLinkPage hyperlink reference to the context file for generated lessons
	 */
	public ManagerGenerate(String[] archivefiles, String archiveDir,String inputFile, String outputFile,String projectName, String lessonFolderName,String contextLinkPage) {
		
		String projectNameWithDash = projectName.replace(" ", "_");
		
		lessonsData = new LessonsData(projectNameWithDash);
		
		contextGenerate = new ContextGenerate(inputFile, outputFile, projectNameWithDash, lessonsData,lessonFolderName,contextLinkPage);
	}
	
	/**
	 * Executes the full generation workflow for lessons and context files.
	 * <p>
	 * This method delegates processing to the {@link ContextGenerate} instance, which handles
	 * reading input, generating output files, and organizing project structure.
	 * </p>
	 */
	public void process() {
		contextGenerate.process();	
	}
}