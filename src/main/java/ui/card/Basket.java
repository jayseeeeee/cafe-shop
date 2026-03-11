package ui.card;

import ui.user.UserUi;
import app.Product;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public class Basket extends Card{
    public JComboBox<Integer> quantity;
    JButton trash;

    public Basket(Product product, UserUi userUi) {
        super(product, 128, 128, 128, 48);
        this.setPreferredSize(new Dimension(420, 164));
        this.setMaximumSize(new Dimension(420, 164));

        JLabel itemPrice = new JLabel(String.format("₱%.2f", product.cost));
        itemPrice.setFont(new Font(UserUi.FONT, Font.PLAIN, 18));

        quantity = new JComboBox<>(new Integer[] {1,2,3,4,5,6,7,8,9});
        quantity.setSelectedIndex(product.quantity - 1);
        quantity.setFont(new Font(UserUi.FONT, Font.PLAIN, 16));
        quantity.setPreferredSize(new Dimension(48,48));
        quantity.setFocusable(false);
        quantity.addActionListener(_ -> {
            product.quantity = quantity.getSelectedIndex() + 1;
            userUi.queueTab.updateInitialCost();
        });

        trash = new JButton(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "trash.png", 48));
        trash.setBorderPainted(false);
        trash.setContentAreaFilled(false);
        trash.setFocusable(false);
        trash.addActionListener(_ -> userUi.basketTab.removeBasket(product));

        textContainer.add(this.cardTitle);
        textContainer.add(itemPrice);

        this.add(textContainer);
        this.add(quantity);
        this.add(trash);
    }
}