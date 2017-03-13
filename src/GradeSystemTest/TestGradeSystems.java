package GradeSystemTest;

import static org.junit.Assert.*;

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
		assertEquals(63, system.getRank(poorGuy));
	}
	@Test
	public void testShowGrade() {
		String poorGuy = "962001051";
		system.showGrade(poorGuy);
	}
}
