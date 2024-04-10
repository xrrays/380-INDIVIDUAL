/**
 * This class helps manage the representation of family relationships between two victims.
 * Alongside other methods in DisasterVicitm, this class ensures the project requirements are met.
 * 
 * @author Rayyan Ahmed
 * @version 1.9
 * @since 1.0
 */

package edu.ucalgary.oop;

import java.util.Objects;

public class FamilyRelation {
    private DisasterVictim personOne;
    private DisasterVictim personTwo;
    private String relationshipTo;

    // Constructor that adds relationships both ways.
    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        this.personOne = personOne;
        this.personTwo = personTwo;
        this.relationshipTo = relationshipTo;

        personOne.addFamilyConnection(this);
        personTwo.addFamilyConnection(this);
    }

    public DisasterVictim getPersonOne() {
        return personOne;
    }

    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    public String getRelationshipTo() {
        return relationshipTo;
    }

    // The setPerson methods updates the relationship of the person.
    public void setPersonOne(DisasterVictim personOne) {
        if (this.personOne != null && !this.personOne.equals(personOne)) {
            this.personOne.removeFamilyConnection(this);
        }

        this.personOne = personOne;

        if (personOne != null && !personOne.getFamilyConnections().contains(this)) {
            personOne.addFamilyConnection(this);
        }
    }

    public void setPersonTwo(DisasterVictim personTwo) {
        if (this.personTwo != null && !this.personTwo.equals(personTwo)) {
            this.personTwo.removeFamilyConnection(this);
        }

        this.personTwo = personTwo;

        if (personTwo != null && !personTwo.getFamilyConnections().contains(this)) {
            personTwo.addFamilyConnection(this);
        }
    }

    // This method updates the relationship type for both persons.
    public void setRelationshipTo(String relationshipTo) {
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

    // This method helps the validateFamilyNetwork process by checking if a person
    // is involved in the relationship.
    public boolean involves(DisasterVictim victim) {
        return this.personOne.equals(victim) || this.personTwo.equals(victim);
    }

    // This method helps the validateFamilyNetwork process by returning the "other"
    // victim in the relationship, not provided in the argument.
    public DisasterVictim getOtherVictim(DisasterVictim victim) {
        if (victim.equals(personOne)) {
            return personTwo;
        } else if (victim.equals(personTwo)) {
            return personOne;
        } else {
            throw new IllegalArgumentException("The provided victim is not part of this relationship.");
        }
    }

    // This method checks if both objects represent the same relationship.
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

    // This method checks if both objects in the relationship have the same hash code, are equal.
    @Override
    public int hashCode() {
        return Objects.hash(personOne, personTwo) + Objects.hash(personTwo, personOne);
    }
}
