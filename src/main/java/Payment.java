import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Payment {
    Shop shop;

    Payment(Shop shop) {
        this.shop = shop;
    }

    public Order pay(String paymentMethod, double finalCost, Product[] basketList) {
        if (paymentMethod.equals("Cashless")) {
            boolean isSuccessful = getGCashNumber(finalCost);
            if (isSuccessful) {
                return new Order(Status.Preparing, finalCost, basketList);
            }
        } else {
            return new Order(Status.Payment, finalCost, basketList);
        }
        return null;
    }

    private boolean getGCashNumber(double finalCost) {
        while (true) {
            String gcashNumber = JOptionPane.showInputDialog(shop, "Enter your GCash number (09XXXXXXXXX):", "GCash Number", JOptionPane.PLAIN_MESSAGE);
            if (gcashNumber == null) {
                return false;
            }

            boolean isMatch = gcashNumber.matches("09\\d{9}");
            if (!isMatch) {
                int confirm = JOptionPane.showConfirmDialog(shop, "The phone number entered is invalid. Would you like to try again?", "GCash Number", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE); // 'ImageIcon' replaces 'MessageType' icon
                if (confirm == JOptionPane.YES_OPTION) {
                    continue;
                }
                return false;
            }
            showQrCode(gcashNumber, finalCost);
            return true;
        }
    }

    private void showQrCode(String gcashNumber, double finalCost) {
        JDialog dialog = new JDialog();
        dialog.setSize(512, 600);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);

        String text = String.format("Please pay %.2f with the GCash QR Code", finalCost);
        ImageIcon imageicon = new ImageIcon(Objects.requireNonNull(Payment.class.getResource("payment/gcash_qr.png")));
        ImageIcon qrCode = new ImageIcon(imageicon.getImage().getScaledInstance(480, 480, Image.SCALE_SMOOTH));
        JLabel image = new JLabel(text, qrCode, JLabel.CENTER);
        image.setBackground(Color.white);
        image.setOpaque(true);
        image.setVerticalTextPosition(JLabel.BOTTOM);
        image.setHorizontalTextPosition(JLabel.CENTER);

        dialog.add(image);
        dialog.setVisible(true);
    }
}
