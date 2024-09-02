package StockExchange;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class showTransaction {

    private static final String TRANSACTION_FILE = "C:\\Users\\durma\\Videos\\java\\StockExchange\\transaction_history.txt"; // Do not forget change it for yourself

    public static void showTransactions() {
        List<String> buyTransactions = new ArrayList<>(); // Buy transactions are stored in separate List<String> collections
        List<String> sellTransactions = new ArrayList<>(); // Sell transactions are stored in separate List<String> collections

        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;

            while ((line = reader.readLine()) != null) { // Transfers the character after each comma to the array
                String[] details = line.split(",");

                switch (details[0].split(":")[0]) { 
                    case "Buy":
                        buyTransactions.add(line); // Writes all purchases transactions in a title
                        break;

                    case "Sell":
                        sellTransactions.add(line); // Writes all sales transactions in a title
                        break;

                    default:
                        System.out.println("Unknown transaction type: " + details[0]);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Shows buying porcess
        System.out.println("Buy Transactions:");
        for (String transaction : buyTransactions) {
            printTransactionDetails(transaction);
        }
        // Shows selling process
        System.out.println("\nSell Transactions:");
        for (String transaction : sellTransactions) {
            printTransactionDetails(transaction);
        }
    }

    private static void printTransactionDetails(String transaction) {
        String[] details = transaction.split(","); //.split() allows us to split the text into the parts we want.
        String transactionType = details[0].split(":")[0]; 
        String stockName = details[0].split(":")[1].trim(); // .trim(), if there are unnecessary gaps at the beginning or end of these parts, it clears them.
        String price = details[1];
        String quantity = details[2];
        String totalValue = details[3];
        String date = details[4];
        StringBuilder output = new StringBuilder();

        output.append("Stock: ").append(stockName)
                      .append("  -- Price: ").append(price)
                      .append("  -- Quantity: ").append(quantity)
                      .append("  -- Total Value: ").append(totalValue)
                      .append("  -- Date: ").append(date).append("\n");

        if (transactionType.equals("Sell")) { // If process is selling, shows profit or loss
            String profitOrLoss = details[5];
            output.append("Profit/Loss: ").append(profitOrLoss).append("\n");
        }
        System.out.print(output.toString());
        System.out.println();
    }
}
