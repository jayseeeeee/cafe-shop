package app;

import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;

public class Order extends JLabel {
    public static final int PAYMENT = 0;
    public static final int PREPARING = 1;
    public static final int SERVING = 2;
    private static int orderCount = 0;
    private final LinkedHashSet<Product> products;
    private final double finalCost;
    private final int status;

    public Order(double finalCost, LinkedHashSet<Product> products) {
        this.status = PAYMENT;
        this.finalCost = finalCost;
        this.products = products;
        this.createUiLabel();
        ++orderCount;
    }

    private void createUiLabel() {
        this.setLayout(new FlowLayout(FlowLayout.TRAILING));
        this.setBackground(Color.white);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setPreferredSize(new Dimension(420, 96));
        this.setMaximumSize(new Dimension(420, 96));

        this.setText(String.format("%03d", orderCount));
        this.setFont(new Font(UserUi.FONT, Font.BOLD, 32));

        ImageIcon statusIcon = null;
        switch (status) {
            case PAYMENT -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "payment_status.png", 48);
            case PREPARING -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "preparing_status.png", 48);
            case SERVING -> statusIcon = FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "serving_status.png", 48);
        }
        this.setIcon(statusIcon);
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setHorizontalTextPosition(LEFT);
        this.setIconTextGap(75);
    }
}
