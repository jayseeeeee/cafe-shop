import java.net.URL;
import java.util.ArrayList;

public class Product {
    static ArrayList<Product> listOfProducts = new ArrayList<>();
    static Shop shop;

    String name;
    String category;
    String description;
    URL imagePath;
    double cost;
    Allergy allergies;
    Item item;
    Basket basket;

    private void newProduct(String name, String category, String description, String imagePath, double cost) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.imagePath = getClass().getResource(String.format("assets/%s", imagePath));
        this.cost = cost;
        if (this.imagePath == null) {
            this.imagePath = getClass().getResource("assets/no-image.png");
        }
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
