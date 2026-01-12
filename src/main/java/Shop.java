import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.*;

public class Shop extends JFrame {
    public final static LineBorder BORDER_STYLE = new LineBorder(new Color(0xFFCFCFCF, true), 1, true);
    public final static Color MAIN_COLOR = new Color(0xFAFAFA);
    public final static String FONT = "Helvetica";

    private final ArrayList<Product> selectionList;
    private final LinkedList<Product> basketList;
    private final LinkedList<Order> orderList;
    private final HashMap<String, JCheckBox> allergyFilterResult;
    private final JPanel basketContainer;
    private final JPanel selectionContainer;
    private final JPanel navigateContainer;
    private final JPanel queueContainer;
    private final JComboBox<String> paymentMethod;
    private final JTextField promoCode;
    private final JLabel initialCostText;
    private final JLabel discountText;
    private final JLabel finalCostText;
    private final JTextField searchField;
    private final JTextField pageSelection;
    private final JComboBox<String> categories;
    private final Payment payment;
    private final JLabel discountLabel;
    private double initialCost;
    private double finalCost;
    private Discount discount;

    Shop() {
        Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/brand_icon.png"))).getImage();
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(0xF3F3F3));
        logoPanel.setPreferredSize(new Dimension(1280,72));
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

        ImageIcon logoImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/brand_logo.png")));
        JLabel logo = new JLabel(logoImage);
        logoPanel.add(logo);

//        TODO: CREATE ADMIN DASHBOARD
//        JButton accessAdmin = new JButton();
//        logoPanel.add(accessAdmin);
//
        add(logoPanel, BorderLayout.NORTH);

        JPanel main = new JPanel();
        main.setBackground(Color.white);
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));

        // Basket Container
        JPanel basketSection = new JPanel();
        basketSection.setBackground(MAIN_COLOR);
        basketSection.setBorder(BORDER_STYLE);
        basketSection.setLayout(new FlowLayout(FlowLayout.LEADING));
        basketSection.setPreferredSize(new Dimension(1, 1));

        JLabel basketTitle = new JLabel("BASKET");
        basketTitle.setPreferredSize(new Dimension(225, 50));
        basketTitle.setFont(new Font(FONT, Font.BOLD, 24));
        basketSection.add(basketTitle);

        JLabel basketDescription = new JLabel("EDIT ORDER");
        basketDescription.setFont(new Font(FONT, Font.BOLD, 10));
        basketSection.add(basketDescription);

        basketList = new LinkedList<>();
        basketContainer = new JPanel();
        JScrollPane scrollPane = new JScrollPane(basketContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(320, 540));
        basketSection.add(scrollPane);

        main.add(basketSection);

        // Menu Container
        JPanel menuSection = new JPanel();
        menuSection.setBackground(MAIN_COLOR);
        menuSection.setBorder(BORDER_STYLE);
        menuSection.setPreferredSize(new Dimension(256, 1));
        menuSection.setLayout(new BoxLayout(menuSection, BoxLayout.Y_AXIS));

        JPanel filterContainer = new JPanel();
        filterContainer.setBackground(MAIN_COLOR);
        filterContainer.setPreferredSize(new Dimension(Integer.MIN_VALUE, 40));

        // Search Field
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(256, 32));
        searchField.addActionListener(_ -> updateSelection());
        filterContainer.add(searchField);

        // Search Button
        JButton searchButton = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("icons/search.png"))));
        searchButton.setContentAreaFilled(false);
        searchButton.setPreferredSize(new Dimension(32, 32));
        searchButton.addActionListener(_ -> updateSelection());
        filterContainer.add(searchButton);

        // Category
        categories = new JComboBox<>(new String[] {"Show All", "Coffee", "Tea", "Pastry", "Sandwich"});
        categories.setPreferredSize(new Dimension(128, 32));
        categories.setFocusable(false);
        categories.addActionListener(_ -> updateSelection());
        filterContainer.add(categories);
        menuSection.add(filterContainer);

        // Allergy
        allergyFilterResult = new HashMap<>();
        JPanel allergyContainer = new JPanel();
        allergyContainer.setBackground(MAIN_COLOR);
        allergyContainer.setPreferredSize(new Dimension(Integer.MIN_VALUE, 32));

        JLabel allergyTitle = new JLabel("FILTER FOOD ALLERGY");
        allergyTitle.setFont(new Font(FONT, Font.BOLD, 10));
        allergyContainer.add(allergyTitle);

        for (String allergy : Allergy.listOfAllergies) {
            JCheckBox checkBox = new JCheckBox(allergy);
            checkBox.setFocusable(false);
            checkBox.setContentAreaFilled(false);
            checkBox.setFont(new Font(FONT, Font.BOLD, 12));
            checkBox.setBackground(MAIN_COLOR);
            checkBox.addItemListener(_ -> {
                if (checkBox.isSelected()) {
                    allergyFilterResult.put(allergy, checkBox);
                } else {
                    allergyFilterResult.remove(allergy);
                }
                updateSelection();
            });
            allergyContainer.add(checkBox);
        }
        menuSection.add(allergyContainer);

        // Item Selection
        selectionList = new ArrayList<>();
        selectionContainer = new JPanel();
        selectionContainer.setBackground(MAIN_COLOR);
        selectionContainer.setPreferredSize(new Dimension(Integer.MIN_VALUE, 720));
        menuSection.add(selectionContainer);

        // Item Navigation
        navigateContainer = new JPanel();
        navigateContainer.setOpaque(false);
        navigateContainer.setPreferredSize(new Dimension(1, 32));

        JButton prevButton = new JButton("<");
        prevButton.setFocusable(false);
        prevButton.addActionListener(_ -> prevSelection());
        navigateContainer.add(prevButton);

        pageSelection = new JTextField("1");
        pageSelection.addActionListener(_ -> {
            try {
                int clampedValue = Math.clamp(Integer.parseInt(pageSelection.getText()), 1, (int) Math.ceil((double) selectionList.size() / 6));
                pageSelection.setText(String.valueOf(clampedValue));
                showSelection();
            } catch(Exception e) {
                pageSelection.setText("1");
            }
        });
        navigateContainer.add(pageSelection);

        JButton nextButton = new JButton(">");
        nextButton.setFocusable(false);
        nextButton.addActionListener(_ -> nextSelection());
        navigateContainer.add(nextButton);
        menuSection.add(navigateContainer);

        main.add(menuSection);

        // Queue Container
        JPanel queueSection = new JPanel();
        queueSection.setBackground(MAIN_COLOR);
        queueSection.setBorder(BORDER_STYLE);
        queueSection.setLayout(new FlowLayout(FlowLayout.LEADING));
        queueSection.setPreferredSize(new Dimension(1, 1));

        JLabel queueTitle = new JLabel("QUEUE");
        queueTitle.setPreferredSize(new Dimension(256, 50));
        queueTitle.setFont(new Font(FONT, Font.BOLD, 24));
        queueSection.add(queueTitle);

        JLabel orderNumber = new JLabel("ORDER NUMBER");
        orderNumber.setPreferredSize(new Dimension(128, 16));
        orderNumber.setFont(new Font(FONT, Font.BOLD, 12));
        queueSection.add(orderNumber);

        JLabel status = new JLabel("STATUS");
        status.setFont(new Font(FONT, Font.BOLD, 12));
        queueSection.add(status);

        orderList = new LinkedList<>();
        queueContainer = new JPanel();
        queueContainer.setLayout(new BoxLayout(queueContainer, BoxLayout.Y_AXIS));
        JScrollPane listPane = new JScrollPane(queueContainer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listPane.setPreferredSize(new Dimension(320, 240));
        queueSection.add(listPane);

        // Payment
        JPanel paymentTitleContainer = new JPanel(new FlowLayout(FlowLayout.LEADING));
        paymentTitleContainer.setBackground(MAIN_COLOR);
        paymentTitleContainer.setPreferredSize(new Dimension(320, 42));

        JLabel paymentTitle = new JLabel("PAYMENT");
        paymentTitle.setFont(new Font(FONT, Font.BOLD, 24));

        paymentTitleContainer.add(paymentTitle);
        queueSection.add(paymentTitleContainer);

        JPanel paymentContainer = new JPanel();
        paymentContainer.setBackground(MAIN_COLOR);
        paymentContainer.setPreferredSize(new Dimension(320, 92));

        // Payment Method
        paymentMethod = new JComboBox<>(new String[] {"Pay on Counter", "Cashless"});
        paymentMethod.setPreferredSize(new Dimension(256, 42));
        paymentMethod.setFont(new Font(FONT, Font.PLAIN, 16));
        paymentMethod.setFocusable(false);
        paymentContainer.add(paymentMethod);

        // Discount
        promoCode = new JTextField();
        promoCode.setPreferredSize(new Dimension(172, 32));
        promoCode.setFont(new Font(FONT, Font.BOLD, 16));
        promoCode.addActionListener(_ -> validateDiscount());
        paymentContainer.add(promoCode);

        JButton apply = new JButton("Apply");
        apply.setFocusable(false);
        apply.setPreferredSize(new Dimension(80, 32));
        apply.addActionListener(_ -> validateDiscount());
        paymentContainer.add(apply);

        queueSection.add(paymentContainer);

        // Payment Summary
        payment = new Payment(this);
        JPanel costContainer = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        costContainer.setBackground(MAIN_COLOR);
        costContainer.setPreferredSize(new Dimension(320, 64));

        // Initial Cost
        JLabel initialCostLabel = new JLabel("INITIAL COST");
        initialCostLabel.setPreferredSize(new Dimension(128, 12));
        initialCostLabel.setFont(new Font(FONT, Font.PLAIN, 12));
        costContainer.add(initialCostLabel);

        initialCostText = getInitialCostText();
        costContainer.add(initialCostText);

        // Discount Cost
        discountLabel = getDiscountLabel();
        costContainer.add(discountLabel);

        discountText = getDiscountText();
        costContainer.add(discountText);

        JSeparator line = getLine();
        costContainer.add(line);

        // Final Cost
        JLabel finalCostLabel = getFinalCostLabel();
        costContainer.add(finalCostLabel);

        finalCostText = getFinalCostText();
        costContainer.add(finalCostText);

        queueSection.add(costContainer);

        // Checkout
        JPanel checkoutPanel = getCheckoutPanel();
        queueSection.add(checkoutPanel);

        main.add(queueSection);
        updateBasket();
        updateSelection();
        this.add(main, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private JLabel getInitialCostText() {
        JLabel label = new JLabel("₱0.00");
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setPreferredSize(new Dimension(164, 12));
        label.setFont(new Font(FONT, Font.PLAIN, 12));
        return label;
    }

    private JLabel getDiscountLabel() {
        JLabel label = new JLabel("DISCOUNT");
        label.setPreferredSize(new Dimension(128, 12));
        label.setFont(new Font(FONT, Font.ITALIC, 12));
        return label;
    }

    private JLabel getDiscountText() {
        JLabel label = new JLabel("–₱0.00");
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setPreferredSize(new Dimension(150, 12));
        label.setFont(new Font(FONT, Font.ITALIC, 12));
        return label;
    }

    private JSeparator getLine() {
        JSeparator line = new JSeparator(SwingConstants.HORIZONTAL);
        line.setBackground(new Color(0xFFCFCFCF));
        line.setPreferredSize(new Dimension(328, 2));
        return line;
    }

    private JLabel getFinalCostLabel() {
        JLabel label = new JLabel("FINAL COST");
        label.setPreferredSize(new Dimension(128, 16));
        label.setFont(new Font(FONT, Font.BOLD, 16));
        return label;
    }

    private JLabel getFinalCostText() {
        JLabel label = new JLabel("₱0.00");
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setPreferredSize(new Dimension(180, 16));
        label.setFont(new Font(FONT, Font.BOLD, 16));
        return label;
    }

    private JPanel getCheckoutPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(MAIN_COLOR);
        panel.setPreferredSize(new Dimension(320, 52));

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFocusable(false);
        checkoutButton.setFont(new Font(FONT, Font.BOLD, 16));
        checkoutButton.setPreferredSize(new Dimension(168, 46));
        checkoutButton.addActionListener(_ -> checkout((String) paymentMethod.getSelectedItem()));
        panel.add(checkoutButton);

        return panel;
    }

    void addBasket(Product product) {
        if (basketList.contains(product)) {
            JComboBox<Integer> quantityComboBox = product.basket.quantity;
            int selectedIndex = quantityComboBox.getSelectedIndex();
            quantityComboBox.setSelectedIndex(Math.min(quantityComboBox.getItemCount() - 1, ++selectedIndex));
        } else {
            basketList.push(product);
        }
        updateBasket();
    }

    void removeBasket(Product product) {
        basketList.remove(product);
        product.basket.quantity.setSelectedIndex(0);
        System.out.println(basketList);
        updateBasket();
    }

    private void updateBasket() {
        basketContainer.removeAll();
        if (basketList.isEmpty()) {
            basketContainer.setLayout(new BorderLayout());
            JLabel emptyResultTitle = new JLabel("BASKET IS EMPTY");
            emptyResultTitle.setFont(new Font(FONT, Font.BOLD, 12));
            emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
            emptyResultTitle.setPreferredSize(new Dimension(256,  256));
            basketContainer.add(emptyResultTitle);
        } else {
            basketContainer.setLayout(new BoxLayout(basketContainer, BoxLayout.Y_AXIS));
            for (Product product : basketList) {
                basketContainer.add(product.basket);
            }
        }
        basketContainer.revalidate();
        basketContainer.repaint();
        updateInitialCost();
    }

    private void updateSelection() {
        pageSelection.setText("1");
        selectionList.clear();
        String selectedCategory = (String) categories.getSelectedItem();
        String searchText = searchField.getText().trim();
        System.out.println(selectedCategory);
        for (Product product : Product.listOfProducts) {
            boolean categoryMatch = product.category.equals(selectedCategory) || Objects.equals(selectedCategory, "Show All");
            boolean searchMatch = product.name.toLowerCase().contains(searchText.toLowerCase()) || searchText.isBlank();
            boolean allergyMatch = isAllergyMatch(product.allergies);
            if (!searchMatch || !categoryMatch || allergyMatch) {
                continue;
            }
            selectionList.add(product);
        }
        if (selectionList.isEmpty()) {
            selectionContainer.removeAll();
            selectionContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
            JPanel emptyResultPanel = new JPanel();
            emptyResultPanel.setBackground(MAIN_COLOR);
            emptyResultPanel.setPreferredSize(new Dimension(1000,210));

            JLabel emptyResultTitle = new JLabel("NO MATCHING PRODUCTS FOUND");
            emptyResultTitle.setFont(new Font(FONT, Font.BOLD, 14));
            emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
            emptyResultTitle.setPreferredSize(new Dimension(512,  14));

            JLabel emptyResultTip = new JLabel("TRY ADJUSTING YOUR SEARCH FILTER.");
            emptyResultTip.setFont(new Font(FONT, Font.BOLD, 12));
            emptyResultTip.setHorizontalAlignment(JLabel.CENTER);
            emptyResultTip.setPreferredSize(new Dimension(512,  12));

            selectionContainer.add(emptyResultPanel);
            selectionContainer.add(emptyResultTitle);
            selectionContainer.add(emptyResultTip);
            selectionContainer.revalidate();
            selectionContainer.repaint();
        } else {
            sortProductByName(selectionList, 0, selectionList.size() - 1);
            showSelection();
        }
        navigateContainer.setVisible(selectionList.size() > 6);
    }

    private boolean isAllergyMatch(Allergy allergy) {
        for (String allergyFilter : allergyFilterResult.keySet()) {
            if (allergy.productAllergies.contains(allergyFilter)) {
                return true;
            }
        }
        return false;
    }

    private static void sortProductByName(ArrayList<Product> list, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int pivotIndex = partition(list, lowIndex, highIndex);

            sortProductByName(list, lowIndex, pivotIndex - 1);   // Left side
            sortProductByName(list, pivotIndex + 1, highIndex);  // Right side
        }
    }

    private static int partition(ArrayList<Product> list, int lowIndex, int highIndex) {
        Product lastProduct = list.get(highIndex);  // pivot = last element
        int firstIndex = lowIndex - 1;        // index of smaller element

        for (int i = lowIndex; i < highIndex; i++) {
            if (list.get(i).name.compareTo(lastProduct.name) < 0) {
                firstIndex++;
                Collections.swap(list, firstIndex, i);
            }
        }

        Collections.swap(list, firstIndex + 1, highIndex);
        return firstIndex + 1;
    }

    private void showSelection() {
        selectionContainer.removeAll();
        selectionContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 18, 18));
        int currentPage = Integer.parseInt(pageSelection.getText()) - 1;
        int currentItem = currentPage * 6;
        for (int i = currentItem; i < (Math.min(currentItem + 6, (selectionList.size()))); i++) {
            selectionContainer.add(selectionList.get(i).item);
        }
        selectionContainer.revalidate();
        selectionContainer.repaint();
    }

    private void prevSelection() {
        pageSelection.setText(String.valueOf(Math.max(Integer.parseInt(pageSelection.getText()) - 1, 1)));
        showSelection();
    }

    private void nextSelection() {
        pageSelection.setText(String.valueOf(Math.min(Integer.parseInt(pageSelection.getText()) + 1, (int) Math.ceil((double) selectionList.size() / 6))));
        showSelection();
    }

    public void updateInitialCost() {
        initialCost = 0.00;
        for (Product product : basketList) {
            int quantity = (int) Objects.requireNonNull(product.basket.quantity.getSelectedItem());
            initialCost += (product.cost * quantity);
        }
        initialCostText.setText(String.format("₱%.2f", initialCost));
        updateFinalCost();
    }

    private void validateDiscount() {
        discount = Discount.validate(promoCode.getText().trim());
        if (discount == null && !promoCode.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "The voucher code you entered is invalid or no longer available.", "Voucher", JOptionPane.WARNING_MESSAGE);
        }
        updateFinalCost();
    }

    private void updateFinalCost() {
        finalCost = initialCost;
        discountLabel.setVisible(discount != null);
        discountText.setVisible(discount != null);

        // Compute for Full Price Order
        if (discount == null) {
            this.finalCostText.setText(String.format("₱%.2f", finalCost));
            return;
        }

        // Compute for Discounted Order
        if (discount.isPercentage) {
            finalCost = finalCost * (1 - discount.discountCost / 100.00);
            discountText.setText(String.format("– %d%%", (int) discount.discountCost));
        } else {
            finalCost = finalCost - discount.discountCost;
            discountText.setText(String.format("– ₱%.2f", discount.discountCost));
        }
        finalCost = Math.max(finalCost, 0.00);
        this.finalCostText.setText(String.format("₱%.2f", finalCost));
    }

    void checkout(String paymentMethod) {
        if (basketList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your basket is currently empty.", "Checkout", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Order order = payment.pay(paymentMethod, finalCost, basketList.toArray(new Product[0]));
        if (order != null) {
            basketList.clear();
            orderList.offer(order);

            if (discount != null) {
                discount.apply();
                discount = null;
                promoCode.setText("");
            }

            queueContainer.add(order);
            queueContainer.revalidate();
            queueContainer.repaint();

            basketContainer.removeAll();
            basketContainer.revalidate();
            basketContainer.repaint();

            updateInitialCost();
        }
    }
}
