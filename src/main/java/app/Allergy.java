package app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Allergy {
    public static HashSet<String> listOfAllergies = new HashSet<>();
    public ArrayList<String> productAllergies = new ArrayList<>();

    public Allergy(String productAllergies) {
        String removeQuotation = productAllergies.replaceAll("\"", "");
        String[] allergies = removeQuotation.split(",\\s*");

        this.productAllergies.addAll(List.of(allergies));
        listOfAllergies.addAll(List.of(allergies));
        System.out.println(listOfAllergies);
    }
}
