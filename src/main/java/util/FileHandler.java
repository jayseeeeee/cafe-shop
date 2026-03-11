package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Objects;

public class FileHandler {
    public final static String USER_DIRECTORY = System.getProperty("user.home");
    public final static File PROGRAM_FOLDER = new File(USER_DIRECTORY, "Documents\\My Shop");
    public final static File IMAGE_FOLDER = new File(PROGRAM_FOLDER + "\\Images");
    public final static File ASSETS_FOLDER = new File(PROGRAM_FOLDER + "\\Assets");

    public static void setDirectories() {
        PROGRAM_FOLDER.mkdir();
        IMAGE_FOLDER.mkdir();
        ASSETS_FOLDER.mkdir();
        createImageDirectory();
    }

    private static void createImageDirectory() {
        try {
            File assets = new File(Objects.requireNonNull(FileHandler.class.getResource("/assets/")).toURI());
            for (File image : Objects.requireNonNull(assets.listFiles())) {
                Path imagePath = Paths.get(ASSETS_FOLDER.getPath(), image.getName());
                if (!imagePath.toFile().exists()) {
                    Files.copy(Paths.get(image.toURI()), imagePath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println(image.getName());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ImageIcon scaleImage(File location, String name, int targetHeight) {
        ImageIcon image = new ImageIcon(location + "\\" + name);
        float scaleRatio = (float) targetHeight / image.getIconHeight();
        return new ImageIcon(
                image.getImage().getScaledInstance(
                        (int) (image.getIconWidth() * scaleRatio),
                        (int) (image.getIconHeight() * scaleRatio),
                        Image.SCALE_SMOOTH)
        );
    }

    public static ImageIcon scaleImage(File location, String name, int targetHeight, int targetWidth) {
        ImageIcon image = new ImageIcon(location + "\\" + name);
        return new ImageIcon(
                image.getImage().getScaledInstance(
                        targetWidth,
                        targetHeight,
                        Image.SCALE_SMOOTH)
        );
    }
}
