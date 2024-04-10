/**
 * This class represents a disaster relief location. It holds information about the site,
 * including name and address, as well as managing the records of supplies and occupants
 * present at the location.
 * 
 * @author Rayyan Ahmed
 * @version 1.9
 * @since 1.0
 */

package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name;
    private String address;
    private List<DisasterVictim> occupants;
    private List<Supply> supplies;

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
        this.occupants = new ArrayList<>();
        this.supplies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<DisasterVictim> getOccupants() {
        return occupants;
    }

    public void setOccupants(List<DisasterVictim> occupants) {
        this.occupants = occupants;
    }

    public void addOccupant(DisasterVictim occupant) {
        this.occupants.add(occupant);
    }

    public void removeOccupant(DisasterVictim occupant) {
        this.occupants.remove(occupant);
    }

    public List<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<Supply> supplies) {
        this.supplies = supplies;
    }

    public void addSupply(Supply supply) {
        this.supplies.add(supply);
    }

    public void removeSupply(Supply supply) {
        this.supplies.remove(supply);
    }

    // This method meets a requirement by ensuring supply consistency. If a
    // supply is given to a victim, it is removed from the location.
    public void allocateSupply(DisasterVictim victim, Supply supply) {
        if (this.supplies.contains(supply)) {
            this.supplies.remove(supply);
            victim.addPersonalBelonging(supply);
        }
    }

}
