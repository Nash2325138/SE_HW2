package GradeSystemTest;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;


import gradeSystemProject.UI;

public class TestUI {

	/**
	 * Test to quit system immediately
	 */
	@Test
	public void test1() {
		System.out.println("--------------test1--------------------");
		String testing="Q";
		InputStream inputStream = new ByteArrayInputStream(testing.getBytes());
		System.setIn(inputStream);
		UI ui = new UI();
		ui.run();
	}
	
	/**
	 * Test get into the system by ID then quit immediately
	 */
	@Test
	public void test2() {
		System.out.println("--------------test2--------------------");
		String testing="962001044\nE\nQ";
		InputStream inputStream = new ByteArrayInputStream(testing.getBytes());
		System.setIn(inputStream);
		UI ui = new UI();
		ui.run();
		
	}
	
	/**
	 * Test get into the system by ID then show Grade, Rank, Average then quit
	 */
	@Test
	public void test3() {
		System.out.println("--------------test3--------------------");
		String testing="962001051\nG\nR\nA\nE\nQ";
		InputStream inputStream = new ByteArrayInputStream(testing.getBytes());
		System.setIn(inputStream);
		UI ui = new UI();
		ui.run();
		
	}
	
	/**
	 * Test get into the system by ID then update new weight, after that quit the system
	 */
	@Test
	public void test4() {
		System.out.println("--------------test4--------------------");
		String testing="965002038\nW\n20\n20\n20\n20\n20\nE\nQ";
		InputStream inputStream = new ByteArrayInputStream(testing.getBytes());
		System.setIn(inputStream);
		UI ui = new UI();
		ui.run();
		
	}
	
	/**
	 * Test when update new weight with total not 100%
	 */
	@Test
	public void test5() {
		System.out.println("--------------test5--------------------");
		String testing="965002038\nW\n20\n20\n30\n20\n20\nE\nQ";
		InputStream inputStream = new ByteArrayInputStream(testing.getBytes());
		System.setIn(inputStream);
		UI ui = new UI();
		ui.run();
		
	}
	
	/**
	 * Test trying to use error ID to get into the system
	 */
	@Test
	public void testErrorID() {
		System.out.println("--------------testErrorID--------------------");
		String testing="948794\nQ";
		InputStream inputStream = new ByteArrayInputStream(testing.getBytes());
		System.setIn(inputStream);
		UI ui = new UI();
		ui.run();
		
	}
	
	/**
	 * Test try to input wrong command
	 */
	@Test
	public void testErrorCommand() {
		System.out.println("--------------testErrorCommand--------------------");
		String testing="962001051\nP\nE\nQ";
		InputStream inputStream = new ByteArrayInputStream(testing.getBytes());
		System.setIn(inputStream);
		UI ui = new UI();
		ui.run();
		
	}
	

}
