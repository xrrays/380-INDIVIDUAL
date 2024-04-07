package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;

public class DisasterVictim {
    private String firstName;
    private String lastName;
    private String dateOfBirth; // In YYYY-MM-DD format
    private int approximateAge;
    private String comments;
    private final int assignedSocialID;
    private List<MedicalRecord> medicalRecords;
    private List<FamilyRelation> familyConnections;
    private List<Supply> personalBelongings;
    private List<DietaryRestriction> dietaryRestrictions;
    private Gender gender;
    private Location location;
    private static int socialIDCounter = 1;

    // Constructor
    public DisasterVictim(String firstName, String entryDate) {
        // Validate entry date format, omitted for brevity
        this.firstName = firstName;
        this.assignedSocialID = socialIDCounter++;
        this.medicalRecords = new ArrayList<>();
        this.familyConnections = new ArrayList<>();
        this.personalBelongings = new ArrayList<>();
        this.dietaryRestrictions = new ArrayList<>();
        // Additional initialization as necessary
    }

    // Accessor methods for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Accessor methods for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Accessor methods for dateOfBirth
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (this.approximateAge != 0) {
            // Invalidate setting the date of birth if age is already set
            this.dateOfBirth = null;
        } else {
            // Validate date format and set the dateOfBirth
            this.dateOfBirth = dateOfBirth;
        }
    }

    // Accessor methods for approximateAge
    public int getApproximateAge() {
        return approximateAge;
    }

    public void setApproximateAge(int approximateAge) {
        if (this.dateOfBirth != null) {
            // Invalidate setting the age if date of birth is already set
            this.approximateAge = 0;
        } else {
            this.approximateAge = approximateAge;
        }
    }

    // Accessor methods for comments
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // Accessor methods for assignedSocialID
    public int getAssignedSocialID() {
        return assignedSocialID;
    }

    // Method to add a medical record
    public void addMedicalRecord(MedicalRecord record) {
        medicalRecords.add(record);
    }

    // Accessor methods for medicalRecords
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    // Method to add a family connection
    public void addFamilyConnection(FamilyRelation relation) {
        // Ensure that the relation is added to both DisasterVictims
        if (!this.familyConnections.contains(relation)) {
            this.familyConnections.add(relation);
            relation.getPersonTwo().familyConnections.add(relation);
        }
    }

    // Method to remove a family connection
    public void removeFamilyConnection(FamilyRelation relation) {
        // Ensure that the relation is removed from both DisasterVictims
        this.familyConnections.remove(relation);
        relation.getPersonTwo().familyConnections.remove(relation);
    }

    // Accessor methods for familyConnections
    public List<FamilyRelation> getFamilyConnections() {
        return familyConnections;
    }

    public void setFamilyConnections(List<FamilyRelation> familyConnections) {
        this.familyConnections = familyConnections;
        // Also, update family connections for each related DisasterVictim
        for (FamilyRelation relation : familyConnections) {
            relation.getPersonTwo().setFamilyConnections(familyConnections);
        }
    }

    // Method to add a personal belonging
    public void addPersonalBelonging(Supply supply) {
        personalBelongings.add(supply);
    }

    // Method to remove a personal belonging
    public void removePersonalBelonging(Supply supply) {
        personalBelongings.remove(supply);
    }

    // Accessor methods for personalBelongings
    public List<Supply> getPersonalBelongings() {
        return personalBelongings;
    }

    public void setPersonalBelongings(List<Supply> personalBelongings) {
        this.personalBelongings = personalBelongings;
    }

    // Accessor methods for dietaryRestrictions
    public List<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    // Accessor methods for gender
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // Accessor methods for location
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // Additional methods and logic as needed...

}

