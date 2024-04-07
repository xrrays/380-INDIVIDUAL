package edu.ucalgary.oop;

public class FamilyRelation {
    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private String relationshipTo;

    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        this.personOne = personOne;
        this.personTwo = personTwo;
        this.relationshipTo = relationshipTo;
        
        // When a new family relation is created, it should be added to both DisasterVictims' family connections
        personOne.addFamilyConnection(this);
        personTwo.addFamilyConnection(this);
    }

    public DisasterVictim getPersonOne() {
        return personOne;
    }

    public void setPersonOne(DisasterVictim personOne) {
        // Update the relationship from the perspective of personTwo as well
        if (this.personOne != null) {
            this.personOne.removeFamilyConnection(this);
        }
        personOne.addFamilyConnection(this);
        this.personOne = personOne;
    }

    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    public void setPersonTwo(DisasterVictim personTwo) {
        // Update the relationship from the perspective of personOne as well
        if (this.personTwo != null) {
            this.personTwo.removeFamilyConnection(this);
        }
        personTwo.addFamilyConnection(this);
        this.personTwo = personTwo;
    }

    public String getRelationshipTo() {
        return relationshipTo;
    }

    public void setRelationshipTo(String relationshipTo) {
        // Update the relationship for both persons involved
        for (FamilyRelation rel : personOne.getFamilyConnections()) {
            if (rel.getPersonTwo().equals(this.personTwo)) {
                rel.relationshipTo = relationshipTo;
            }
        }
        for (FamilyRelation rel : personTwo.getFamilyConnections()) {
            if (rel.getPersonOne().equals(this.personOne)) {
                rel.relationshipTo = relationshipTo;
            }
        }
        this.relationshipTo = relationshipTo;
    }
}
