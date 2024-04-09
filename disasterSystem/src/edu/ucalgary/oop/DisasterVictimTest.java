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
    private List<FamilyRelation> familyRelations;
    private String expectedFirstName = "Freda";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String validDateOfBirth = "2024-01-15"; // Assuming the format is YYYY-MM-DD
    private String invalidDateOfBirth = "15/01/2024"; // Invalid format
    private String invalidDate = "15/13/2024";
    private String expectedGender = "FEMALE"; // Assuming it's an enum value
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

    @Test
    public void testConstructorWithValidEntryDate() {
        String validEntryDate = "2024-01-18";
        DisasterVictim victim = new DisasterVictim("Freda", validEntryDate);
        assertNotNull("Constructor should successfully create an instance with a valid entry date", victim);
        assertEquals("Constructor should set the entry date correctly", validEntryDate, victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidEntryDateFormat() {
        String invalidEntryDate = "18/01/2024"; // Incorrect format according to your specifications
        new DisasterVictim("Freda", invalidEntryDate);
        // Expecting IllegalArgumentException due to invalid date format
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthWithInvalidFormat() {
        victim.setDateOfBirth(invalidDate); // This format should cause an exception
    }

    @Test
    public void testSetAndGetFirstName() {
        String newFirstName = "Alice";
        victim.setFirstName(newFirstName);
        assertEquals("setFirstName should update and getFirstName should return the new first name", newFirstName, victim.getFirstName());
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
        // The next victim should have an ID one higher than the previous victim
        // Tests can be run in any order so two victims will be created
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
        assertTrue("addPersonalBelonging should add the supply to personal belongings", testSupplies.contains(newSupply));
    }

    @Test
    public void testRemovePersonalBelonging() {
        Supply supplyToRemove = suppliesToSet.get(0);
        victim.addPersonalBelonging(supplyToRemove);
        victim.removePersonalBelonging(supplyToRemove);
        assertFalse("removePersonalBelonging should remove the supply from personal belongings", victim.getPersonalBelongings().contains(supplyToRemove));
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
    public void testGenderOptionsLoadedCorrectly() {
        // Assuming you have a method in Gender class to get all loaded genders for debugging
        List<String> loadedGenders = Gender.getLoadedGenders();
        System.out.println("Loaded genders: " + loadedGenders);
        assertTrue("Loaded genders should contain 'non-binary'", loadedGenders.contains("non-binary"));
    }


    @Test
    public void testSetGenderWithValidOption() {
        String validGender = "non-binary"; // Correct case as per GenderOptions.txt
        assertTrue("The gender should be valid as per GenderOptions.txt", Gender.isValidGender(validGender));
    
        // Now set this gender to a DisasterVictim object
        victim.setGender(validGender);
        assertEquals("The gender should be set correctly", validGender, victim.getGender());
    }

    @Test
    public void testSetAndGetGender() {
        String validGender = "non-binary"; // Use the case as defined in GenderOptions.txt
        victim.setGender(validGender); // Assuming setGender checks for valid gender internally
        assertEquals("The gender should be set and retrieved correctly", validGender, victim.getGender());
    }
    
    
    

    @Test
    // set diet restriction from enum class
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
        victim.addDietaryRestriction(DietaryRestriction.VGML); // Adding a vegan meal restriction
        victim.addDietaryRestriction(DietaryRestriction.KSML); // Adding a kosher meal restriction
    
        List<DietaryRestriction> expectedRestrictions = Arrays.asList(DietaryRestriction.VGML, DietaryRestriction.KSML);
        assertEquals("Dietary restrictions should include VGML and KSML", expectedRestrictions, victim.getDietaryRestrictions());
    
        // Trying to add a duplicate restriction to see if it's handled correctly
        victim.addDietaryRestriction(DietaryRestriction.VGML);
        assertEquals("Dietary restrictions should still only include VGML and KSML once each", expectedRestrictions, victim.getDietaryRestrictions());
    }

    @Test
    public void testAgeOrBirthdateRequirement() {
        victim.setApproximateAge(30);
        assertNull("Date of birth should be null when age is set", victim.getDateOfBirth());

        victim.setDateOfBirth("1990-01-01");
        assertNull("Approximate age should be null when date of birth is set", victim.getApproximateAge());
        assertEquals("Date of birth should be set correctly", "1990-01-01", victim.getDateOfBirth());
    }

    @Test
    public void testSetApproximateAge() {
        // First, set a date of birth
        victim.setDateOfBirth("1987-05-21");
        // Now set an approximate age, which should nullify the date of birth
        victim.setApproximateAge(34);
        assertNull("Date of birth should be nullified if age is already set", victim.getDateOfBirth());
        assertEquals("Approximate age should be set", Integer.valueOf(34), victim.getApproximateAge());
    }

    @Test
    public void testSetDateOfBirth() {
        victim.setDateOfBirth("1987-05-21");
        assertEquals("setDateOfBirth should correctly update the date of birth", "1987-05-21", victim.getDateOfBirth());
        
        // Now set age, it should not affect the date of birth because date of birth is already set
        victim.setApproximateAge(34);
        assertEquals("Approximate age should be null if date of birth is already set", Integer.valueOf(34), victim.getApproximateAge());
    }

    @Test
    // add record update with location and victim
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

    // establish two sided relationship test
    // so add relations and remove relations from each person
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
    
    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
    
        FamilyRelation relation = new FamilyRelation(victim1, "sibling", victim2);
        
        // Add and then remove the connection
        victim1.addFamilyConnection(relation);
        victim1.removeFamilyConnection(relation);
    
        assertFalse("The connection should be removed from victim1", victim1.getFamilyConnections().contains(relation));
        assertFalse("The connection should also be removed from victim2", victim2.getFamilyConnections().contains(relation));
    }

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
    
        assertTrue("setFamilyConnection should set family relations for victim1", actualRelations1.containsAll(expectedRelations));
        assertTrue("setFamilyConnection should set family relations for victim2", actualRelations2.containsAll(expectedRelations));
    }

    @Test
    public void testTwoSidedRelationship() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
    
        FamilyRelation relation = new FamilyRelation(victim1, "sibling", victim2);
    
        assertTrue(victim1.getFamilyConnections().contains(relation));
        assertTrue(victim2.getFamilyConnections().contains(relation));
    }
    
    @Test
    public void testDuplicationPrevention() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
    
        FamilyRelation relation1 = new FamilyRelation(victim1, "sibling", victim2);
        FamilyRelation relation2 = new FamilyRelation(victim1, "sibling", victim2);
    
        assertEquals(1, victim1.getFamilyConnections().size());
        assertEquals(1, victim2.getFamilyConnections().size());
    }
    
    @Test
    public void testSeriesRelationshipEnforcement() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");
        DisasterVictim victim3 = new DisasterVictim("Jake", "2024-01-23");
    
        new FamilyRelation(victim1, "sibling", victim2);
        new FamilyRelation(victim1, "sibling", victim3);
    
        // Call validateFamilyNetwork for victim1 to ensure all indirect relationships are established
        victim1.validateFamilyNetwork();

        // Now check if the relationship between victim2 and victim3 is established
        boolean relationshipEstablished = victim2.getFamilyConnections().stream()
            .anyMatch(relation -> relation.involves(victim3) && "sibling".equals(relation.getRelationshipTo()));
        
        assertTrue("A sibling relationship should be established between victim2 and victim3", relationshipEstablished);
        
    }
    


    

}
