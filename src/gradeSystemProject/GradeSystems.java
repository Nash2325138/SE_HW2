package gradeSystemProject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GradeSystems {
	private Vector<Grades> gradesCollection = new Vector<Grades>();
	private TreeMap<String, Grades> IDtoGrades = new TreeMap<String, Grades>();
	private float[] weights;
	private float[] averages;
	private String[] examNames = {"Lab1", "Lab2", "Lab3", "Midterm", "Final Exam"};
/**
 *  Read the file to build gradesCollection and IDtoGrades.
 *  Initial the weights.
 */
	public GradeSystems () {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("gradeinput.txt"));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				Grades temp = new Grades(line);
				gradesCollection.add(temp);
				IDtoGrades.put(temp.ID, temp);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("File suck");
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("IO fucked");
			System.exit(-2);
		}
		weights = new float[] {10,10,10,30,40};
	}
/**
 * Get function of weights
 * @return
 */
	public float[] getWeights() {
		return weights;
	}
/**
 * Replace the weights with new weights
 * @param newWeights
 */
	public void updateWeights (float[] newWeights) {
		weights = Arrays.copyOf(newWeights, newWeights.length);
	}
/**
 * return true if the system has ID
 * @param ID
 * @return
 */
	public boolean containsID(String ID) {
		return IDtoGrades.containsKey(ID);
	}
/**
 * Get the rank of the querying user and show it on screen
 * @param ID
 */
	public void showRank(String ID) {
		int rank = getRank(ID);
		if(rank == -1){
			System.out.println("No such ID!!");
			return;
		}
		Grades grades = IDtoGrades.get(ID);
		System.out.println(grades.name + "'s rank is " + rank + "\n");
	}
/**
 * Calculate the rank of the querying user
 * @param ID
 * @return
 */
	private int getRank(String ID) {
		if(IDtoGrades.containsKey(ID)==false) return -1;
		int rank = 1;
		float myScore = IDtoGrades.get(ID).calculateTotalGrade(weights);
		for (Grades grades : gradesCollection) {
			if (grades.calculateTotalGrade(weights) > myScore) rank++;
		}
		return rank;
	}
/**
 * Show all grades and weighted grade of the querying user
 * @param ID
 */
	public void showGrade(String ID) {
		if(IDtoGrades.containsKey(ID)==false){
			System.out.println("No such ID!!");
			return;
		}
		Grades someone = IDtoGrades.get(ID);
		Vector<Integer> grades = someone.grades;
		System.out.println(someone.name + "'s scores are:");
		for (int i = 0; i < grades.size(); i++) {
			Integer grade = grades.get(i);
			if (grade < 60) {
				System.out.println("\t" + examNames[i] + ": " + grade + "*");
			} else {
				System.out.println("\t" + examNames[i] + ": " + grade);
			}
		}
		System.out.println("\tWeighted grade: " + someone.calculateTotalGrade(weights) + "\n");
	}
/**
 * Get function of the name of the querying user
 * @param ID
 * @return
 */
	public String getName(String ID) {
		return IDtoGrades.get(ID).name;
	}
/**
 * Get function of examNames;
 * @return
 */
	public String[] getExamNames() {
		return examNames;
	}
	/**
	 * Calculate the average scores of all grades over entire class.
	 * After the first time the function is called since gradeSystem's
	 * construction, the result will be stored and won't be calculated
	 * when this function is called again.
	 * @return
	 */

	public float[] getAverages() {
		if (averages == null) {
			int len = gradesCollection.get(0).grades.size();
			int size = gradesCollection.size();
			float[] sum = new float[size];
			for (Iterator<Grades> iterator = gradesCollection.iterator(); iterator.hasNext();) {
				Grades grades = (Grades) iterator.next();
				for (int i = 0; i < len; i++) {
					sum[i] += grades.grades.get(i);
				}
			}
			averages = new float[len];
			for (int i = 0; i < len; i++) {
				averages[i] = sum[i]/size;
			}
		}
		return averages;
	}
}
