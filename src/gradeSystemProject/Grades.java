package gradeSystemProject;
import java.util.*;

public class Grades {
	public String ID, name;
	public ArrayList<Integer> grades = new ArrayList<>();
	public Grades(String ID, String name, ArrayList<Integer> grades) {
		this.ID = ID;
		this.name = name;
		this.grades = grades;
	}
	/*
	 * Calculate the weighted total grade
	 */
	public float calculateTotalGrade(ArrayList<Float> weights) {
		float ret = 0;
		for (int i=0; i<grades.size(); i++) {
			ret += weights.get(i) * grades.get(i);
		}
		return ret / 100;
	}
}