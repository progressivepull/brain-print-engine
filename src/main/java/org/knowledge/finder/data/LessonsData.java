package org.knowledge.finder.data;

import java.util.HashMap;
import java.util.Map;

public class LessonsData {
	
    private String projectName;
    private final Map<Integer, String> titles = new HashMap<>();
    private final Map<String, String> lessons = new HashMap<>();
    
    private Integer section = 1;
    private String subSection = "1.1";
    
	
    private Map<Integer, Integer> lessonsCount = new HashMap<>();
    
    private int whole;
	private int decimal;
	

	public enum DecimalPoint {
		WHOLE,
		DECIMAL
	}

    public LessonsData(String projectName) {
    	this.projectName = sanitize(projectName);
    }
    
    public Map<Integer, Integer> getLessonsCount() {return lessonsCount;}
	public void setLessonsCount(Map<Integer, Integer> lessonsCount) {this.lessonsCount = lessonsCount;}

    public String getProjectName() {return projectName;}
    public void setProjectName(String projectName) {this.projectName = sanitize(projectName);}
    
    public void setTitle(String titleSection) {
    	titles.put(section, sanitize(titleSection));
    	section++;
    }
    
    public Map<Integer, String> getTitles() { return titles;}
    

    public void setLessons(String lessonContent, boolean isNewSection) {
    	
    	if(isNewSection) {
    		increaseSubSection(DecimalPoint.WHOLE);
    	}
    	lessons.put(subSection, sanitize(lessonContent));
    	increaseSubSection(  DecimalPoint.DECIMAL  );
    }
    
    public Map<String, String> getLessons() { return lessons;}
    
    private void getSubSection() {
    	
        String[] parts = subSection.split("\\.");
        if (parts.length == 2) {
        	whole = Integer.parseInt(parts[0]);
            decimal = Integer.parseInt(parts[1]);
            
        } else {
            System.out.println("Input not in expected format."); // Consider throwing an exception here instead of printing to console.
        }
    }

    private void increaseSubSection(DecimalPoint decimalPoint) {
    	
    	getSubSection();
		
		if(decimalPoint == DecimalPoint.DECIMAL) {
			decimal++;
		} else if(decimalPoint == DecimalPoint.WHOLE) {
			
			lessonsCount.put(whole, decimal -1); // Stores the count of lessons in the completed section
			whole++;
			decimal = 1;
		}else {
			//TODO: throw error // Consider throwing an IllegalArgumentException for invalid DecimalPoint values.
		}
        
        subSection = whole + "." + decimal;
    	
    }
    
    public void done() {
    	lessonsCount.put(whole, decimal -1);
    }


    private String sanitize(String input) {
        return input == null ? "" : input.trim().replace(" ", "_"); // Complete the sanitize method
    }
    
    public void printTitles() {
    	
    	System.out.println("TITLES _________________");
    	
        for (Map.Entry<Integer, String> entry : titles.entrySet()) {
        	
        	System.out.println(entry.getKey() +":" + entry.getValue() );
        }
    }
    
    public void printLessons() {
    	
    	System.out.println("LESSONS _________________");
    	
        for (Map.Entry<String, String> entry : lessons.entrySet()) {
        	
        	System.out.println(entry.getKey() +":" + entry.getValue() );
        }
    }
    
    public void printLessonsCount() {
    	
    	System.out.println("LESSONS COUNT_________________");
    	
        for (Map.Entry<Integer, Integer> entry : lessonsCount.entrySet()) {
        	
        	System.out.println(entry.getKey() +":" + entry.getValue() );
        }
    }
}
