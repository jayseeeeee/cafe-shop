import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;

public class Product {
    static ArrayList<Product> listOfProducts = new ArrayList<>();
    static Shop shop;

    String name;
    String category;
    String description;
    Path imagePath;
    double cost;
    Allergy allergies;
    Item item;
    Basket basket;

    private void newProduct(String name, String category, String description, String imagePath, double cost) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.cost = cost;
        this.imagePath = new File(Shop.IMAGE_FOLDER, imagePath).toPath();
//        this.imagePath = getClass().getResource("assets/no-image.png");
        listOfProducts.add(this);
        item = new Item(this);
        basket = new Basket(this);
    }

    public Product(String name, String category, String description, String imagePath, double cost, Allergy allergies) {
        newProduct(name, category, description, imagePath, cost);
        this.allergies = allergies;
    }

    public Product(String name, String category, String description, String imagePath, double cost) {
        newProduct(name, category, description, imagePath, cost);
        this.allergies = new Allergy();
    }

    public static void setShop(Shop shop) {
        Product.shop = shop;
    }
}
