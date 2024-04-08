package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DisasterVictim {
    private String firstName;
    private String lastName;
    private String dateOfBirth; // In YYYY-MM-DD format
    private String entryDate;
    private int approximateAge;
    private String comments;
    private final int assignedSocialID;
    private List<MedicalRecord> medicalRecords;
    private List<FamilyRelation> familyConnections;
    private List<Supply> personalBelongings;
    private List<DietaryRestriction> dietaryRestrictions;
    private String gender;
    private Location location;
    private static int socialIDCounter = 1;

    // Existing constructor that initializes with first name and entry date
    public DisasterVictim(String firstName, String entryDate) {
        this(firstName, null, entryDate); // Delegate to the new constructor
    }

    // New constructor that allows setting both first and last names along with the entry date
    public DisasterVictim(String firstName, String lastName, String entryDate) {
        this.firstName = firstName;
        this.lastName = lastName; // This can be null, indicating the last name is not provided

        // Validate entry date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);  // Set lenient to false to strictly check the date format
        try {
            dateFormat.parse(entryDate);  // Attempt to parse the entryDate
            this.entryDate = entryDate;  // If parsing is successful, set the entryDate
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format for entryDate. Please use yyyy-MM-dd format.");
        }

        this.assignedSocialID = socialIDCounter++;
        this.medicalRecords = new ArrayList<>();
        this.familyConnections = new ArrayList<>();
        this.personalBelongings = new ArrayList<>();
        this.dietaryRestrictions = new ArrayList<>();
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEntryDate() {
        return this.entryDate;
    }

    public Integer getApproximateAge() {
        return approximateAge == -1 ? null : approximateAge;
    }

    public String getDateOfBirth() {
        // If the date of birth is 'unset' indicated by null, return null
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        // Check if the dateOfBirth is null before matching the regex pattern
        if (dateOfBirth != null && !dateOfBirth.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format.");
        }
        // Invalidate the approximate age if a valid date of birth is being set.
        this.approximateAge = -1; // Use -1 to indicate 'unset'.
        this.dateOfBirth = dateOfBirth;
    }
    

    public void setApproximateAge(int approximateAge) {
        // Invalidate the date of birth when setting the age.
        this.dateOfBirth = null;
        this.approximateAge = approximateAge;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getAssignedSocialID() {
        return assignedSocialID;
    }

    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    // Method to add a family connection
    public void addFamilyConnection(FamilyRelation relation) {
        // Only add the relation to this DisasterVictim.
        if (!this.familyConnections.contains(relation)) {
            this.familyConnections.add(relation);
        }
    }

    // Method to remove a family connection
    public void removeFamilyConnection(FamilyRelation relation) {
        // Remove the relation from this DisasterVictim
        this.familyConnections.remove(relation);
        // Remove the relation from the related DisasterVictim if they exist
        if (relation.getPersonOne() == this && relation.getPersonTwo() != null) {
            relation.getPersonTwo().familyConnections.remove(relation);
        } else if (relation.getPersonTwo() == this && relation.getPersonOne() != null) {
            relation.getPersonOne().familyConnections.remove(relation);
        }
    }
    

    public List<FamilyRelation> getFamilyConnections() {
        return familyConnections;
    }

    public void setFamilyConnections(List<FamilyRelation> familyConnections) {
        this.familyConnections.clear(); // Clear the existing connections
        for (FamilyRelation relation : familyConnections) {
            this.addFamilyConnection(relation); // Add each connection
        }
    }

    public void addPersonalBelonging(Supply supply) {
        personalBelongings.add(supply);
    }

    public void removePersonalBelonging(Supply supply) {
        personalBelongings.remove(supply);
    }

    public List<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    public void setPersonalBelongings(List<Supply> personalBelongings) {
        this.personalBelongings = personalBelongings;
    }

    public List<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public void setGender(String gender) {
        if (Gender.isValidGender(gender)) {
            this.gender = gender;
        } else {
            throw new IllegalArgumentException("Invalid gender");
        }
    }

    public String getGender() {
        return this.gender;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


}

