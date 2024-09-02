package StockExchange;

import java.util.Scanner;

public class StockExchange {
    
    public static int menu() { // to choose option
        int option;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice");
        System.out.println("1 -> investor profile");
        System.out.println("2 -> Buy shares");
        System.out.println("3 -> Sell shares");
        System.out.println("4 -> Show the portfolio");
        System.out.println("5 -> Show the transaction history");
        System.out.println("6 -> Exit");
        option = scanner.nextInt();
        return option;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int password = 2534; // the password is always the same
        int option;
        System.out.println("welcome to the investment app");
        System.out.println("Enter the password:");
        int password1 = scanner.nextInt();
        if(password == password1){
            System.out.println("Password you entered is correct.");
        }
        else{
            System.out.println("Password you entered is wrong!!");
            System.exit(0);
        }
        do{
            option = menu();
            switch(option){
                case 1:
                InvestorProfile.manageProfile();
                break;
                case 2:
                BuyShares.buyStock();
                break;
                case 3:
                sellShares.sell();
                break;
                case 4:
                showPortfolio.showAllStocks();
                break;
                case 5:
                showTransaction.showTransactions();
                break;
                case 6:
                System.out.println("Exiting...");
                System.exit(0);
                break;
                default:
                System.out.println("Invalid choice");
                break;
            }
        }while (option != 6);

        scanner.close();
    }
}
