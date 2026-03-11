package ui.user.menu;

import app.Allergy;
import app.Product;
import ui.card.Item;
import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class MenuTab extends JPanel{
    public final UserUi userUi;
    public final FilterContainer filterContainer = new FilterContainer(this);
    public final AllergyContainer allergyContainer = new AllergyContainer(this);
    public final SelectionContainer selectionContainer = new SelectionContainer(this);
    public final JPanel menuContainer = getMenuContainer();

    public final HashMap<String, JCheckBox> allergyFilterResult = new HashMap<>();
    public final ArrayList<Product> menuList = new ArrayList<>();

    public MenuTab(UserUi userUi) {
        this.userUi = userUi;
        this.setOpaque(false);
        this.setBorder(UserUi.BORDER_STYLE);
        this.setPreferredSize(new Dimension(575, 1));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.add(filterContainer);
        this.add(allergyContainer);
        this.add(menuContainer);
        this.add(selectionContainer);
    }

    private JPanel getMenuContainer() {
        JPanel menuContainer = new JPanel();
        menuContainer.setOpaque(false);
        menuContainer.setPreferredSize(new Dimension(1000, 800));
        return menuContainer;
    }

    public void updateAllergy(JCheckBox checkBox, String allergy) {
        if (checkBox.isSelected()) {
            allergyFilterResult.put(allergy, checkBox);
        } else {
            allergyFilterResult.remove(allergy);
        }
        updateMenuContainer();
    }

    public boolean isAllergyMatch(Allergy allergy) {
        for (String allergyFilter : allergyFilterResult.keySet()) {
            if (allergy == null) {
                break;
            } else if (allergy.productAllergies.contains(allergyFilter)) {
                return true;
            }
        }
        return false;
    }

    private void showMenu() {
        menuContainer.removeAll();
        menuContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 20));
        int currentPage = Integer.parseInt(selectionContainer.pageSelection.getText()) - 1;
        int currentItem = currentPage * 6;
        for (int i = currentItem; i < (Math.min(currentItem + 6, (menuList.size()))); i++) {
            menuContainer.add(new Item(menuList.get(i), userUi));
        }
        menuContainer.revalidate();
        menuContainer.repaint();
    }

    public void updateMenuContainer() {
        selectionContainer.pageSelection.setText("1");
        menuList.clear();
        String selectedCategory = (String) filterContainer.categories.getSelectedItem();
        String searchText = filterContainer.searchField.getText().trim();
        System.out.println(selectedCategory);
        for (Product product : Product.listOfProducts) {
            boolean categoryMatch = product.category.equals(selectedCategory) || Objects.equals(selectedCategory, "Show All");
            boolean searchMatch = product.name.toLowerCase().contains(searchText.toLowerCase()) || searchText.isBlank();
            boolean allergyMatch = isAllergyMatch(product.allergies);
            if (!searchMatch || !categoryMatch || allergyMatch) {
                continue;
            }
            menuList.add(product);
        }
        if (menuList.isEmpty()) {
            showEmptyMenu();
        } else {
            sortProductByName(menuList, 0, menuList.size() - 1);
            showMenu();
        }
        selectionContainer.setVisible(menuList.size() > 6);
    }

    public void showEmptyMenu() {
        menuContainer.removeAll();
        menuContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        menuContainer.add(getEmptyResultTitle());
        menuContainer.add(getEmptyResultTip());
        menuContainer.revalidate();
        menuContainer.repaint();
    }

    private JLabel getEmptyResultTitle() {
        JLabel emptyResultTitle = new JLabel("NO MATCHING PRODUCTS FOUND");
        emptyResultTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 32));
        emptyResultTitle.setHorizontalAlignment(JLabel.CENTER);
        emptyResultTitle.setVerticalAlignment(JLabel.BOTTOM);
        emptyResultTitle.setPreferredSize(new Dimension(1000, 400));
        return emptyResultTitle;
    }

    private JLabel getEmptyResultTip() {
        JLabel emptyResultTip = new JLabel("TRY ADJUSTING YOUR SEARCH FILTER.");
        emptyResultTip.setFont(new Font(UserUi.FONT, Font.BOLD, 16));
        emptyResultTip.setHorizontalAlignment(JLabel.CENTER);
        return emptyResultTip;
    }

    private static void sortProductByName(ArrayList<Product> list, int firstIndex, int lastIndex) {
        if (firstIndex < lastIndex) {
            int pivotIndex = partition(list, firstIndex, lastIndex);
            sortProductByName(list, firstIndex, pivotIndex - 1);   // Left side
            sortProductByName(list, pivotIndex + 1, lastIndex);  // Right side
        }
    }

    private static int partition(ArrayList<Product> list, int firstIndex, int lastIndex) {
        Product lastProduct = list.get(lastIndex);  // pivot = last element
        int lowestIndex = firstIndex - 1;        // index of smaller element

        for (int i = firstIndex; i < lastIndex; i++) {
            if (list.get(i).name.compareTo(lastProduct.name) < 0) {
                lowestIndex++;
                Collections.swap(list, lowestIndex, i);
            }
        }

        Collections.swap(list, ++lowestIndex, lastIndex);
        return lowestIndex;
    }

    public void changeSelection() {
        try {
            int clampedValue = Math.clamp(Integer.parseInt(selectionContainer.pageSelection.getText()), 1, (int) Math.ceil((double) menuList.size() / 6));
            selectionContainer.pageSelection.setText(String.valueOf(clampedValue));
            showMenu();
        } catch(Exception e) {
            selectionContainer.pageSelection.setText("1");
        }
    }

    public void prevSelection() {
        selectionContainer.pageSelection.setText(String.valueOf(Math.max(Integer.parseInt(selectionContainer.pageSelection.getText()) - 1, 1)));
        showMenu();
    }

    public void nextSelection() {
        selectionContainer.pageSelection.setText(String.valueOf(Math.min(Integer.parseInt(selectionContainer.pageSelection.getText()) + 1, (int) Math.ceil((double) menuList.size() / 6))));
        showMenu();
    }
}
