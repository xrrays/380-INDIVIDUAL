/**
 * This class contains tests for the Inquirer class, ensuring that inquirer details
 * are correctly managed and retrievable.
 * 
 * @author Rayyan Ahmed
 * @version 1.9
 * @since 1.0
 */

package edu.ucalgary.oop;

import org.junit.*;
import static org.junit.Assert.*;

public class InquirerTest {

    private String expectedFirstName = "Joseph";
    private String expectedLastName = "Bouillon";
    private String expectedPhoneNumber = "+1-123-456-7890";
    private String expectedMessage = "looking for my family members";
    private Inquirer inquirer = new Inquirer(expectedFirstName, expectedLastName, expectedPhoneNumber, expectedMessage);

    @Test
    public void testObjectCreation() {
        assertNotNull(inquirer);
    }

    @Test
    public void testGetFirstName() {
        assertEquals("getFirstName() should return inquirer's first name", expectedFirstName, inquirer.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("getLastName() should return inquirer's last name", expectedLastName, inquirer.getLastName());
    }

    @Test
    public void testGetServicesPhone() {
        assertEquals("getServicesPhone() should return the correct Services Phone", expectedPhoneNumber,
                inquirer.getServicesPhone());
    }

    @Test
    public void testGetInfo() {
        assertEquals("getInfo() should return the inquirer message", expectedMessage, inquirer.getInfo());
    }

}
