package edu.ucalgary.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Gender {
    private static List<String> genderOptions = new ArrayList<>();

    static {
        // Use class loader to get the resource as a stream
        try (InputStream is = Gender.class.getClassLoader().getResourceAsStream("edu/ucalgary/oop/GenderOptions.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            genderOptions = reader.lines().map(String::toLowerCase).collect(Collectors.toList());
        } catch (IOException | NullPointerException e) {
            System.err.println("Error loading GenderOptions.txt");
            e.printStackTrace();
        }
    }

    public static boolean isValidGender(String gender) {
        // Compare lower case version for case-insensitive matching
        return genderOptions.contains(gender.toLowerCase());
    }

    public static List<String> getLoadedGenders() {
        return new ArrayList<>(genderOptions);
    }
}
