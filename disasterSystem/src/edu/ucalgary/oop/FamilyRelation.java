package edu.ucalgary.oop;

import java.util.Objects;

public class FamilyRelation {
    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private String relationshipTo;

    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        this.personOne = personOne;
        this.personTwo = personTwo;
        this.relationshipTo = relationshipTo;

        // Add this relation to both personOne's and personTwo's family connections
        personOne.addFamilyConnection(this);
        personTwo.addFamilyConnection(this);
    }

    public DisasterVictim getPersonOne() {
        return personOne;
    }

    public void setPersonOne(DisasterVictim personOne) {
        // First, check if we are changing the person
        if (this.personOne != null && !this.personOne.equals(personOne)) {
            // If so, remove the existing connection from the old personOne
            this.personOne.removeFamilyConnection(this);
        }

        // Then set the new personOne
        this.personOne = personOne;

        // Finally, add this relationship to the new personOne's connections
        if (personOne != null && !personOne.getFamilyConnections().contains(this)) {
            personOne.addFamilyConnection(this);
        }
    }

    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    public void setPersonTwo(DisasterVictim personTwo) {
        // First, check if we are changing the person
        if (this.personTwo != null && !this.personTwo.equals(personTwo)) {
            // If so, remove the existing connection from the old personTwo
            this.personTwo.removeFamilyConnection(this);
        }

        // Then set the new personTwo
        this.personTwo = personTwo;

        // Finally, add this relationship to the new personTwo's connections
        if (personTwo != null && !personTwo.getFamilyConnections().contains(this)) {
            personTwo.addFamilyConnection(this);
        }
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

    // Add this utility method in FamilyRelation class
    public boolean involves(DisasterVictim victim) {
        return this.personOne.equals(victim) || this.personTwo.equals(victim);
    }

    public DisasterVictim getOtherVictim(DisasterVictim victim) {
        if (victim.equals(personOne)) {
            return personTwo;
        } else if (victim.equals(personTwo)) {
            return personOne;
        } else {
            throw new IllegalArgumentException("The provided victim is not part of this relationship.");
        }
    }

    // Duplication prevention
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FamilyRelation that = (FamilyRelation) o;
        return (Objects.equals(personOne, that.personOne) && Objects.equals(personTwo, that.personTwo)) ||
                (Objects.equals(personOne, that.personTwo) && Objects.equals(personTwo, that.personOne));
    }

    @Override
    public int hashCode() {
        return Objects.hash(personOne, personTwo) + Objects.hash(personTwo, personOne);
    }
}
