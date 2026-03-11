package ui.user.menu;

import ui.user.UserUi;

import javax.swing.*;
import java.awt.*;

public class SelectionContainer extends JPanel {
    private final MenuTab menuTab;
    public final JButton prevButton = getPrevButton();
    public final JTextField pageSelection = getPageSelection();
    public final JButton nextButton = getNextButton();

    public SelectionContainer(MenuTab menuTab) {
        this.menuTab = menuTab;
        this.setOpaque(false);
        this.add(prevButton);
        this.add(pageSelection);
        this.add(nextButton);
    }

    private JButton getPrevButton() {
        JButton prevButton = new JButton("<");
        prevButton.setFont(new Font(UserUi.FONT, Font.PLAIN, 16));
        prevButton.setFocusable(false);
        prevButton.addActionListener(_ -> menuTab.prevSelection());
        return prevButton;
    }

    private JTextField getPageSelection() {
        JTextField pageSelection = new JTextField("1");
        pageSelection.setFont(new Font(UserUi.FONT, Font.PLAIN, 16));
        pageSelection.addActionListener(_ -> menuTab.changeSelection());
        return pageSelection;
    }

    private JButton getNextButton() {
        JButton nextButton = new JButton(">");
        nextButton.setFont(new Font(UserUi.FONT, Font.PLAIN, 16));
        nextButton.setFocusable(false);
        nextButton.addActionListener(_ -> menuTab.nextSelection());
        return nextButton;
    }

}
