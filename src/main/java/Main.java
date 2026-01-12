import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            IO.println(e);
        }

        String documentsPath = System.getProperty("user.home");
        String folderName = "Documents\\My Shop";
        String fileName = "Products.csv";
        File folder = new File(documentsPath, folderName);
        File csvFile =  new File(folder, fileName);
        System.out.println(folder);
        folder.mkdir();

        String configMessage = """
                It seems that you are launching this software for the first time.
                Configure the 'Product.csv' under 'My Shop' folder in your Documents folder first.
                Relaunch the program after configuration.
                """;
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            String[] object;
            reader.readLine();
            while((line = reader.readLine()) != null) {
                object = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                System.out.println(Arrays.toString(object));
                if (object.length == 5) {
                    new Product(object[0], object[1], object[2], object[3], Double.parseDouble(object[4]), new Allergy());
                } else {
                    new Product(object[0], object[1], object[2], object[3], Double.parseDouble(object[4]), new Allergy(object[5]));
                }
            }
        } catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, configMessage, "New Configuration", JOptionPane.INFORMATION_MESSAGE);
            createCsvFile(csvFile);
            System.exit(0);
            IO.println(e);
            IO.println("Error: File location could not be located.");
        } catch(IOException e){
            IO.println(e);
            IO.println("Error: File could not be read.");
        }


        new Discount("OliviaRodrigoDuterte2025", 20, true, -1);
        new Discount("Dookie$", 20, false, 1);

        Shop shop = new Shop();
        Product.setShop(shop);
    }

    private static void createCsvFile(File csvFile) {
        try(FileWriter writer = new FileWriter(csvFile)) {
            writer.write("Name,Category,Description,Image,Price,Allergies");
            IO.println("File has been successfully written!");
        } catch(FileNotFoundException e){
            IO.println(e);
            IO.println("Error: File location could not be located.");
        } catch(IOException e){
            IO.println(e);
            IO.println("Error: File could not be written.");
        }
    }
}