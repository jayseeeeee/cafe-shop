package ui.user.queue;

import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PaymentContainer extends JPanel {
    private final QueueTab queueTab;
    public final JComboBox<String> paymentMethod = getPaymentMethod();
    public final JTextField promoCode = getPromoCode();

    public PaymentContainer(QueueTab queueTab) {
        this.queueTab = queueTab;
        this.setPreferredSize(new Dimension(420, 200));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setOpaque(false);
        this.add(getPaymentTitle());
        this.add(paymentMethod);
        this.add(promoCode);
        this.add(getApplyButton());
    }

    private JLabel getPaymentTitle() {
        JLabel paymentTitle = new JLabel("PAYMENT");
        paymentTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 36));
        return paymentTitle;
    }

    private JComboBox<String> getPaymentMethod() {
        JComboBox<String> paymentMethod = new JComboBox<>(new String[] {"Pay on Counter"});
        paymentMethod.setPreferredSize(new Dimension(405, 64));
        paymentMethod.setFont(new Font(UserUi.FONT, Font.PLAIN, 18));
        paymentMethod.setFocusable(false);
        if (new File(FileHandler.ASSETS_FOLDER + "/" + "qr_code.png").exists()) {
            paymentMethod.addItem("Cashless");
        }
        return paymentMethod;
    }

    private JTextField getPromoCode() {
        JTextField promoCode = new JTextField();
        promoCode.setPreferredSize(new Dimension(300, 48));
        promoCode.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        promoCode.addActionListener(_ -> queueTab.validateDiscount());
        return promoCode;
    }

    private JButton getApplyButton() {
        JButton apply = new JButton("APPLY");
        apply.setFocusable(false);
        apply.setPreferredSize(new Dimension(100, 48));
        apply.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        apply.addActionListener(_ -> queueTab.validateDiscount());
        return apply;
    }
}
