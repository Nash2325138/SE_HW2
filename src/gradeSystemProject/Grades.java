package gradeSystemProject;
import java.util.*;

public class Grades {
	public String ID, name;
	public Vector<Integer> grades = new Vector<Integer>();
/**
 * Read a line to construct a user's information
 * @param rawInput
 * 1. fill ID
 * 2. fill name
 * 3. for (i from 2 to column number):
 * 4.	add each grade
 */
	public Grades(String rawInput) {
		String[] inputs = rawInput.split(" ");
//		System.out.println(Arrays.toString(inputs));
		this.ID = inputs[0];
		this.name = inputs[1];
		for (int i=2; i<inputs.length; i++) {
			try {
				this.grades.add(Integer.parseInt(inputs[i]));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.out.println(rawInput);
			}
			
		}
	}
/**
 * Calculate the weighted total grade
 * @param weights
 * @return
 * 1. sum up all weights[i] * grades[i]
 * 2. divide by 100
 */
	public float calculateTotalGrade(float[] weights) {
		float ret = 0;
		for (int i=0; i<grades.size(); i++) {
			ret += weights[i] * grades.get(i);
		}
		return ret / 100;
	}
}