package edu.ucalgary.oop;

import java.util.Objects;

public class Inquirer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String servicesPhone;
    private String info;

    // Constructor without ID for creating new inquirers
    public Inquirer(String firstName, String lastName, String servicesPhone, String info) {
        this(null, firstName, lastName, servicesPhone, info);
    }

    // Constructor with ID for when you fetch an inquirer from the database
    public Inquirer(Integer id, String firstName, String lastName, String servicesPhone, String info) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.servicesPhone = servicesPhone;
        this.info = info;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getServicesPhone() {
        return servicesPhone;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Inquirer inquirer = (Inquirer) o;
        return Objects.equals(firstName, inquirer.firstName) &&
                Objects.equals(lastName, inquirer.lastName) &&
                Objects.equals(servicesPhone, inquirer.servicesPhone) &&
                Objects.equals(info, inquirer.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, servicesPhone, info);
    }
}
