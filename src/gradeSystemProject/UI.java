package gradeSystemProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
	 GradeSystems aGradeSystems = new GradeSystems();
	 Scanner scanner = new Scanner(System.in);
	 String queryingID;
	 public UI() {
		 // while( prompt ID )
		 // 	if ( checkID == False ) continue
		 // 	while (not exit)
		 // 		showWelcomeMsg
		 // 		prompt command
		 // 			showGrade or showRank or updateWeights
		 // 	showFinishMsg
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
	  * prompt ID, when getting a "Q" -> false 
	  * else -> true
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
	 private void showWelcomeMsg() {
		 System.out.println("Welcome, " + aGradeSystems.getName(queryingID));
		 printTxtFile("welcome.txt");
	 }
	 private void showFinishMsg() {
		 System.out.println("Goodbye, " + aGradeSystems.getName(queryingID));
		 printTxtFile("finish.txt");
	 }
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
	  * prompt command and execute certain function using aGradeSystems
	  * return false when a 'exit' command is found
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
	 private void showAverage() {
		 System.out.println("The average scores are listed below");
		 String[] examNames = aGradeSystems.getExamNames();
		 float[] averages = aGradeSystems.getAverages();
		 for (int i = 0; i < examNames.length; i++) {
			System.out.println("\t" + examNames[i] + ": " + averages[i]);
		}
		 System.out.println();
	 }
	 private void promptUpdateWeight() {
		 String[] examNames = aGradeSystems.getExamNames();
		 float[] new_weights = new float[examNames.length];
		 float sum = 0;
		 
		 System.out.println("Please enter the new weights below (in %)");
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
		 scanner.nextLine();
		 if (Math.abs(sum-100.) < 0.0001) {
			 aGradeSystems.updateWeights(new_weights);
		 } else {
			 System.err.println("The sum of new weights is not 100%");
		 }
	 }
	 
}
