package ui.user.queue;

import app.Discount;
import app.Product;
import app.Order;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public class QueueTab extends JPanel {
    public final UserUi userUi;
    public final LabelContainer labelContainer = new LabelContainer();
    public final CostContainer costContainer = new CostContainer();
    public final PaymentContainer paymentContainer = new PaymentContainer(this);
    private final JPanel queueContainer = new JPanel();
    private double initialCost;
    private double finalCost;
    private Discount discount;

    public QueueTab(UserUi userUi) {
        this.userUi = userUi;
        this.setOpaque(false);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(1, 1));
        this.add(labelContainer);
        this.add(getScrollPane());
        this.add(paymentContainer);
        this.add(costContainer);
        this.add(getCheckoutButton());
    }

    private JScrollPane getScrollPane() {
        queueContainer.setLayout(new BoxLayout(queueContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(queueContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(420, 440));
        return scrollPane;
    }

    private JButton getCheckoutButton() {
        JButton checkoutButton = new JButton("CHECKOUT");
        checkoutButton.setFocusable(false);
        checkoutButton.setFont(new Font(UserUi.FONT, Font.BOLD, 28));
        checkoutButton.setForeground(new Color(30,30,30));
        checkoutButton.setPreferredSize(new Dimension(200, 64));
        checkoutButton.addActionListener(_ -> checkout((String) paymentContainer.paymentMethod.getSelectedItem()));
        return checkoutButton;
    }

    public void updateInitialCost() {
        initialCost = 0.00;
        for (Product product : userUi.basketTab.basketList) {
            initialCost += (product.cost * product.quantity);
        }
        costContainer.initialCost.setText(String.format("₱%.2f", initialCost));
        updateFinalCost();
    }

    public void updateFinalCost() {
        finalCost = initialCost;
        costContainer.discountTitle.setVisible(discount != null);
        costContainer.discount.setVisible(discount != null);

        // Compute for Full Price Order
        if (discount == null) {
            costContainer.finalCost.setText(String.format("₱%.2f", finalCost));
            return;
        }

        // Compute for Discounted Order
        if (discount.isPercentage) {
            finalCost = finalCost * (1 - discount.discountCost / 100.00);
            costContainer.discount.setText(String.format("– %d%%", (int) discount.discountCost));
        } else {
            finalCost = finalCost - discount.discountCost;
            costContainer.discount.setText(String.format("– ₱%.2f", discount.discountCost));
        }
        finalCost = Math.max(finalCost, 0.00);
        costContainer.finalCost.setText(String.format("₱%.2f", finalCost));
    }

    public void validateDiscount() {
        discount = Discount.validate(paymentContainer.promoCode.getText().trim());
        if (discount == null && !paymentContainer.promoCode.getText().isBlank()) {
            userUi.setUi(UserUi.INVALID_VOUCHER_UI);
        }
        updateFinalCost();
    }

    public void checkout(String paymentMethod) {
        if (userUi.basketTab.basketList.isEmpty()) {
            userUi.setUi(UserUi.EMPTY_BASKET_UI);
            return;
        }
        Order order = new Order(finalCost, userUi.basketTab.basketList);
        if (paymentMethod.equals("Cashless")) {
            userUi.setUi(UserUi.QR_CODE_UI);
        }
        for (Product product : userUi.basketTab.basketList) {
            product.quantity = 0;
        }
        userUi.basketTab.basketList.clear();
        if (discount != null) {
            discount.apply();
            discount = null;
            paymentContainer.promoCode.setText("");
        }

        queueContainer.add(order);
        queueContainer.revalidate();
        queueContainer.repaint();

        userUi.basketTab.basketContainer.removeAll();
        userUi.basketTab.basketContainer.revalidate();
        userUi.basketTab.basketContainer.repaint();
        updateInitialCost();
    }
}
