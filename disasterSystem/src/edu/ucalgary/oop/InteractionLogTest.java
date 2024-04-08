package edu.ucalgary.oop;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.List;

public class InteractionLogTest {

    private Inquirer inquirer;
    private DisasterVictim victim;
    private Location location;
    private ReliefService reliefService;
    private InteractionLog interactionLog;

    @Before
    public void setUp() {
        inquirer = new Inquirer("John", "Doe", "1234567890", "Looking for family");
        // Adjusted to match the DisasterVictim constructor parameters
        victim = new DisasterVictim("Jane", "2024-01-01");
        location = new Location("Shelter A", "1234 Shelter Ave");
        reliefService = new ReliefService(inquirer, victim, "2024-01-01", "Providing shelter", location);
        interactionLog = new InteractionLog();
    }

    @Test
    // log interactions between relief service and inquierer
    public void testLogInteraction() {
        // log interaction
        interactionLog.logInteraction(inquirer, reliefService);

        // retrieve interaction from log
        List<ReliefService> interactions = interactionLog.getInteractions(inquirer);

        // verify intera ction is stored correctly
        assertTrue("Interaction should be logged", interactions.contains(reliefService));
    }

    @Test
    // search for inquirer in interaction log
    public void testSearchInquirer() {
        // log interactions
        interactionLog.logInteraction(inquirer, reliefService);

        // search for inquirer
        List<Inquirer> searchResults = interactionLog.searchInquirer("John");

        // verify that the correct inquirer objects are returned
        assertEquals("Search should return one result", 1, searchResults.size());
        assertEquals("Search result should match the query", inquirer, searchResults.get(0));
    }
}
