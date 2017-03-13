package gradeSystemProject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class GradeSystems {
	private Vector<Grades> gradesCollection;
	private float[] weights = new float[5]; 
	public GradeSystems () {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("gradeinput.txt"));
			String line;
			while ((line = bufferedReader.readLine()) != null)
				gradesCollection.add(new Grades(line));
			bufferedReader.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("File suck");
		}
		
	}
	public void updateWeights (float[] newWeights) {
		weights = Arrays.copyOf(newWeights, newWeights.length);
	}
	
}
