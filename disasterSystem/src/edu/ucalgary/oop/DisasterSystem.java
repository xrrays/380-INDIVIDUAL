/**
 * The main class for the Disaster System handles the program flow and user interaction.
 * This class presents an interface for central or location-based relief workers to
 * enter and manage disaster victim information as well as log and search inquirer queries.
 * It uses a simple command-line interface for interaction with the user.
 * 
 * The main method starts the application and responds based on user input.
 * The centralWorkerInterface and locationBasedWorkerInterface methods
 * provide different options for central and location-based workers respectively.
 * 
 * @author Rayyan Ahmed
 * @version 1.9
 * @since 1.6
 */

package edu.ucalgary.oop;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DisasterSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static Location currentLocation;
    private static final List<String> disasterVictimRecords = new ArrayList<>();

    // The main function starts the interfaces needed to meet all the requirements
    // and goes different paths depending on the user input (worker type).
    public static void main(String[] args) {
        DatabaseUtils.setInitialSequenceValues();
        System.out.println("Disaster System Interface");
        System.out.println("Are you a central(1) or location-based(2) relief worker?");
        int workerType = Integer.parseInt(scanner.nextLine());

        if (workerType == 1) {
            centralWorkerInterface();
        } else {
            locationBasedWorkerInterface();
        }

        scanner.close();
        System.out.println("Exiting Disaster System Interface.");
    }

    // The interface for the central worker, based around inquirers and inquiries.
    // The choices by the user lead to different paths, taken care of by other
    // methods.
    private static void centralWorkerInterface() {
        boolean running = true;
        while (running) {
            System.out.println("\nCentral Worker - Select an option:");
            System.out.println("1 - Log Inquirer query");
            System.out.println("2 - Search Inquirers by name");
            System.out.println("3 - View Inquirers");
            System.out.println("4 - View Inquiry Log");
            System.out.println("5 - Exit");

            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    logInquirerQuery();
                    break;
                case 2:
                    searchInquirersByName();
                    break;
                case 3:
                    DatabaseUtils.viewInquirers();
                    break;
                case 4:
                    DatabaseUtils.viewInquiryLog();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
        }
    }

    // The interface for the location worker, based around disaster victim data.
    // The choices by the user lead to different paths, taken care of by other
    // methods.
    private static void locationBasedWorkerInterface() {
        System.out.println("Choose Location:");
        System.out.println("1 - Location A");
        System.out.println("2 - Location B");
        System.out.println("3 - Location C");
        int locationChoice = Integer.parseInt(scanner.nextLine());
        setCurrentLocation(locationChoice);

        boolean running = true;
        while (running) {
            System.out.println("\nLocation-Based Worker - Select an option:");
            System.out.println("1 - View Disaster Victims");
            System.out.println("2 - Add Disaster Victim");
            System.out.println("3 - Exit");

            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    viewDisasterVictims();
                    break;
                case 2:
                    enterDisasterVictimInformation();
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
        }
    }

    // This method is used for the location worker, to choose the location they work
    // at.
    private static void setCurrentLocation(int choice) {
        switch (choice) {
            case 1:
                currentLocation = new Location("Location A", "Address A");
                break;
            case 2:
                currentLocation = new Location("Location B", "Address B");
                break;
            case 3:
                currentLocation = new Location("Location C", "Address C");
                break;
            default:
                System.out.println("Invalid location selected, defaulting to Location A.");
                currentLocation = new Location("Location A", "Address A");
        }
        System.out.println("Selected: " + currentLocation.getName());
    }

    // The interface when the user chooses to add a new disaster victim.
    // The information is then stored in the victim list.
    private static void enterDisasterVictimInformation() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter medical record notes:");
        String medicalRecord = scanner.nextLine();

        StringBuilder relationships = new StringBuilder();
        String addMore;
        do {
            System.out.println("Enter relationship person's full name:");
            String relationshipPerson = scanner.nextLine();
            System.out.println("Enter their relationship to the victim:");
            String relationship = scanner.nextLine();

            relationships.append(relationshipPerson).append(" - ").append(relationship).append(", ");

            System.out.println("Add another relation? (yes/no)");
            addMore = scanner.nextLine().trim().toLowerCase();
        } while (addMore.equals("yes"));

        if (relationships.length() > 0) {
            relationships.setLength(relationships.length() - 2);
        }

        // The victim's new information is stored
        String victimRecord = String.format("Victim: %s %s, Medical Notes: %s, Relations: %s",
                firstName, lastName, medicalRecord, relationships.toString());
        disasterVictimRecords.add(victimRecord);
        System.out.println("Disaster victim record added.");
    }

    // The victim list can then be viewed.
    private static void viewDisasterVictims() {
        System.out.println("Victims at " + currentLocation.getName() + ":");
        for (String record : disasterVictimRecords) {
            if (record.startsWith("Victim:")) {
                System.out.println(record);
            }
        }
    }

    // This is the interface used to log a query.
    // Once the information is gathered it is sent to the database.
    private static void logInquirerQuery() {
        System.out.println("Enter inquirer first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter inquirer last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter inquirer phone number:");
        String phone = scanner.nextLine();
        System.out.println("Enter inquiry details:");
        String details = scanner.nextLine();

        Inquirer inquirer = new Inquirer(firstName, lastName, phone, details);
        DatabaseUtils.logInquiry(inquirer, details);
        System.out.println("Inquirer query logged successfully.");
    }

    // This is the interface to search for an inquirer.
    // Once the input is gathered, it is sent to the database method to search.
    private static void searchInquirersByName() {
        System.out.println("Enter the name to search for:");
        String query = scanner.nextLine();
        DatabaseUtils.searchInquirersByName(query);
    }

}