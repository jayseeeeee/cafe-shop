import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Item extends Card {
    Item(Product product) {
        super(product, 128, 128, 164, 32);
        setPreferredSize(new Dimension(172, 230));

        JLabel itemDescription = new JLabel(String.format("<html>%s</html>", product.description));
        itemDescription.setFont(new Font(Shop.FONT, Font.ITALIC, 8));

        JLabel itemPrice = new JLabel(String.format("₱%.2f", product.cost));
        itemPrice.setForeground(new Color(0x68463C));
        itemPrice.setFont(new Font(Shop.FONT, Font.BOLD, 16));

        JButton addToCart = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/add.png"))));
        addToCart.setBorderPainted(false);
        addToCart.setContentAreaFilled(false);
        addToCart.setFocusable(false);
        addToCart.addActionListener(_ -> Product.shop.addBasket(product));

        textContainer.add(itemDescription);

        add(itemPrice);
        add(addToCart);
    }
}