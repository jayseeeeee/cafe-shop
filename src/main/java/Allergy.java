import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Allergy {
    static HashSet<String> listOfAllergies = new HashSet<>();
    ArrayList<String> productAllergies = new ArrayList<>();

    Allergy(String ... productAllergies) {
        this.productAllergies.addAll(List.of(productAllergies));
        listOfAllergies.addAll(List.of(productAllergies));
    }
}
