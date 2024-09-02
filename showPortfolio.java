package StockExchange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class showPortfolio {
    private static final String STOCK_FILE = "C:\\Users\\durma\\Videos\\java\\StockExchange\\buy.txt"; // Do not forget change it for yourself

    public static void showAllStocks() { // To show portfolio
        double totalPortfolioValue = 0.0;

        try (BufferedReader reader = new BufferedReader(new FileReader(STOCK_FILE))) {
            String line;
            StringBuilder output = new StringBuilder("Current stocks:\n");

            while ((line = reader.readLine()) != null) { // Transfers the character after each comma to the array
                String[] stockDetails = line.split(",");
                String stockName = stockDetails[0];
                double stockPrice = Double.parseDouble(stockDetails[1]);
                int stockQuantity = Integer.parseInt(stockDetails[2]);
                double totalValue = Double.parseDouble(stockDetails[3]);
                String date = stockDetails[4];
                
                // Adds to the total portfolio value
                totalPortfolioValue += totalValue;
                
                // Adds details for each share
                output.append("Stock: ").append(stockName)
                      .append("  -- Price: ").append(stockPrice)
                      .append("  -- Quantity: ").append(stockQuantity)
                      .append("  -- Total Value: ").append(totalValue)
                      .append("  -- Date: ").append(date).append("\n");
            } // This method is more efficient compared to the "+" operator in terms of performance when you want to perform a large number of text merging operations

            // Prints the total value at the top
            System.out.println("Total Portfolio Value: " + totalPortfolioValue);
            // Prints all stock details
            System.out.print(output.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}