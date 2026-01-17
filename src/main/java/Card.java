import javax.swing.*;
import java.awt.*;

public abstract class Card extends JPanel {
    JLabel cardImage;
    JLabel cardTitle;
    JPanel textContainer;
    Product product;

    Card(Product product, int imageWidth, int imageHeight, int textContainerWidth, int textContainerHeight) {
        this.product = product;
        setBackground(Color.white);
        setBorder(Shop.BORDER_STYLE);

        Image loadImage = new ImageIcon(product.image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH)).getImage();
        cardImage = new JLabel(new ImageIcon(loadImage));

        textContainer = new JPanel(new FlowLayout(FlowLayout.LEADING));
        textContainer.setBackground(Color.white);
        textContainer.setPreferredSize(new Dimension(textContainerWidth, textContainerHeight));

        cardTitle = new JLabel(product.name);
        cardTitle.setPreferredSize(new Dimension(textContainerWidth, 14));
        cardTitle.setFont(new Font("Helvetica", Font.BOLD, 12));

        textContainer.add(cardTitle);

        add(cardImage);
        add(textContainer);
    }
}