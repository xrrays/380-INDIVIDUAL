/**
 * The Gender class is responsible for extracting from GenderOptions.txt,
 * and validating the list.
 * 
 * @author Rayyan Ahmed
 * @version 1.9
 * @since 1.0
 */

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

    // This static block reads the text file and populates a list with the contents.
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

    // This checks if the provided argument is one of the genders in the list.
    public static boolean isValidGender(String gender) {
        boolean isValid = genderOptions.contains(gender.toLowerCase());
        System.out.println("Checking if gender is valid: " + gender + " - " + (isValid ? "Valid" : "Invalid"));
        return isValid;
    }

    // A list of the loaded genders.
    public static List<String> getLoadedGenders() {
        return new ArrayList<>(genderOptions);
    }
}
