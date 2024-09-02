package StockExchange;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class sellShares {
    private static final String STOCK_FILE = "C:\\Users\\durma\\Videos\\java\\StockExchange\\buy.txt"; // Do not forget change it for yourself
    private static final String TRANSACTION_FILE = "C:\\Users\\durma\\Videos\\java\\StockExchange\\transaction_history.txt";

    public static void sell(){ // To sell process
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter to sell name of your stock: ");
        String forSellName = scanner.nextLine(); // To get a name before checking if there is such a name or not, to
        
        // Reads existing stocks
        Map<String, String[]> stockMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(STOCK_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] stockDetails = line.split(","); // Then it places these packets in the array with the while loop
                stockMap.put(stockDetails[0], stockDetails);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Checks if stock already exists
        if(stockMap.containsKey(forSellName)){
            String[] existingDetails = stockMap.get(forSellName); // Name in file
            double existingPrice = Double.parseDouble(existingDetails[1]); // Price in file
            int existingQuantity = Integer.parseInt(existingDetails[2]); // Quantity in file
            double existingTotalValue = Double.parseDouble(existingDetails[3]); // Total value in file

            System.out.println("Enter to sell price of your stock: ");
            double sellStockPrice = scanner.nextDouble(); // Selling price
            System.out.println("Enter to sell quantity of your stock: ");
            int sellStockQuantity = scanner.nextInt(); // Selling quantity
            scanner.nextLine();
            System.out.println("Enter to sell date (YYYY-MM-DD): ");
            String sellStockDate = scanner.nextLine(); // Selling date
            double sellTotalValue = sellStockPrice * sellStockQuantity; // Selling total value
            double status; // process profit or loss

            if(sellStockQuantity <= 0){ // If selling quantity is zero or less
                System.out.println("The quantity cannot be 0 or less");
                System.exit(0);
            }
            else if(sellStockQuantity < existingQuantity){ // If selling quantity is less than quantity in file
                // Update stock details
                int newQuantity = existingQuantity - sellStockQuantity; // Calculates the new quantity
                double newTotalValue = existingTotalValue - (sellStockQuantity * sellStockPrice); // Calculates the new total value
                double newPrice = newTotalValue / newQuantity; // Calculates the new price
                status = ( sellStockPrice - existingPrice ) * sellStockQuantity; // Calculates status (profit or loss)
                if(status > 0){ // If status is bigger than zero
                    System.out.println("You are in profit form " + forSellName + ". Your profit is "+ status);
                }
                else{ // If status is less than zero
                    System.out.println("You are at loss from " + forSellName + ". Your loss is " + status);
                }
                stockMap.put(forSellName, new String[]{forSellName, String.valueOf(newPrice), String.valueOf(newQuantity), String.valueOf(newTotalValue), sellStockDate});
                logTransaction(forSellName, sellStockPrice, sellStockQuantity, sellStockQuantity * sellStockPrice, sellStockDate, "Sell", status);
            }
            else if(sellStockQuantity == existingQuantity){ // If selling quantity is equal to quantity in file
                status = sellTotalValue - existingTotalValue; // Calculates status (profit or loss)
                //Updates stock details 
                stockMap.remove(forSellName); 
                logTransaction(forSellName, sellStockPrice, sellStockQuantity, sellTotalValue, sellStockDate, "Sell", status);
                if(status > 0){ // If status is bigger than zero
                    System.out.println("You are in profit form " + forSellName + ". Your profit is "+ status);
                }
                else{
                    System.out.println("You are at loss from " + forSellName + ". Your loss is " + status);
                }
            }
            else{ // If status is less than zero
                System.out.println("The quantity cannot be more than current quantity");
                System.exit(0);
            }

            // Writes updated stock data back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STOCK_FILE))) {
            for (String[] stockDetails : stockMap.values()) {
                writer.write(String.join(",", stockDetails));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        }else{ // If your stock name you entered is wrong 
            System.out.println("This stock is not available");
            System.exit(0);
        }
        System.out.println("You sold stock successfully.");
        
        }

    private static void logTransaction(String forSellName, double sellStockPrice, int sellStockQuantity, double sellTotalValue, String sellStockDate,
    String string, double status) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
            writer.write(string + ": " + forSellName + "," + sellStockPrice + "," + sellStockQuantity + "," + sellTotalValue + "," + sellStockDate + "," + status);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
