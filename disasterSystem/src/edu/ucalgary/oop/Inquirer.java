/**
 * This class represents an inquirer and their details in the disaster management system.
 * Each inquirer is identified uniquely, enabling efficient inquiry tracking and support.
 * 
 * @author Rayyan Ahmed
 * @version 1.9
 * @since 1.0
 */

package edu.ucalgary.oop;

public class Inquirer {
    private String firstName;
    private String lastName;
    private String servicesPhone;
    private String info;

    // Constructor for creating new inquirers in the system.
    public Inquirer(String firstName, String lastName, String servicesPhone, String info) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.servicesPhone = servicesPhone;
        this.info = info;
    }

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

    public String getServicesPhone() {
        return servicesPhone;
    }

    public void setServicesPhone(String servicesPhone) {
        this.servicesPhone = servicesPhone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
