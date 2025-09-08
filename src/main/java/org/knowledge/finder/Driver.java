package org.knowledge.finder;

import org.knowledge.finder.generate.ManagerGenerate;

public class Driver {

	public static void main(String[] args) {
		
		String[] archivefiles = { "conversion.log", "context.md", "application.log" };
		String archiveDir = "archive";

		String inputFile = "webpage.txt";
		String outputFile = "context.md";

		String projectName = "Learning_Ansible";
		
		String lessonFolderName = "LESSON_";

		String contextHyperLink= "## [Context](./../context.md)";
		
		ManagerGenerate contextGenerate = new ManagerGenerate( archivefiles, archiveDir, inputFile, outputFile, projectName,lessonFolderName,contextHyperLink);
		
		contextGenerate.process();
			
		
		

	}

}

