package ui.user.queue;

import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public class LabelContainer extends JPanel {

    LabelContainer() {
        this.setOpaque(false);
        this.add(getQueueTitle());
        this.add(Box.createHorizontalStrut(32));
        this.add(getOrderNumber());
        this.add(Box.createHorizontalStrut(16));
        this.add(getStatusTitle());
    }

    private JLabel getQueueTitle() {
        JLabel queueTitle = new JLabel("QUEUE");
        queueTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        return queueTitle;
    }

    private JLabel getOrderNumber() {
        JLabel orderNumber = new JLabel("ORDER NUMBER");
        orderNumber.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return orderNumber;
    }

    private JLabel getStatusTitle() {
        JLabel statusTitle = new JLabel("STATUS");
        statusTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        return statusTitle;
    }

}
