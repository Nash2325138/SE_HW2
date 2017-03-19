package gradeSystemProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
	 GradeSystems aGradeSystems;
	 Scanner scanner;
	 String queryingID;
	 public UI() {
		 aGradeSystems = new GradeSystems();
		 scanner = new Scanner(System.in);
	 }
	 /*
	  * The method that runs the UI to deal with the scenarios described in spec 
	  */
	 public void run() {
		 while (promptID() == true) {
			 if (aGradeSystems.containsID(queryingID) == false) {
				 System.out.println("Sorry, but we don't find ID: " + queryingID);
				 continue;
			 }
			 showWelcomeMsg();
			 while (promptCommand());
			 showFinishMsg();
		 }
		 System.out.println("Thanks for using grade system!!");		 
	 }

	 /*
	  * Prompt ID, when getting a "Q" -> false 
	  * Else -> true
	  */
	 private boolean promptID() {
		 System.out.println("Please enter your ID or enter \"Q\" or \"q\" to quit");
		 String input = scanner.nextLine();
		 if (input.equals("Q") || input.equals("q")) {
			 return false;
		 } else {
			 queryingID = input;
			 return true;
		 }
	 }
	 /*
	  * Show welcome message with ascii art file
	  */
	 private void showWelcomeMsg() {
		 System.out.println("Welcome, " + aGradeSystems.getName(queryingID));
		 printTxtFile("welcome.txt");
	 }
	 /*
	  * Show goodbye with ascii art file
	  */
	 private void showFinishMsg() {
		 System.out.println("Goodbye, " + aGradeSystems.getName(queryingID));
		 printTxtFile("finish.txt");
	 }
	 /*
	  * Print a entire txt file 
	  */
	 private void printTxtFile(String txt) {
		 try (BufferedReader br = new BufferedReader(new FileReader(txt))) {
			 String line = null;
			 while ((line = br.readLine()) != null) {
				 System.out.println(line);
			 }
		 } catch (Exception e) {
			 e.printStackTrace();
			 System.err.println("reading" + txt + "error");
		 }
	 }
	 
	 /*
	  * Prompt command and execute certain function using aGradeSystems.
	  * Return false when a 'exit' command is found.
	  */
	 private boolean promptCommand() {
		System.out.println("Please enter a command");
		System.out.println("\tG: Show your grades");
		System.out.println("\tR: Show your rank");
		System.out.println("\tA: Show class average");
		System.out.println("\tW: Update weights");
		System.out.println("\tE: Exit");
		String command = scanner.nextLine();
		if (command.equals("E")) {
			return false;
		}
		executeCommand(command);
		return true; 
	 }
	 /*
	  * Execute the command read in promptCommand()
	  */
	 private void executeCommand(String command) {
		 if (command.equals("G")) {
			aGradeSystems.showGrade(queryingID);
		} else if (command.equals("R")) {
			aGradeSystems.showRank(queryingID);
		} else if (command.equals("A")) {
			showAverage();
		} else if (command.equals("W")) {
			promptUpdateWeight();
		} else {
			System.out.println("No such command");
		}
	 }
	 /*
	  * Get the average scores from system and show them on screen
	  */
	 private void showAverage() {
		 System.out.println("The average scores are listed below");
		 String[] examNames = aGradeSystems.getExamNames();
		 float[] averages = aGradeSystems.getAverages();
		 for (int i = 0; i < examNames.length; i++) {
			System.out.println("\t" + examNames[i] + ": " + averages[i]);
		}
		 System.out.println();
	 }
	 /*
	  * Prompt user to input the weights they want to update
	  */
	 private void promptUpdateWeight() {
		 String[] examNames = aGradeSystems.getExamNames();
		 float[] new_weights = new float[examNames.length];
		 
		 System.out.println("Please enter the new weights below (in %)");
		 float sum = getNewWeights_sumup(examNames, new_weights);
		 
		 if (Math.abs(sum-100.) < 0.0001) {
			 aGradeSystems.updateWeights(new_weights);
		 } else {
			 System.err.println("The sum of new weights is not 100%");
		 }
	 }
	 /*
	  * Get the weights input from user
	  */
	 private float getNewWeights_sumup(String[] examNames, float[] new_weights) {
		 float sum = 0;
		 for (int i = 0; i < examNames.length; i++) {
			System.out.print("\t" + examNames[i] + ": ");
			try {
				new_weights[i] = scanner.nextFloat();
				sum += new_weights[i];
			} catch (InputMismatchException e) {
				System.err.println("It's not a floating number");
				scanner.nextLine();
				i--;
				continue;
			}
		 }
		 // eat one new line
		 scanner.nextLine();
		 return sum;
	 }
}
