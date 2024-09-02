package StockExchange;

import java.io.*;
import java.util.Scanner;

public class InvestorProfile {
    private static final String PROFILE_FILE = "C:\\Users\\durma\\Videos\\java\\StockExchange\\profile.txt"; // Do not forget change it for yourself
                                                                // This file is for saving informations you entered to file
    public static void manageProfile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add Profile");
        System.out.println("2. View Profile");
        System.out.println("3. Update Profile");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addProfile(scanner);
                break;
            case 2:
                viewProfile();
                break;
            case 3:
                updateProfile(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addProfile(Scanner scanner) { // To add new profile
        // We use try-catch blocks for catching and managing errors (exceptions) that may occur during the operation of our program
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROFILE_FILE))) { // To add, firstly we can use BufferedWriter for writing to file
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Surname: ");
            String surname = scanner.nextLine();
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Portfolio Number: ");
            String portfolioNumber = scanner.nextLine();

            writer.write(name + "," + surname + "," + id + "," + portfolioNumber); // Writes information you entered to file
            writer.newLine(); // For new line
            System.out.println("Profile added successfully.");
        } catch (IOException e) {
            e.printStackTrace(); // Or we could have used an error message
        }
    }

    private static void viewProfile() { // To we can show the profile
        try (BufferedReader reader = new BufferedReader(new FileReader(PROFILE_FILE))) {
            String profile = reader.readLine();
            if (profile != null) {
                String[] details = profile.split(",");
                System.out.println("Name: " + details[0]);
                System.out.println("Surname: " + details[1]);
                System.out.println("ID: " + details[2]);
                System.out.println("Portfolio Number: " + details[3]);
            } else {
                System.out.println("No profile found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateProfile(Scanner scanner) { // To update profile informations
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter New ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Portfolio Number: ");
        String portfolioNumber = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PROFILE_FILE))) { // Writes new informations you entered to file
            writer.write(name + "," + surname + "," + id + "," + portfolioNumber);
            writer.newLine();
            System.out.println("Profile updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
