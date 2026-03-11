package ui.user.queue;

import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public class CostContainer extends JPanel {
    public final JLabel initialCost = getInitialCost();
    public final JLabel discountTitle = getDiscountTitle();
    public final JLabel discount = getDiscount();
    public final JLabel finalCost = getFinalCost();

    public CostContainer() {
        this.setPreferredSize(new Dimension(420, 120));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.setOpaque(false);
        this.add(getInitialCostTitle());
        this.add(Box.createHorizontalStrut(200));
        this.add(initialCost);
        this.add(getDiscountPanel());
        this.add(getLine());
        this.add(getFinalCostTitle());
        this.add(Box.createHorizontalStrut(155));
        this.add(finalCost);
    }

    public JLabel getInitialCostTitle() {
        JLabel initialCostLabel = new JLabel("INITIAL COST");
        initialCostLabel.setFont(new Font(UserUi.FONT, Font.PLAIN, 20));
        return initialCostLabel;
    }

    private JLabel getInitialCost() {
        JLabel initialCost = new JLabel("₱0.00");
        initialCost.setFont(new Font(UserUi.FONT, Font.PLAIN, 18));
        return initialCost;
    }

    private JPanel getDiscountPanel() {
        JPanel discountPanel = new JPanel();
        discountPanel.setOpaque(false);
        discountPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        discountPanel.setPreferredSize(new Dimension(420,24));
        discountPanel.add(discountTitle);
        discountPanel.add(Box.createHorizontalStrut(250));
        discountPanel.add(discount);
        return discountPanel;
    }

    private JLabel getDiscountTitle() {
        JLabel discountTitle = new JLabel("DISCOUNT");;
        discountTitle.setFont(new Font(UserUi.FONT, Font.ITALIC, 16));
        discountTitle.setVisible(false);
        return discountTitle;
    }

    private JLabel getDiscount() {
        JLabel discount = new JLabel("–₱0.00");
        discount.setFont(new Font(UserUi.FONT, Font.ITALIC, 14));
        discount.setVisible(false);
        return discount;
    }

    private JSeparator getLine() {
        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setBackground(new Color(0xFFCFCFCF));
        line.setPreferredSize(new Dimension(400, 5));
        return line;
    }

    private JLabel getFinalCostTitle() {
        JLabel finalCostTitle = new JLabel("FINAL COST");
        finalCostTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 28));
        return finalCostTitle;
    }

    private JLabel getFinalCost() {
        JLabel finalCost = new JLabel("₱0.00");
        finalCost.setFont(new Font(UserUi.FONT, Font.BOLD, 20));
        return finalCost;
    }
}
