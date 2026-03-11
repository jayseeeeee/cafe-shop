package util;

import app.Discount;
import app.Allergy;
import app.Product;

import javax.swing.*;
import java.io.*;

public class CSVParser {
    private final static String[] CSV_FILES = {
            "Products.csv",
            "Discounts.csv"
    };
    private final static String CONFIG_MESSAGE = """
                Configure the CSV files under 'My Shop' folder in your Documents folder.
                Relaunch the program after configuration.
                """;

    public static void readAllCsvFiles() {
        for (String csv : CSV_FILES) {
            readCsv(new File(FileHandler.PROGRAM_FOLDER, csv));
        }
    }

    private static void readCsv(File csvFile) {
        System.out.println(csvFile);
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            String[] data;
            reader.readLine(); // Skip header
            while((line = reader.readLine()) != null) {
                data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                createObjectFromCsv(data, csvFile);
            }
        } catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, CONFIG_MESSAGE, "New Configuration", JOptionPane.INFORMATION_MESSAGE);
            createCsvFile(csvFile);
            System.exit(0);
        } catch(IOException e){
            IO.println("Error: File could not be read.");
            System.exit(0);
        }
    }

    private static void createObjectFromCsv(String[] data, File csvFile) {
        String fileName = csvFile.getName();
        if (fileName.equals(CSV_FILES[0])) {
            if (data.length == 6) {
                new Product(data[0], data[1], data[2], data[3], Double.parseDouble(data[4]), new Allergy(data[5]));
            } else {
                new Product(data[0], data[1], data[2], data[3], Double.parseDouble(data[4]), null);
            }
        } else if (fileName.equals(CSV_FILES[1])) {
            new Discount(data[0], Integer.parseInt(data[1]), Boolean.parseBoolean(data[2]), Integer.parseInt(data[3]));
        }
    }

    private static void createCsvFile(File csvFile) {
        String fileName = csvFile.getName();
        try(FileWriter writer = new FileWriter(csvFile)) {
            if (fileName.equals(CSV_FILES[0])) {
                writer.write("Name,Category,Description,Image,Price,Allergies");
            } else if (fileName.equals(CSV_FILES[1])) {
                writer.write("Code,Amount,Percentage,Uses");
            }
            IO.println("File has been successfully written!");
        } catch(FileNotFoundException e){
            IO.println("Error: File location could not be located.");
            IO.println(e);
        } catch(IOException e){
            IO.println("Error: File could not be written.");
            IO.println(e);
        }
    }
}
