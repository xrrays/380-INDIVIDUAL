package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InteractionLog {
    private Map<Inquirer, List<ReliefService>> interactions;

    public InteractionLog() {
        interactions = new HashMap<>();
    }

    public void logInteraction(Inquirer inquirer, ReliefService service) {
        interactions.computeIfAbsent(inquirer, k -> new ArrayList<>()).add(service);
    }

    public List<ReliefService> getInteractions(Inquirer inquirer) {
        return interactions.getOrDefault(inquirer, new ArrayList<>());
    }

    public List<Inquirer> searchInquirer(String query) {
        return interactions.keySet().stream()
                .filter(inquirer -> inquirer.getFirstName().contains(query) || inquirer.getLastName().contains(query))
                .collect(Collectors.toList());
    }
}
