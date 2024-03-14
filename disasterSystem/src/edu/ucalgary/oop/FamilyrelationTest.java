package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;

public class FamilyRelationTest {

    private DisasterVictim personOne = new DisasterVictim("John Dalan", "2024-01-19");
    private DisasterVictim personTwo = new DisasterVictim("Jane Dalan", "2024-02-20");
    private String relationshipTo = "sibling";
    private FamilyRelation testFamilyRelationObject = new FamilyRelation(personOne, relationshipTo, personTwo);

    @Test
    public void testObjectCreation() {
        assertNotNull(testFamilyRelationObject);
    }

    @Test
    public void testSetAndGetPersonOne() {
        DisasterVictim newPersonOne = new DisasterVictim("New Person", "2024-03-21");
        testFamilyRelationObject.setPersonOne(newPersonOne);
        assertEquals("setPersonOne should update personOne", newPersonOne, testFamilyRelationObject.getPersonOne());
    }

    @Test
    public void testSetAndGetPersonTwo() {
        DisasterVictim newPersonTwo = new DisasterVictim("Another Person", "2024-04-22");
        testFamilyRelationObject.setPersonTwo(newPersonTwo);
        assertEquals("setPersonTwo should update personTwo", newPersonTwo, testFamilyRelationObject.getPersonTwo());
    }

    // test that when realtion is changed it is changed for both
    @Test
    public void testSetAndGetRelationshipTo() {
        String newRelationship = "parent";
        testFamilyRelationObject.setRelationshipTo(newRelationship);
        
        // Get the relationship for both personOne and personTwo
        String relationshipForPersonOne = testFamilyRelationObject.getPersonOne().getFamilyConnections()[0].getRelationshipTo();
        String relationshipForPersonTwo = testFamilyRelationObject.getPersonTwo().getFamilyConnections()[0].getRelationshipTo();
        
        assertEquals("setRelationshipTo should update the relationship for personOne", newRelationship, relationshipForPersonOne);
        assertEquals("setRelationshipTo should update the relationship for personTwo", newRelationship, relationshipForPersonTwo);
    }
