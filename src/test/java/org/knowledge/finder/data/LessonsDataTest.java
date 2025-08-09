package org.knowledge.finder.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LessonsDataTest {
	
	private static final Logger logger = LogManager.getLogger(LessonsDataTest.class);
	
    private LessonsData lessonsData;
	
    @BeforeEach
    void setUp() throws Exception {
    	
    	String projectName = "Project Name";

		
		lessonsData = new LessonsData(projectName);
		
		/*** Section 1 ***/
		
	    lessonsData.setTitle("# Section One"); 
		
		lessonsData.setLessons("Section One Chapter One Lesson Title");
			
		lessonsData.setLessons("Section One Chapter Two Lesson Title");
			  
		lessonsData.setLessons("Section One Chapter Three Lesson Title");
			 
		  
		  
		  /*** Section 2 ***/
		  
			
		lessonsData.setTitle("# Section Two");
			  
		lessonsData.setLessons("Section Two Chapter One Lesson Title");
			  
		lessonsData.setLessons("Section Two Chapter Two Lesson Title");
			  
		lessonsData.setLessons("Section Two Chapter Three Lesson Title");
			  
		lessonsData.setLessons("Section Two Chapter Four Lesson Title");
			  
		lessonsData.setLessons("Section Two Chapter Five Lesson Title");
			 
		
		lessonsData.done();
		
		lessonsData.printTitles();
		
		lessonsData.printLessons();
		
		lessonsData.printLessonsCount();

    }
    
    @Test
    void testSectOneTitle()  throws IOException {
    
    	System.out.println("*** testTitle ***");
    	
        String EXPECTED_SectOneTitle = "# 1._Section_One";
        String ACTUAL_SectOneTitle = lessonsData.getTitles().get(1);
        
        System.out.println("EXPECTED_SectOneTitle: " + EXPECTED_SectOneTitle);
        System.out.println("ACTUAL_SectOneTitle: " + ACTUAL_SectOneTitle);
        
        assertEquals(EXPECTED_SectOneTitle, ACTUAL_SectOneTitle);
        
        System.out.println("\n");
    }
    
    @Test
    void testSubSect_1_1_Lesson()  throws IOException {
    	
    	System.out.println("*** testSubSect_1_1_Lesson ***");
    	
    	String EXPECTED_SubSect_1_1_Lesson = "1.1 Section_One_Chapter_One_Lesson_Title";
    	String ACTUAL_SubSect_1_1_Lesson = lessonsData.getLessons().get("1.1");
    	
    	assertEquals(EXPECTED_SubSect_1_1_Lesson, ACTUAL_SubSect_1_1_Lesson);
    }
    
    @Test
    void testSubSect_1_2_Lesson()  throws IOException {
    	
    	System.out.println("*** testSubSect_1_2_Lesson ***");
    	
    	String EXPECTED_SubSect_1_2_Lesson = "1.2 Section_One_Chapter_Two_Lesson_Title";
    	String ACTUAL_SubSect_1_2_Lesson = lessonsData.getLessons().get("1.2");
    	
    	assertEquals(EXPECTED_SubSect_1_2_Lesson, ACTUAL_SubSect_1_2_Lesson);
    }
    
    @Test
    void testSubSect_1_3_Lesson()  throws IOException {
    	
    	System.out.println("*** testSubSect_1_3_Lesson ***");
    	
    	String EXPECTED_SubSect_1_3_Lesson = "1.3 Section_One_Chapter_Three_Lesson_Title";
    	String ACTUAL_SubSect_1_3_Lesson = lessonsData.getLessons().get("1.3");
    	
    	assertEquals(EXPECTED_SubSect_1_3_Lesson, ACTUAL_SubSect_1_3_Lesson);
    }
    
	
	@Test void testSectTwoTitle() throws IOException {
	  
	  System.out.println("*** testSectTwoTitle ***");
	  
	  String EXPECTED_SectTwoTitle = "# 2._Section_Two";
	  String ACTUAL_SectTwoTitle  = lessonsData.getTitles().get(2);
	  
	  System.out.println("EXPECTED_SectTwoTitle: " + EXPECTED_SectTwoTitle);
	  System.out.println("ACTUAL_SectTwoTitle: " + ACTUAL_SectTwoTitle);
	  
	  assertEquals(EXPECTED_SectTwoTitle, ACTUAL_SectTwoTitle);
	  
	  System.out.println("\n");
	  
	}
	
    @Test
    void testSubSect_2_1_Lesson()  throws IOException {
    	
    	System.out.println("*** testSubSect_2_1_Lesson ***");
    	
    	String EXPECTED_SubSect_2_1_Lesson = "2.1 Section_Two_Chapter_One_Lesson_Title";
    	String ACTUAL_SubSect_2_1_Lesson = lessonsData.getLessons().get("2.1");
    	
    	assertEquals(EXPECTED_SubSect_2_1_Lesson, ACTUAL_SubSect_2_1_Lesson);
    }
    
    
    @Test
    void testLessonCountSecOne()  throws IOException {
    	
    	System.out.println("*** testLessonCountSecOne ***");
    	
    	Integer EXPECTED_LessonCountSecOne = 3;
    	Integer ACTUAL_LessonCountSecOne = lessonsData.getLessonsCount().get(1);
    	
    	assertEquals(EXPECTED_LessonCountSecOne, ACTUAL_LessonCountSecOne);
    }
    
    
    @Test
    void testLessonCountSecTwo()  throws IOException {
    	
    	System.out.println("*** testLessonCountSecOne ***");
    	
    	Integer EXPECTED_LessonCountSecTwo = 5;
    	Integer ACTUAL_LessonCountSecTwo = lessonsData.getLessonsCount().get(2);
    	
    	assertEquals(EXPECTED_LessonCountSecTwo, ACTUAL_LessonCountSecTwo);
    	
    	
    }
	 
    

    


}
