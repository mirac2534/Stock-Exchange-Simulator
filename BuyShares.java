package StockExchange;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BuyShares {

    private static final String STOCK_FILE = "C:\\Users\\durma\\Videos\\java\\StockExchange\\buy.txt"; // Do not forget change it for yourself , this file is for saving after purchase
    private static final String TRANSACTION_FILE = "C:\\Users\\durma\\Videos\\java\\StockExchange\\transaction_history.txt"; // This file ise for saving transaction history
    public static void buyStock() { // To buy process
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Stock Name: ");
        String stockName = scanner.nextLine();
        System.out.print("Enter Stock Price: ");
        double stockPrice = scanner.nextDouble();
        System.out.print("Enter Stock Quantity: ");
        int stockQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        double stockTotal = stockQuantity * stockPrice; 
        // Read existing stocks
        Map<String, String[]> stockMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(STOCK_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // The "split" operation is used to split characters into packages after each "," that it sees
                String[] stockDetails = line.split(","); // Then it places these packets in the array with the while loop
                stockMap.put(stockDetails[0], stockDetails);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if stock already exists
        if (stockMap.containsKey(stockName)) { // .containsKey is used to check whether it contains a specific key.
            String[] existingDetails = stockMap.get(stockName);
            double existingPrice = Double.parseDouble(existingDetails[1]);
            int existingQuantity = Integer.parseInt(existingDetails[2]);
            double existingTotalValue = Double.parseDouble(existingDetails[3]);

            // Update stock details
            int newQuantity = existingQuantity + stockQuantity;
            double newTotalValue = existingTotalValue + (stockPrice * stockQuantity);
            double newPrice = newTotalValue / newQuantity; 

            // This code adds a new information to stockMap using the stockName key
            stockMap.put(stockName, new String[]{stockName, String.valueOf(newPrice), String.valueOf(newQuantity), String.valueOf(newTotalValue), date});
            // This function is for writing process to transaction history file 
            logTransaction(stockName, stockPrice, stockQuantity, stockTotal, date, "Buy");
        } else {
            // Add new stock
            double totalValue = stockPrice * stockQuantity;
            double newPrice = stockPrice;
            stockMap.put(stockName, new String[]{stockName, String.valueOf(newPrice), String.valueOf(stockQuantity), String.valueOf(totalValue), date});
            logTransaction(stockName, stockPrice, stockQuantity, stockTotal, date, "Buy");
        }
        
        // Write updated stock data back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STOCK_FILE))) {
            for (String[] stockDetails : stockMap.values()) {
                writer.write(String.join(",", stockDetails));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("You bought stock successfully.");
    }
    private static void logTransaction(String stockName, double stockPrice, int stockQuantity, double totalValue, String stockDate,
    String string) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
            writer.write(string + ": " + stockName + "," + stockPrice + "," + stockQuantity + "," + totalValue + "," + stockDate);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}



