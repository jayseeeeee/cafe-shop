import javax.swing.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        new Product("Caffe Latte", "Coffee", "Espresso with steamed milk and foam topping.", "caffe_latte.png", 50.00, new Allergy("Milk"));
        new Product("Caffe Mocha", "Coffee", "Espresso with chocolate and steamed milk.", "caffe_mocha.png", 55.00, new Allergy("Milk"));
        new Product("Flat White", "Coffee", "Smooth espresso with velvety steamed milk.", "flat_white.png", 50.00, new Allergy("Milk"));
        new Product("Caramel Macchiato", "Coffee", "Espresso layered with milk and caramel drizzle.", "caramel_macchiato.png", 60.00, new Allergy("Milk"));
        new Product("Hazelnut Cappuccino", "Coffee", "Cappuccino infused with hazelnut flavor.", "hazelnut_cappuccino.png", 58.00, new Allergy("Tree Nuts"));
        new Product("Peanut Butter Latte", "Coffee", "Latte blended with peanut butter flavor.", "peanut_latte.png", 62.00, new Allergy("Peanuts"));
        new Product("Espresso Shot", "Coffee", "Strong and bold single espresso shot.", "espresso_shot.png", 35.00);
        new Product("Iced Americano", "Coffee", "Chilled espresso diluted with cold water.", "iced_americano.png", 45.00);
        new Product("Vanilla Latte", "Coffee", "Espresso with steamed milk and vanilla syrup.", "vanilla_latte.png", 55.00, new Allergy("Milk"));
        new Product("Almond Milk Latte", "Coffee", "Latte made with almond milk.", "almond_milk_latte.png", 60.00, new Allergy("Tree Nuts"));
        new Product("Wheat Coffee Blend", "Coffee", "Coffee infused with toasted wheat essence.", "wheat_coffee.png", 48.00, new Allergy("Wheat"));
        new Product("Eggnog Latte", "Coffee", "Seasonal latte with eggnog flavor.", "eggnog_latte.png", 65.00, new Allergy("Eggs"));
        new Product("Cold Brew", "Coffee", "Slow-steeped cold brew coffee.", "cold_brew.png", 50.00);

        new Product("Black Tea", "Tea", "Classic brewed black tea.", "black_tea.png", 35.00);
        new Product("Green Tea", "Tea", "Light and refreshing green tea.", "green_tea.png", 35.00);
        new Product("Milk Tea", "Tea", "Black tea mixed with sweetened milk.", "milk_tea.png", 45.00, new Allergy("Milk"));
        new Product("Thai Milk Tea", "Tea", "Sweet and creamy Thai-style tea.", "thai_milk_tea.png", 50.00, new Allergy("Milk"));
        new Product("Matcha Latte", "Tea", "Matcha blended with steamed milk.", "matcha_latte.png", 55.00, new Allergy("Milk"));
        new Product("Peanut Milk Tea", "Tea", "Milk tea infused with peanut flavor.", "peanut_milk_tea.png", 50.00, new Allergy("Peanuts"));
        new Product("Almond Tea", "Tea", "Warm tea infused with almond flavor.", "almond_tea.png", 48.00, new Allergy("Tree Nuts"));
        new Product("Wheat Barley Tea", "Tea", "Roasted barley tea with wheat notes.", "barley_tea.png", 40.00, new Allergy("Wheat"));
        new Product("Egg Pudding Tea", "Tea", "Milk tea topped with egg pudding.", "egg_pudding_tea.png", 55.00, new Allergy("Eggs"));
        new Product("Jasmine Tea", "Tea", "Fragrant jasmine-infused tea.", "jasmine_tea.png", 38.00);
        new Product("Oolong Tea", "Tea", "Smooth and aromatic oolong tea.", "oolong_tea.png", 42.00);
        new Product("Honey Lemon Tea", "Tea", "Warm tea with honey and lemon.", "honey_lemon_tea.png", 40.00);

        new Product("Ham & Cheese Sandwich", "Sandwich", "Ham layered with melted cheese.", "ham_cheese.png", 75.00, new Allergy("Milk"));
        new Product("Chicken Club Sandwich", "Sandwich", "Grilled chicken with lettuce and mayo.", "chicken_club.png", 85.00, new Allergy("Eggs"));
        new Product("Peanut Butter Sandwich", "Sandwich", "Creamy peanut butter spread on bread.", "peanut_butter_sandwich.png", 60.00, new Allergy("Peanuts"));
        new Product("Nutty Veggie Sandwich", "Sandwich", "Veggies topped with crushed nuts.", "nutty_veggie.png", 80.00, new Allergy("Tree Nuts"));
        new Product("Wheat Tuna Sandwich", "Sandwich", "Tuna salad on wheat bread.", "wheat_tuna.png", 70.00, new Allergy("Wheat"));
        new Product("Egg Salad Sandwich", "Sandwich", "Creamy egg salad filling.", "egg_salad.png", 65.00, new Allergy("Eggs"));
        new Product("Turkey Melt", "Sandwich", "Turkey slices with melted cheese.", "turkey_melt.png", 90.00, new Allergy("Milk"));
        new Product("Veggie Sandwich", "Sandwich", "Fresh vegetables on soft bread.", "veggie_sandwich.png", 60.00);
        new Product("Wheat BLT", "Sandwich", "BLT served on wheat bread.", "wheat_blt.png", 75.00, new Allergy("Wheat"));
        new Product("Cheesy Egg Sandwich", "Sandwich", "Egg and cheese on toasted bread.", "cheesy_egg.png", 70.00, new Allergy("Eggs", "Milk"));

        new Product("Croissant", "Pastry", "Flaky, buttery French pastry.", "croissant.png", 55.00, new Allergy("Wheat"));
        new Product("Chocolate Muffin", "Pastry", "Soft chocolate muffin.", "choco_muffin.png", 50.00, new Allergy("Eggs"));
        new Product("Peanut Butter Cookie", "Pastry", "Cookie made with peanut butter.", "peanut_cookie.png", 45.00, new Allergy("Peanuts"));
        new Product("Bread Roll", "Pastry", "Soft bread roll made with milk.", "bread_roll.png", 40.00, new Allergy("Milk"));
        new Product("Egg Tart", "Pastry", "Creamy egg custard in a flaky crust.", "egg_tart.png", 50.00, new Allergy("Eggs"));
        new Product("Brownie", "Pastry", "Chocolate brownie with mixed nuts.", "brownie.png", 60.00, new Allergy("Tree Nuts"));
        new Product("Strawberry Donut", "Pastry", "Soft donut glazed with strawberry icing.", "strawberry_donut.png", 48.00, new Allergy("Milk"));
        new Product("Butter Cake", "Pastry", "Soft buttery sponge cake.", "butter_cake.png", 55.00, new Allergy("Eggs"));
        new Product("Blueberry Muffin", "Pastry", "Soft muffin with blueberries.", "blueberry_muffin.png", 50.00, new Allergy("Wheat", "Eggs", "Milk"));
        new Product("Cinnamon Roll", "Pastry", "Soft roll with cinnamon swirl.", "cinnamon_roll.png", 65.00, new Allergy("Wheat", "Milk"));
        new Product("Banana Bread", "Pastry", "Moist banana loaf slice.", "banana_bread.png", 55.00, new Allergy("Wheat", "Eggs"));
        new Product("Cheesecake Slice", "Pastry", "Creamy cheesecake on a crust.", "cheesecake.png", 70.00, new Allergy("Milk", "Eggs", "Wheat"));
        new Product("Chocolate Cookie", "Pastry", "Classic buttery cookie.", "chocolate_cookie.png", 40.00, new Allergy("Milk", "Wheat", "Eggs"));
        new Product("Lemon Bar", "Pastry", "Tangy lemon filling on a crust.", "lemon_bar.png", 45.00, new Allergy("Wheat", "Eggs"));
        new Product("Apple Pie Slice", "Pastry", "Warm apple pie with flaky crust.", "apple_pie.png", 60.00, new Allergy("Wheat", "Eggs", "Milk"));

        new Discount("OliviaRodrigoDuterte2025", 20, true, -1);
        new Discount("Dookie$", 20, false, 1);

        Shop shop = new Shop();
        Product.setShop(shop);
    }
}