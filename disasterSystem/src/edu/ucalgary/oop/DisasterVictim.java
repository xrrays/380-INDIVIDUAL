/**
 * This class represents a disaster victim, hholding all their personal details.
 * It provides methods for managing and validating family relationships,
 * recording medical history, and handling personal belongings and dietary restrictions.
 *
 * @author Rayyan Ahmed
 * @version 1.9
 * @since 1.0
 */

package edu.ucalgary.oop;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.HashSet;

public class DisasterVictim {
    private String firstName;
    private String lastName;
    private String dateOfBirth; // In YYYY-MM-DD format
    private String entryDate;
    private int approximateAge = -1; // Used to help with method, -1 indicates unset
    private String comments;
    private final int assignedSocialID;
    private List<MedicalRecord> medicalRecords;
    private Set<FamilyRelation> familyConnections;
    private List<Supply> personalBelongings;
    private List<DietaryRestriction> dietaryRestrictions;
    private String gender;
    private Location location;
    private static int socialIDCounter = 1;

    // Constructor that initializes with first name and entry date.
    public DisasterVictim(String firstName, String entryDate) {
        this(firstName, null, entryDate);
    }

    // Constructor that allows setting first and last names along with entry date.
    public DisasterVictim(String firstName, String lastName, String entryDate) {
        this.firstName = firstName;
        this.lastName = lastName;

        // Validate entry date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(entryDate);
            this.entryDate = entryDate;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format for entryDate. Please use yyyy-MM-dd format.");
        }

        this.assignedSocialID = socialIDCounter++;
        this.medicalRecords = new ArrayList<>();
        this.familyConnections = new HashSet<>();
        this.personalBelongings = new ArrayList<>();
        this.dietaryRestrictions = new ArrayList<>();
    }

    // ALL SECTION:
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // AGE SECTION:
    public Integer getApproximateAge() {
        return approximateAge == -1 ? null : approximateAge;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    // The setDateOfBirth and setApproximateAge methods work to ensure that only
    // one of them is set at a time, by setting the other to null.
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

    // FAMILY SECTION:
    public void addFamilyConnection(FamilyRelation relation) {
        this.familyConnections.add(relation);
        relation.getOtherVictim(this).getFamilyConnections().add(relation);
    }

    public void removeFamilyConnection(FamilyRelation relation) {
        this.familyConnections.remove(relation);
        DisasterVictim otherVictim = relation.getOtherVictim(this);
        otherVictim.familyConnections.remove(relation);
    }

    public Set<FamilyRelation> getFamilyConnections() {
        return new HashSet<>(familyConnections);
    }

    public void setFamilyConnections(Set<FamilyRelation> familyConnections) {
        this.familyConnections.clear();
        this.familyConnections.addAll(familyConnections);
    }

    // This method is used to ensure family series consistency. If there are three
    // siblings
    // then sibling 1 and sibling 3 will be related if sibling 1 and 2 are related.
    public void validateFamilyNetwork() {
        System.out.println("Starting validateFamilyNetwork for: " + this.getFirstName());

        // Collect all siblings of the current disaster victim
        Set<DisasterVictim> siblings = new HashSet<>();
        for (FamilyRelation relation : this.familyConnections) {
            if ("sibling".equals(relation.getRelationshipTo())) {
                siblings.add(relation.getOtherVictim(this));
            }
        }

        // Check each pair of siblings to ensure they are directly connected
        for (DisasterVictim sibling1 : siblings) {
            for (DisasterVictim sibling2 : siblings) {
                // If sibling1 and sibling2 are not the same and no direct connection exists,
                // create one
                if (!sibling1.equals(sibling2) && !hasDirectConnection(sibling1, sibling2)) {
                    System.out.println("Adding new sibling relation between: " + sibling1.getFirstName() + " and "
                            + sibling2.getFirstName());
                    new FamilyRelation(sibling1, "sibling", sibling2);
                }
            }
        }

        System.out.println("Completed validateFamilyNetwork for: " + this.getFirstName());
    }

    // This method helps the validation by confirming connection between disaster
    // victims.
    private boolean hasDirectConnection(DisasterVictim victim1, DisasterVictim victim2) {
        return victim1.getFamilyConnections().stream()
                .anyMatch(relation -> relation.involves(victim2));
    }

    // DIET SECTION:
    // The next three methods are used with the enum class to retrieve, add and set
    // dietary restrictions to a disaster victim's profile.
    public List<DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
        System.out.println("Dietary restrictions set for " + getFirstName() + ": " + this.dietaryRestrictions);
    }

    public void addDietaryRestriction(DietaryRestriction dietaryRestriction) {
        // Prevent adding duplicates
        if (!this.dietaryRestrictions.contains(dietaryRestriction)) {
            this.dietaryRestrictions.add(dietaryRestriction);
            System.out.println("Added dietary restriction for " + getFirstName() + ": " + dietaryRestriction);
        } else {
            System.out.println("Attempted to add a duplicate dietary restriction for " + getFirstName() + ": "
                    + dietaryRestriction);
        }
    }

    // GENDER SECTION:
    // These methods retrieve and set the gender of the disaster victim.
    public void setGender(String gender) {
        // Check if the provided gender is valid by consulting the Gender class
        if (Gender.isValidGender(gender)) {
            this.gender = gender;
            System.out.println("Setting gender: " + gender);
        } else {
            System.out.println("Attempted to set invalid gender: " + gender);
            throw new IllegalArgumentException("Invalid gender");
        }
    }

    public String getGender() {
        return this.gender;
    }

}
