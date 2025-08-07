package org.knowledge.finder.data;

import java.util.Map;

public class LessonsData {
	
    private String dir;
    private int section;
    private Map<Integer,String> lessonList;
    
	public LessonsData( String dir ) {
		this.dir = dir;
	}

	public String getDir() {return dir;}
	public void setDir(String dir) {this.dir = dir;}

	public int getSection() {return section;}
	public void setSection(int section) {this.section = section;}

	public Map<Integer, String> getLessonList() {return lessonList;}
	public void setLessonList(Map<Integer, String> lessonList) {this.lessonList = lessonList;}
	 
}
