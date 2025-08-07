package org.knowledge.finder.generate;

import java.util.logging.Logger;

public class ManagerGenerate {
	private static final Logger logger = Logger.getLogger(ManagerGenerate.class.getName());
	
	private ContextGenerate contextGenerate;
	
	public ManagerGenerate(String[] archivefiles, String archiveDir,String inputFile, String outputFile,String projectName, String contextLinkPage) {
		contextGenerate = new ContextGenerate(inputFile, outputFile, projectName);
	}
	
	public void process() {
		contextGenerate.convert();	
	}
	

	
	

}
