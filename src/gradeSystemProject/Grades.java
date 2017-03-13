package gradeSystemProject;
import java.util.*;

public class Grades {
	public String ID, name;
	public Vector<Integer> grades;
	public Grades(String rawInput) {
		String[] inputs = rawInput.split(" ");
		this.ID = inputs[0];
		this.name = inputs[1];
		for (int i=2; i<7; i++) {
			this.grades.add(Integer.parseInt(inputs[i]));
		}
		assert(this.grades.size() == 5);
	}
	/*
	 * Calculate the weighted total grade
	 */
	public float calculateTotalGrade(Vector<Float> weights) {
		float ret = 0;
		for (int i=0; i<grades.size(); i++) {
			ret += weights.get(i) * grades.get(i);
		}
		return ret / 100;
	}
}