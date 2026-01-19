import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Allergy {
    static HashSet<String> listOfAllergies = new HashSet<>();
    ArrayList<String> productAllergies = new ArrayList<>();

    Allergy(String productAllergies) {
        String removeQuotation = productAllergies.replaceAll("\"", "");
        String[] allergies = removeQuotation.split(",\\s*");
        this.productAllergies.addAll(List.of(allergies));
        listOfAllergies.addAll(List.of(allergies));
        System.out.println(listOfAllergies);
    }
}
