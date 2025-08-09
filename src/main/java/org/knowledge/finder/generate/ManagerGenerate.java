package org.knowledge.finder.generate;

import java.util.logging.Logger;

import org.knowledge.finder.data.LessonsData;


public class ManagerGenerate {
	private static final Logger logger = Logger.getLogger(ManagerGenerate.class.getName());
	
	private LessonsData lessonsData;
	
	private ContextGenerate contextGenerate;
	
	public ManagerGenerate(String[] archivefiles, String archiveDir,String inputFile, String outputFile,String projectName, String lessonFolderName,String contextLinkPage) {
		
		String projectNameWithDash = projectName.replace(" ", "_");
		
		lessonsData = new LessonsData(projectNameWithDash);
		
		contextGenerate = new ContextGenerate(inputFile, outputFile, projectNameWithDash, lessonsData,lessonFolderName,contextLinkPage);
	}
	
	public void process() {
		contextGenerate.process();	
	}
	

	
	

}
