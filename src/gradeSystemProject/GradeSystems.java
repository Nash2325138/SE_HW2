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
			System.out.println("File suck");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO fucked");
		}
		weights = new float[] {10,10,10,30,40};
	}
	public void updateWeights (float[] newWeights) {
		weights = Arrays.copyOf(newWeights, newWeights.length);
	}
	public boolean containsID(String ID) {
		return IDtoGrades.containsKey(ID);
	}
	public void showRank(String ID) {
		int rank = getRank(ID);
		if(rank == -1){
			System.out.println("No such ID!!");
			return;
		}
		Grades grades = IDtoGrades.get(ID);
		System.out.println(grades.name + "'s rank is " + rank + "\n");
	}
	private int getRank(String ID) {
		if(IDtoGrades.containsKey(ID)==false) return -1;
		int rank = 1;
		float myScore = IDtoGrades.get(ID).calculateTotalGrade(weights);
		for (Grades grades : gradesCollection) {
			if (grades.calculateTotalGrade(weights) > myScore) rank++;
		}
		return rank;
	}
	public void showGrade(String ID) {
		if(IDtoGrades.containsKey(ID)==false){
			System.out.println("No such ID!!");
			return;
		}
		Grades someone = IDtoGrades.get(ID);
		Vector<Integer> grades = someone.grades;
		System.out.println(someone.name + "'s scores are:");
		for (int i = 0; i < grades.size(); i++) {
			System.out.println("\t" + examNames[i] + ": " + grades.get(i));
		}
		System.out.println("\tWeighted grade: " + someone.calculateTotalGrade(weights) + "\n");
	}
	public String getName(String ID) {
		return IDtoGrades.get(ID).name;
	}
	public String[] getExamNames() {
		return examNames;
	}
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
