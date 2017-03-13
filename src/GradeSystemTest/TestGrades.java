package GradeSystemTest;

import static org.junit.Assert.*;

import org.junit.*;
import org.omg.CORBA.INITIALIZE;

import gradeSystemProject.Grades;

public class TestGrades {
	Grades grades;
	@Before
	public void init() throws Exception{
		String rawInput = "11111 Vincent 20 30 40 50 60";
		grades = new Grades(rawInput);
	}
	
	@Test
	public void testCorrect() {
		float[] weights={20,20,20,20,20};
		float totalgrade = grades.calculateTotalGrade(weights);
		assertEquals(40.0, totalgrade, 0.001);
		//assertEquals(expected, actual, delta)
		
	}
	
	@Test
	public void testDouble(){
		float[] weights={20,20,20,15,25};
		float totalgrade = grades.calculateTotalGrade(weights);
		assertEquals(40.5, totalgrade, 0.01);
	}
	@After
	public void cleanObject() throws Exception{
		grades = null;
	}

}
