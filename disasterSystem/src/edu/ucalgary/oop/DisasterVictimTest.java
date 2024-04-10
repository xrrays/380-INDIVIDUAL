/**
 * This class contains unit tests for the DisasterVictim class, 
 * validating functionality such as getters and setters, age/birthdate,
 * family connections, gender, and dietary restrictions.
 * 
 * @author Rayyan Ahmed
 * @version 1.9
 * @since 1.0
 */

package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class DisasterVictimTest {
    private DisasterVictim victim;
    private List<Supply> suppliesToSet;
    private String expectedFirstName = "Freda";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String invalidDate = "15/13/2024";
    private String expectedComments = "Needs medical attention and speaks 2 languages";

    @Before
    public void setUp() {
        victim = new DisasterVictim(expectedFirstName, EXPECTED_ENTRY_DATE);
        suppliesToSet = new ArrayList<>();
        suppliesToSet.add(new Supply("Water Bottle", 10));
        suppliesToSet.add(new Supply("Blanket", 5));

        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

    }

    // ALL SECTION:
    @Test
    public void testConstructorWithValidEntryDate() {
        String validEntryDate = "2024-01-18";
        DisasterVictim victim = new DisasterVictim("Freda", validEntryDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        String invalidEntryDate = "18/01/2024";
        new DisasterVictim("Freda", invalidEntryDate);
    }

    @Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Alice";
        victim.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName,
                victim.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        String newLastName = "Smith";
        victim.setLastName(newLastName);
        assertEquals("setLastName should update and getLastName should return the new last name", newLastName,
                victim.getLastName());
    }

    @Test
    public void testGetComments() {
        victim.setComments(expectedComments);
        assertEquals("getComments should return the initial correct comments", expectedComments, victim.getComments());
    }

    @Test
    public void testSetComments() {
        victim.setComments(expectedComments);
        String newComments = "Has a minor injury on the left arm";
        victim.setComments(newComments);
        assertEquals("setComments should update the comments correctly", newComments, victim.getComments());
    }

    @Test
    public void testGetAssignedSocialID() {
        DisasterVictim newVictim = new DisasterVictim("Kash", "2024-01-21");
        int expectedSocialId = newVictim.getAssignedSocialID() + 1;
        DisasterVictim actualVictim = new DisasterVictim("Adeleke", "2024-01-22");

        assertEquals("getAssignedSocialID should return the expected social ID", expectedSocialId,
                actualVictim.getAssignedSocialID());
    }

    @Test
    public void testGetEntryDate() {
        assertEquals("getEntryDate should return the expected entry date", EXPECTED_ENTRY_DATE, victim.getEntryDate());
    }

    @Test
    public void testAddPersonalBelonging() {
        Supply newSupply = new Supply("Emergency Kit", 1);
        victim.addPersonalBelonging(newSupply);
        List<Supply> testSupplies = victim.getPersonalBelongings();
        assertTrue("addPersonalBelonging should add the supply to personal belongings",
                testSupplies.contains(newSupply));
    }

    @Test
    public void testRemovePersonalBelonging() {
        Supply supplyToRemove = suppliesToSet.get(0);
        victim.addPersonalBelonging(supplyToRemove);
        victim.removePersonalBelonging(supplyToRemove);
        assertFalse("removePersonalBelonging should remove the supply from personal belongings",
                victim.getPersonalBelongings().contains(supplyToRemove));
    }

    @Test
    public void testSetPersonalBelongings() {
        List<Supply> newSupplies = new ArrayList<>();
        newSupplies.add(new Supply("Tent", 1));
        newSupplies.add(new Supply("Jug", 3));

        victim.setPersonalBelongings(newSupplies);
        List<Supply> actualSupplies = victim.getPersonalBelongings();

        assertEquals("setPersonalBelongings should correctly update personal belongings", newSupplies, actualSupplies);
    }

    @Test
    public void testSetMedicalRecords() {
        Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
        MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");

        List<MedicalRecord> newRecords = new ArrayList<>();
        newRecords.add(testRecord);

        victim.setMedicalRecords(newRecords);
        List<MedicalRecord> actualRecords = victim.getMedicalRecords();
        assertEquals("setMedicalRecords should correctly update medical records", newRecords, actualRecords);
    }

    @Test
    public void testAddMedicalRecord() {
        Location testLocation = new Location("Hospital", "1234 Hospital St");
        MedicalRecord testRecord = new MedicalRecord(testLocation, "Checkup", "2024-03-01");
        victim.addMedicalRecord(testRecord);
        List<MedicalRecord> records = victim.getMedicalRecords();
        assertTrue("addMedicalRecord should add the medical record", records.contains(testRecord));
    }

    @Test
    public void testSetAndGetLocation() {
        Location testLocation = new Location("Shelter X", "111 Shelter St");
        victim.setLocation(testLocation);
        assertEquals("setLocation should update the location", testLocation, victim.getLocation());
    }

    // AGE SECTION:
    // These tests will check if the age/birthdate requirement is met.
    // There should only be one of the two fields set.
    @Test
    public void testAgeOrBirthdateRequirement() {
        victim.setApproximateAge(30);
        assertNull("Date of birth should be null when age is set", victim.getDateOfBirth());

        victim.setDateOfBirth("1990-01-01");
        assertNull("Approximate age should be null when date of birth is set", victim.getApproximateAge());
        assertEquals("Date of birth should be set correctly", "1990-01-01", victim.getDateOfBirth());
    }

    @Test
    public void testSetApproximateAgeInvalidatesDateOfBirth() {
        victim.setDateOfBirth("1987-05-21");
        victim.setApproximateAge(34);
        assertNull("Setting approximate age should nullify the date of birth", victim.getDateOfBirth());
        assertEquals("Approximate age should be set correctly", Integer.valueOf(34), victim.getApproximateAge());
    }

    @Test
    public void testSetDateOfBirthInvalidatesApproximateAge() {
        victim.setApproximateAge(34);
        victim.setDateOfBirth("1987-05-21");
        assertNull("Setting date of birth should nullify the approximate age", victim.getApproximateAge());
        assertEquals("Date of birth should be set correctly", "1987-05-21", victim.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        victim.setDateOfBirth(invalidDate);
    }

    // FAMILY SECTION:
    // This checks if the connection is added to both victims.
    @Test
    public void testAddFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim2, "parent", victim1);
        victim2.addFamilyConnection(relation);

        Set<FamilyRelation> testFamily1 = victim1.getFamilyConnections();
        Set<FamilyRelation> testFamily2 = victim2.getFamilyConnections();

        assertTrue("addFamilyConnection should add a family relationship",
                testFamily1.contains(relation) && testFamily2.contains(relation));
    }

    // This checks if the connection is removed from both victims.
    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim1, "sibling", victim2);

        victim1.addFamilyConnection(relation);
        victim1.removeFamilyConnection(relation);

        assertFalse("The connection should be removed from victim1", victim1.getFamilyConnections().contains(relation));
        assertFalse("The connection should also be removed from victim2",
                victim2.getFamilyConnections().contains(relation));
    }

    // This checks if the new connections are applied correctly.
    @Test
    public void testSetFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation1 = new FamilyRelation(victim1, "sibling", victim2);
        FamilyRelation relation2 = new FamilyRelation(victim1, "parent", victim2);
        Set<FamilyRelation> expectedRelations = new HashSet<>(Arrays.asList(relation1, relation2));
        victim1.setFamilyConnections(expectedRelations);

        Set<FamilyRelation> actualRelations1 = victim1.getFamilyConnections();
        Set<FamilyRelation> actualRelations2 = victim2.getFamilyConnections();

        assertTrue("setFamilyConnection should set family relations for victim1",
                actualRelations1.containsAll(expectedRelations));
        assertTrue("setFamilyConnection should set family relations for victim2",
                actualRelations2.containsAll(expectedRelations));
    }

    // This checks if the connection applies to both victims.
    @Test
    public void testTwoSidedRelationship() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation = new FamilyRelation(victim1, "sibling", victim2);

        assertTrue(victim1.getFamilyConnections().contains(relation));
        assertTrue(victim2.getFamilyConnections().contains(relation));
    }

    // This checks if the same relation is added multiple times.
    @Test
    public void testDuplicationPrevention() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation1 = new FamilyRelation(victim1, "sibling", victim2);
        FamilyRelation relation2 = new FamilyRelation(victim1, "sibling", victim2);

        assertEquals(1, victim1.getFamilyConnections().size());
        assertEquals(1, victim2.getFamilyConnections().size());
    }

    // This checks if connections are linked between family.
    @Test
    public void testSeriesRelationshipEnforcement() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        DisasterVictim victim3 = new DisasterVictim("Jake", "2024-01-23");

        new FamilyRelation(victim1, "sibling", victim2);
        new FamilyRelation(victim1, "sibling", victim3);

        victim1.validateFamilyNetwork();

        boolean relationshipEstablished = victim2.getFamilyConnections().stream()
                .anyMatch(relation -> relation.involves(victim3) && "sibling".equals(relation.getRelationshipTo()));

        assertTrue("A sibling relationship should be established between victim2 and victim3", relationshipEstablished);

    }

    // DIET SECTION:
    // These methods check if restrictions can be added, set and retrieved from
    // the enumeration class and from a victim's profile correctly.
    @Test
    public void testSetAndGetDietaryRestrictions() {

        DietaryRestriction restriction1 = DietaryRestriction.AVML;
        DietaryRestriction restriction2 = DietaryRestriction.VGML;

        List<DietaryRestriction> restrictions = new ArrayList<>(Arrays.asList(restriction1, restriction2));
        victim.setDietaryRestrictions(restrictions);

        assertEquals("getDietaryRestrictions should return the correct dietary restrictions", restrictions,
                victim.getDietaryRestrictions());
    }

    @Test
    public void testAddDietaryRestriction() {
        DisasterVictim victim = new DisasterVictim("John", "2024-01-01");
        victim.addDietaryRestriction(DietaryRestriction.VGML);
        victim.addDietaryRestriction(DietaryRestriction.KSML);

        List<DietaryRestriction> expectedRestrictions = Arrays.asList(DietaryRestriction.VGML, DietaryRestriction.KSML);
        assertEquals("Dietary restrictions should include VGML and KSML", expectedRestrictions,
                victim.getDietaryRestrictions());

        victim.addDietaryRestriction(DietaryRestriction.VGML);
        assertEquals("Dietary restrictions should still only include VGML and KSML once each", expectedRestrictions,
                victim.getDietaryRestrictions());
    }

    // GENDER SECTION:
    // This method was used to check if the list from the text file was being
    // correctly loaded.
    @Test
    public void testGenderOptionsLoadedCorrectly() {
        List<String> loadedGenders = Gender.getLoadedGenders();
        System.out.println("Loaded genders: " + loadedGenders);
        assertTrue("Loaded genders should contain 'non-binary'", loadedGenders.contains("non-binary"));
    }

    // This method checked if the loaded gender was correct, then sets it to a
    // victim.
    @Test
    public void testSetGenderWithValidOption() {
        String validGender = "non-binary";
        assertTrue("The gender should be valid as per GenderOptions.txt", Gender.isValidGender(validGender));

        victim.setGender(validGender);
        assertEquals("The gender should be set correctly", validGender, victim.getGender());
    }

    // This method checks if the set and get gender methods work with the loaded
    // gender.
    @Test
    public void testSetAndGetGender() {
        String validGender = "non-binary";
        victim.setGender(validGender);
        assertEquals("The gender should be set and retrieved correctly", validGender, victim.getGender());
    }
}
