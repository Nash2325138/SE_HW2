package gradeSystemProject;
import java.util.*;

public class Grades {
	public String ID, name;
	public Vector<Integer> grades = new Vector<Integer>();
	/*
	 * Read a line to construct a user's information
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
	/*
	 * Calculate the weighted total grade
	 */
	public float calculateTotalGrade(float[] weights) {
		float ret = 0;
		for (int i=0; i<grades.size(); i++) {
			ret += weights[i] * grades.get(i);
		}
		return ret / 100;
	}
}