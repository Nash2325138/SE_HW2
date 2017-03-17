package GradeSystemTest;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gradeSystemProject.GradeSystems;

public class TestGradeSystems {
	GradeSystems system;
	@Before
	public void setUpBeforeClass() throws Exception {
		system = new GradeSystems();
	}

	@After
	public void tearDownAfterClass() throws Exception {
		system = null;
	}

	@Test
	public void testRank() {
		String poorGuy = "962001051";
		system.showRank(poorGuy);
	}
	
	@Test 
	public void testRank2(){
		String randomID = "990909324";
		system.showRank(randomID);
		
	}
	@Test
	public void testShowGrade() {
		String poorGuy = "962001051";
		system.showGrade(poorGuy);
	}
	
	@Test
	public void testShowGrade2() {
		String randomID = "135789789";
		system.showGrade(randomID);
	}
	
	@Test
	public void testaverage()
	{
		float[] check;
		check = system.getAverages();
		System.out.println(Arrays.toString(check));
		
	}
}
