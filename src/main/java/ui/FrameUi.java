package ui;

import ui.user.UserUi;
import util.FileHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Objects;

public abstract class FrameUi extends JFrame {
    public final static LineBorder BORDER_STYLE = new LineBorder(new Color(0xFFCFCFCF, true), 1, true);
    public final static Color MAIN_COLOR = new Color(0xFAFAFA);
    public final static String FONT = "Helvetica";

    public final JPanel mainPanel = getMainPanel();

    public FrameUi() {
        Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/assets/brand_icon.png"))).getImage();
        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.add(getLogo(128), BorderLayout.NORTH);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public JPanel getLogo(int height) {
        JLabel logo = new JLabel(FileHandler.scaleImage(FileHandler.ASSETS_FOLDER, "brand_logo.png", height));
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setPreferredSize(new Dimension(1280, height));
        logoPanel.add(logo);
        return logoPanel;
    }

    public JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(MAIN_COLOR);
        return mainPanel;
    }
}
