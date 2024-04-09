package edu.ucalgary.oop;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DisasterSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static Location currentLocation;
    private static final List<String> disasterVictimRecords = new ArrayList<>();

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
            relationships.setLength(relationships.length() - 2);  // Remove trailing comma and space
        }
    
        String victimRecord = String.format("Victim: %s %s, Medical Notes: %s, Relations: %s",
                                            firstName, lastName, medicalRecord, relationships.toString());
        disasterVictimRecords.add(victimRecord);
        System.out.println("Disaster victim record added.");
    }

    private static void viewDisasterVictims() {
        System.out.println("Victims at " + currentLocation.getName() + ":");
        for (String record : disasterVictimRecords) {
            if (record.startsWith("Victim:")) {
                System.out.println(record);
            }
        }
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
        DatabaseUtils.logInquiry(inquirer, details);
        System.out.println("Inquirer query logged successfully.");
    }

    private static void searchInquirersByName() {
        System.out.println("Enter the name to search for:");
        String query = scanner.nextLine();
        DatabaseUtils.searchInquirersByName(query);
    }



}