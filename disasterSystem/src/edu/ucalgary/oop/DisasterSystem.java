package edu.ucalgary.oop;

import java.util.*;

public class DisasterSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InteractionLog interactionLog = new InteractionLog();
    private static Location currentLocation = null;

    public static void main(String[] args) {
        System.out.println("Disaster System Interface");
        System.out.println("Are you a central(1) or location-based(2) relief worker?");
        int workerType = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (workerType == 1) {
            System.out.println("Central relief worker mode selected.");
        } else {
            System.out.println("Location-based relief worker mode selected.");
            System.out.println("Enter the location name:");
            String locationName = scanner.nextLine();
            System.out.println("Enter the location address:");
            String locationAddress = scanner.nextLine();
            currentLocation = new Location(locationName, locationAddress);
        }

        boolean running = true;
        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1 - Enter Disaster Victim information");
            System.out.println("2 - Log Inquirer query");
            System.out.println("3 - Search Inquirers by name");
            System.out.println("4 - Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
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

        DisasterVictim victim = new DisasterVictim(firstName, lastName, entryDate);
        if (currentLocation != null) {
            currentLocation.addOccupant(victim);
        }
        System.out.println("Disaster victim added successfully.");

        // Add additional victim information, relationships, and medical records as needed...
    }

    private static void logInquirerQuery() {
        System.out.println("Enter inquirer first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter inquirer last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter inquirer phone number:");
        String phone = scanner.nextLine();
        System.out.println("Enter inquirer information:");
        String info = scanner.nextLine();

        Inquirer inquirer = new Inquirer(firstName, lastName, phone, info);
        // Create and log the ReliefService interaction based on the inquirer.
        // Assume we have a method to create a ReliefService object here.
        ReliefService service = createReliefService(inquirer, currentLocation);
        interactionLog.logInteraction(inquirer, service);
        System.out.println("Inquirer query logged successfully.");
    }

    private static void searchInquirersByName() {
        System.out.println("Enter the name to search for:");
        String query = scanner.nextLine();

        List<Inquirer> results = interactionLog.searchInquirer(query);
        System.out.println("Found inquirers:");
        for (Inquirer inq : results) {
            System.out.println(inq.getFirstName() + " " + inq.getLastName());
        }
    }

    private static ReliefService createReliefService(Inquirer inquirer, Location location) {
        System.out.println("Enter the name of the missing person (or press Enter to skip):");
        String missingFirstName = scanner.nextLine();
        String missingLastName = "";
    
        // Assume the last name is provided if the first name is not empty
        if (!missingFirstName.isEmpty()) {
            System.out.println("Enter the missing person's last name:");
            missingLastName = scanner.nextLine();
        }
    
        System.out.println("Enter the date of inquiry (YYYY-MM-DD):");
        String dateOfInquiry = scanner.nextLine();
        System.out.println("Enter any additional information provided:");
        String infoProvided = scanner.nextLine();
    
        DisasterVictim missingPerson = null;
        if (!missingFirstName.isEmpty()) {
            missingPerson = new DisasterVictim(missingFirstName, missingLastName, dateOfInquiry);
            // You can also add the missing person to the location's list of occupants if needed
            // location.addOccupant(missingPerson);
        }
    
        return new ReliefService(inquirer, missingPerson, dateOfInquiry, infoProvided, location);
    }
    
}
