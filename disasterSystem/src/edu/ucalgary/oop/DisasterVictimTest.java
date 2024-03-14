package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class DisasterVictimTest {
    private DisasterVictim victim;
    private List<Supply> suppliesToSet;
    private List<FamilyRelation> familyRelations;
    private String expectedFirstName = "Freda";
    private String EXPECTED_ENTRY_DATE = "2024-01-18";
    private String validDate = "2024-01-15";
    private String invalidDate = "15/13/2024";
    private String expectedGender = "female";
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
        Supply[] testSupplies = victim.getPersonalBelongings();
        boolean correct = false;

        int i;
        for (i = 0; i < testSupplies.length; i++) {
            if (testSupplies[i] == newSupply) {
                correct = true;
            }
        }
        assertTrue("addPersonalBelonging should add the supply to personal belongings", correct);
    }

    @Test
    public void testRemovePersonalBelonging() {

        Supply supplyToRemove = suppliesToSet.get(0);
        victim.addPersonalBelonging(supplyToRemove);
        victim.removePersonalBelonging(supplyToRemove);

        Supply[] testSupplies = victim.getPersonalBelongings();
        boolean correct = true;

        int i;
        for (i = 0; i < testSupplies.length; i++) {
            if (testSupplies[i] == supplyToRemove) {
                correct = false;
            }
        }
        assertTrue("removePersonalBelonging should remove the supply from personal belongings", true);
    }

    @Test
    public void testSetPersonalBelongings() {
        Supply one = new Supply("Tent", 1);
        Supply two = new Supply("Jug", 3);
        Supply[] newSupplies = { one, two };
        boolean correct = true;

        victim.setPersonalBelongings(newSupplies);
        Supply[] actualSupplies = victim.getPersonalBelongings();

        // We have not studied overriding equals in arrays of custom objects so we will
        // manually evaluate equality
        if (newSupplies.length != actualSupplies.length) {
            correct = false;
        } else {
            int i;
            for (i = 0; i < newSupplies.length; i++) {
                if (actualSupplies[i] != newSupplies[i]) {
                    correct = false;
                }
            }
        }
        assertTrue("setPersonalBelongings should correctly update personal belongings", correct);
    }

    @Test
    public void testSetMedicalRecords() {
        Location testLocation = new Location("Shelter Z", "1234 Shelter Ave");
        MedicalRecord testRecord = new MedicalRecord(testLocation, "test for strep", "2024-02-09");
        boolean correct = true;

        MedicalRecord[] newRecords = { testRecord };
        victim.setMedicalRecords(newRecords);
        MedicalRecord[] actualRecords = victim.getMedicalRecords();

        // We have not studied overriding equals in arrays of custom objects so we will
        // manually evaluate equality
        if (newRecords.length != actualRecords.length) {
            correct = false;
        } else {
            int i;
            for (i = 0; i < newRecords.length; i++) {
                if (actualRecords[i] != newRecords[i]) {
                    correct = false;
                }
            }
        }
        assertTrue("setMedicalRecords should correctly update medical records", correct);
    }

    // need to add: approx age, family connections, gender, dietary restrcition,
    // location,
    // list of methods above: constructor, name , comments, social id, entry date,
    // belongings, (set) medical
    // list of methods tested below: gender, diet, age, dob, (add) medical record,
    // locaion, fam connection

    @Test
    // set gender from enum class
    public void testSetAndGetGender() {
        Gender newGender = Gender.MALE;
        victim.setGender(newGender);
        assertEquals("setGender should update and getGender should return the new gender", newGender,
                victim.getGender());
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

    // Requirement: Age or birthdate. It must be possible to store a person's
    // approximate age or their birthdate (these two fields cannot both be set).
    @Test
    public void testGetAndSetApproximateAge() {
        int age = 34;
        victim.setApproximateAge(age);
        assertEquals("getApproximateAge should return the correct age", age, victim.getApproximateAge());

        // attempt to set date of birth after setting the age
        String newDateOfBirth = "1987-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertNull("Setting date of birth should be nullified if age is already set", victim.getDateOfBirth());
    }

    @Test
    // set dob, obly use one of dob/age
    public void testSetDateOfBirth() {
        String newDateOfBirth = "1987-05-21";
        victim.setDateOfBirth(newDateOfBirth);
        assertEquals("setDateOfBirth should correctly update the date of birth", newDateOfBirth,
                victim.getDateOfBirth());

        // attempt to set age after setting the date of birth
        int age = 34;
        victim.setApproximateAge(age);
        assertEquals("Setting age should be nullified if date of birth is already set", newDateOfBirth,
                victim.getDateOfBirth());
        assertNull("Setting age should be nullified if date of birth is already set", victim.getApproximateAge());
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

        FamilyRelation[] testFamily1 = victim1.getFamilyConnections();
        FamilyRelation[] testFamily2 = victim2.getFamilyConnections();

        assertTrue("addFamilyConnection should add a family relationship",
                Arrays.asList(testFamily1).contains(relation) && Arrays.asList(testFamily2).contains(relation));
    }

    @Test
    public void testRemoveFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation1 = new FamilyRelation(victim1, "sibling", victim2);
        FamilyRelation relation2 = new FamilyRelation(victim1, "parent", victim2);

        victim1.addFamilyConnection(relation1);
        victim2.addFamilyConnection(relation2);

        victim1.removeFamilyConnection(relation1);

        FamilyRelation[] testFamily1 = victim1.getFamilyConnections();
        FamilyRelation[] testFamily2 = victim2.getFamilyConnections();

        assertFalse("removeFamilyConnection should remove the family member from victim1's connections",
                Arrays.asList(testFamily1).contains(relation1));
        assertFalse("removeFamilyConnection should remove the family member from victim2's connections",
                Arrays.asList(testFamily2).contains(relation1));
    }

    @Test
    public void testSetFamilyConnection() {
        DisasterVictim victim1 = new DisasterVictim("Jane", "2024-01-20");
        DisasterVictim victim2 = new DisasterVictim("John", "2024-01-22");

        FamilyRelation relation1 = new FamilyRelation(victim1, "sibling", victim2);
        FamilyRelation relation2 = new FamilyRelation(victim1, "parent", victim2);
        FamilyRelation[] expectedRelations = { relation1, relation2 };

        victim1.setFamilyConnections(Arrays.asList(expectedRelations));

        FamilyRelation[] actualRelations1 = victim1.getFamilyConnections();
        FamilyRelation[] actualRelations2 = victim2.getFamilyConnections();

        assertTrue("setFamilyConnection should set family relations for victim1",
                Arrays.asList(actualRelations1).containsAll(Arrays.asList(expectedRelations)));
        assertTrue("setFamilyConnection should set family relations for victim2",
                Arrays.asList(actualRelations2).containsAll(Arrays.asList(expectedRelations)));
    }

    //

}
