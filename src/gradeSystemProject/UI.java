package gradeSystemProject;

import java.io.BufferedReader;
import java.io.FileReader;
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
		 }
	 }
	 /*
	  * prompt ID, when getting a "Q" -> false 
	  * else -> true
	  */
	 boolean promptID() {
		 System.out.println("Please enter your ID or enter \"Q\" or \"q\" to quit");
		 String input = scanner.next();
		 if (input.equals("Q") || input.equals("q")) {
			 return false;
		 } else {
			 queryingID = input;
			 return true;
		 }
	 }
	 void showWelcomeMsg() {
		try (BufferedReader br = new BufferedReader(new FileReader("welcome.txt"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("reading welcome file error");
		}
		System.out.println("Welcome " + aGradeSystems.getName(queryingID));
	 }
	 void showFinishMsg() {
		 
	 }
	 
	 /*
	  * prompt command and execute certain function using aGradeSystems
	  * return false when a 'exit' command is found
	  */
	 boolean promptCommand() {
		return false; 
	 }
	 
}
