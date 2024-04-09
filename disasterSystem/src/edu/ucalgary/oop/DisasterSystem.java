package edu.ucalgary.oop;

import java.util.Scanner;

public class DisasterSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static Location currentLocation;

    public static void main(String[] args) {
        System.out.println("Disaster System Interface");
        System.out.println("Are you a central(1) or location-based(2) relief worker?");
        int workerType = Integer.parseInt(scanner.nextLine());

        if (workerType == 1) {
            System.out.println("Central relief worker mode selected.");
            // Perform central worker-specific logic
        } else {
            System.out.println("Location-based relief worker mode selected.");
            // Perform location-based worker-specific logic
            // For example, prompt for and set the currentLocation
        }

        boolean running = true;
        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1 - Enter Disaster Victim information");
            System.out.println("2 - Log Inquirer query");
            System.out.println("3 - Search Inquirers by name");
            System.out.println("4 - View Inquirers");
            System.out.println("5 - View Inquiry Log");
            System.out.println("6 - Exit");

            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    enterDisasterVictimInformation();
                    break;
                case 2:
                    logInquirerQuery();
                    break;
                case 3:
                    searchInquirersByName();
                    break;
                // Add other cases for viewing inquirers and inquiry logs
                case 4:
                    DatabaseUtils.viewInquirers();
                    break;
                case 5:
                    DatabaseUtils.viewInquiryLog();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option selected.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Exiting Disaster System Interface.");
    }

    private static void enterDisasterVictimInformation() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter entry date (YYYY-MM-DD):");
        String entryDate = scanner.nextLine();
    
        // If currentLocation is not null, use it in the logic for adding a disaster victim
        if (currentLocation != null) {
            DisasterVictim victim = new DisasterVictim(firstName, lastName, entryDate);
            currentLocation.addOccupant(victim);
            System.out.println("Disaster victim added to location: " + currentLocation.getName());
        } else {
            // Handle case for central relief workers
            System.out.println("Central worker - not adding victim to a location.");
        }
    
        // Potentially call DatabaseUtils to save to database
        DatabaseUtils.addDisasterVictim(firstName, lastName, entryDate);
    }
    
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
        DatabaseUtils.logInquiry(inquirer, details);  // Now you are passing the correct arguments
        System.out.println("Inquirer query logged successfully.");
    }
    
    private static void searchInquirersByName() {
        System.out.println("Enter the name to search for:");
        String query = scanner.nextLine();
        DatabaseUtils.searchInquirersByName(query);
    }
}
