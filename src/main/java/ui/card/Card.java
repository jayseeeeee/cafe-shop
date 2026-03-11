package ui.card;

import ui.user.UserUi;
import app.Product;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;

public abstract class Card extends JPanel {
    JLabel cardImage;
    JLabel cardTitle;
    JPanel textContainer;
    Product product;

    Card(Product product, int imageWidth, int imageHeight, int textContainerWidth, int textContainerHeight) {
        this.product = product;
        this.setBackground(Color.white);
        this.setBorder(UserUi.BORDER_STYLE);

        cardImage = new JLabel(FileHandler.scaleImage(FileHandler.IMAGE_FOLDER, product.imagePath, imageHeight, imageWidth));
        cardTitle = new JLabel(product.name);
        cardTitle.setFont(new Font("Helvetica", Font.BOLD, 18));

        textContainer = new JPanel(new FlowLayout(FlowLayout.LEADING));
        textContainer.setOpaque(false);
        textContainer.setPreferredSize(new Dimension(textContainerWidth, textContainerHeight));
        textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
        textContainer.add(cardTitle);

        this.add(cardImage);
        this.add(textContainer);
    }
}