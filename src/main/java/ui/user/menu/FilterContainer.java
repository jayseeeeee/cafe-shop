package ui.user.menu;

import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FilterContainer extends JPanel {
    private final MenuTab menuTab;

    public final JTextField searchField = getSearchField();
    public final JButton searchButton = getSearchButton();
    public final JComboBox<String> categories = getCategories();

    public FilterContainer(MenuTab menuTab) {
        this.menuTab = menuTab;
        this.setOpaque(false);
        this.add(searchField);
        this.add(searchButton);
        this.add(categories);
    }

    private JTextField getSearchField() {
        JTextField searchField = new JTextField();
        searchField.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        searchField.setPreferredSize(new Dimension(320, 48));
        searchField.addActionListener(_ -> menuTab.updateMenuContainer());
        return searchField;
    }

    private JButton getSearchButton() {
        JButton searchButton = new JButton(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "search.png", 48));
        searchButton.setContentAreaFilled(false);
        searchButton.addActionListener(_ -> menuTab.updateMenuContainer());
        return searchButton;
    }

    private JComboBox<String> getCategories() {
        JComboBox<String> categories = new JComboBox<>(new String[] {"Show All", "Coffee", "Tea", "Pastry", "Sandwich"});
        categories.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        categories.setPreferredSize(new Dimension(180, 48));
        categories.setFocusable(false);
        categories.addActionListener(_ -> menuTab.updateMenuContainer());
        return categories;
    }
}
