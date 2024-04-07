package edu.ucalgary.oop;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Gender {
    private static List<String> genderOptions = new ArrayList<>();

    static {
        try {
            genderOptions = Files.readAllLines(Paths.get("GenderOptions.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidGender(String gender) {
        return genderOptions.contains(gender);
    }
}
