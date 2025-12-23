import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Basket extends Card{
    JComboBox<Integer> quantity;
    JButton trash;

    Basket(Product product) {
        super(product, 72, 72, 76, 48);
        setPreferredSize(new Dimension(320, 96));
        setMaximumSize(new Dimension(320, 96));

        JLabel itemPrice = new JLabel(String.format("₱%.2f", product.cost));
        itemPrice.setFont(new Font(Shop.FONT, Font.BOLD, 12));

        JPanel editContainer = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        editContainer.setBackground(Color.white);
        editContainer.setPreferredSize(new Dimension(136, 42));

        quantity = new JComboBox<>(new Integer[] {1,2,3,4,5,6,7,8,9});
        quantity.setPreferredSize(new Dimension(64,32));
        quantity.setFocusable(false);
        quantity.addActionListener(_ -> Product.shop.updateInitialCost());

        trash = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/trash.png"))));
        trash.setBorderPainted(false);
        trash.setContentAreaFilled(false);
        trash.setFocusable(false);
        trash.setPreferredSize(new Dimension( 32, 32));
        trash.addActionListener(_ -> Product.shop.removeBasket(product));

        textContainer.add(this.cardTitle);
        textContainer.add(itemPrice);

        editContainer.add(quantity);
        editContainer.add(trash);

        add(textContainer);
        add(editContainer);
    }
}