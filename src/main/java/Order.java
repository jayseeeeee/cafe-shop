import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Order extends JPanel {
    private static int orderCount = 0;
    private final Product[] products;
    private final double finalCost;
    private Status status;
    private JLabel orderStatus;

    Order(Status status, double finalCost, Product ... products) {
        this.status = status;
        this.finalCost = finalCost;
        this.products = products;
        this.orderStatus = new JLabel();
        this.setPanel();
        this.setStatus(status);
        ++orderCount;
    }

    private void setPanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setBackground(Color.white);
        this.setBorder(Shop.BORDER_STYLE);
        this.setPreferredSize(new Dimension(320, 64));
        this.setMaximumSize(new Dimension(320, 64));

        JLabel orderNumber = new JLabel(String.format("%03d", orderCount));
        orderNumber.setPreferredSize(new Dimension(120, 48));
        orderNumber.setFont(new Font(Shop.FONT, Font.BOLD, 16));

        this.add(orderNumber);
        this.add(orderStatus);
    }

    private void setStatus(Status status) {
        ImageIcon statusIcon = null;
        switch (status) {
            case Payment -> statusIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("status/payment.png")));
            case Preparing -> statusIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("status/preparing.png")));
            case Serving -> statusIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("status/serving.png")));
        }
        this.orderStatus.setIcon(statusIcon);
    }
}
