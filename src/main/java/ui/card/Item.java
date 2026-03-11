package ui.card;

import ui.user.UserUi;
import app.Product;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public class Item extends Card {
    public Item(Product product, UserUi userUi) {
        super(product, 240, 240, 280, 40);
        this.setPreferredSize(new Dimension(300, 380));

        JLabel itemDescription = new JLabel(String.format("<html>%s</html>", product.description));
        itemDescription.setFont(new Font(UserUi.FONT, Font.ITALIC, 12));

        JLabel itemPrice = new JLabel(String.format("₱%.2f", product.cost));
        itemPrice.setForeground(new Color(0x68463C));
        itemPrice.setFont(new Font(UserUi.FONT, Font.BOLD, 32));

        JButton addToCart = new JButton(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "add.png", 48));
        addToCart.setBorderPainted(false);
        addToCart.setContentAreaFilled(false);
        addToCart.setFocusable(false);
        addToCart.addActionListener(_ -> userUi.basketTab.addBasket(product));

        this.textContainer.add(itemDescription);
        this.add(itemPrice);
        this.add(addToCart);
    }
}