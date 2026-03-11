package ui.user.basket;

import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public class LabelContainer extends JPanel {

    LabelContainer() {
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(getTitle());
        this.add(Box.createHorizontalStrut(164));
        this.add(getDescription());
    }

    private JLabel getTitle() {
        JLabel basketTitle = new JLabel("BASKET");
        basketTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        return basketTitle;
    }

    private JLabel getDescription() {
        JLabel basketDescription = new JLabel("EDIT ORDER");
        basketDescription.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return basketDescription;
    }
}
