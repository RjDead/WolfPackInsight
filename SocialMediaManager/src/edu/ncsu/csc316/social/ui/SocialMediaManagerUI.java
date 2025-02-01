package edu.ncsu.csc316.social.ui;

import java.io.FileNotFoundException;

import java.util.Scanner;

import edu.ncsu.csc316.social.manager.ReportManager;

/**
 * A user interface for interacting with the SocialMediaManager.
 * Allows users to view connections by user name or by each social media platform.
 * Users can also exit the program.
 * @author Rishi Jeswani
 */
public class SocialMediaManagerUI {
    private static final Scanner SCANNERS = new Scanner(System.in);
    /**
     * The main method of the program.
     * @param args the command-line arguments
     * @throws FileNotFoundException if the input files are not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to SocialMediaManager!");
        String peopleFile = getInput("People File");
        String connectionsFile = getInput("Connections File");

        ReportManager manager = createReportManager(peopleFile, connectionsFile);
        if (manager == null) {
            System.out.println("Invalid file paths. Exiting.");
            return;
        }

        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            String choice = SCANNERS.nextLine();
            if ("1".equals(choice)) {
                System.out.println(manager.getConnectionsByPerson());
            } else if ("2".equals(choice)) {
                System.out.println(manager.getConnectionsByPlatform());
            } else if ("3".equals(choice)) {
                isRunning = false;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        SCANNERS.close();
    }
    /**
     * Prompts the user to enter input.
     * 
     * @param prompt the prompt to display to the user
     * @return the user's input
     */
    private static String getInput(String prompt) {
        System.out.print(prompt + ": ");
        return SCANNERS.nextLine();
    }
    /**
     * Prints the menu options for the user.
     */
    private static void printMenu() {
        System.out.println("\nOptions:");
        System.out.println("1. View connections by User Name");
        System.out.println("2. View connections by each social media platform");
        System.out.println("3. Exit");
        System.out.print("Enter option: ");
    }
    /**
     * Creates a ReportManager object with the provided input files.
     * @param peopleFile - the file containing information about people
     * @param connectionsFile -  the file containing information about connections
     * @return the created ReportManager object, or null if files are not found
     */
    private static ReportManager createReportManager(String peopleFile, String connectionsFile) {
        try {
            return new ReportManager(peopleFile, connectionsFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return null;
        }
    }
}