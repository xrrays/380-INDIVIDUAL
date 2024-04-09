package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Set;

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
        
            // Use List.get(index) instead of array indexing.
            String relationshipForPersonOne = testFamilyRelationObject.getPersonOne().getFamilyConnections().iterator().next().getRelationshipTo();
            String relationshipForPersonTwo = testFamilyRelationObject.getPersonTwo().getFamilyConnections().iterator().next().getRelationshipTo();

        
        assertEquals("setRelationshipTo should update the relationship for personOne", newRelationship, relationshipForPersonOne);
        assertEquals("setRelationshipTo should update the relationship for personTwo", newRelationship, relationshipForPersonTwo);
    }

    @Test
    public void testRelationshipUpdate() {
        FamilyRelation relation = new FamilyRelation(personOne, "sibling", personTwo);
    
        // Change the relationship
        relation.setRelationshipTo("parent");
        
        assertEquals("parent", relation.getRelationshipTo());
        assertEquals("parent", personOne.getFamilyConnections().iterator().next().getRelationshipTo());
        assertEquals("parent", personTwo.getFamilyConnections().iterator().next().getRelationshipTo());
    }
    
}
