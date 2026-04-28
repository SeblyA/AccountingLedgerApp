package com.pluralsight;
import java.util.Scanner;
import java.util.ArrayList;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String choice;
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        FileHandler fileHandler = new FileHandler();
        ArrayList<Transaction> transactions = fileHandler.loadFile();


        System.out.println("Home Screen");
        while (running) {

            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println("Choose an Option: ");
            choice = scanner.nextLine();
            switch (choice) {
                case "D":
                    deposit(scanner, transactions, fileHandler);
                    break;
                case "P":
                    payment(scanner, transactions,fileHandler);
                    break;


                case "X":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice ");
            }
        }
    }

    public static void deposit(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // save to csv file and Automatically generates timestamp
        String date = java.time.LocalDate.now().toString();
        String time = java.time.LocalDate.now().toString();
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        //Writes to csv
        fileHandler.saveTransaction(transaction);
        System.out.println("Deposit Saved ");
    }

    public static void payment(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // save to csv file and Automatically generates timestamp
        String date = java.time.LocalDate.now().toString();
        String time = java.time.LocalDate.now().toString();
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        //Writes to csv
        fileHandler.saveTransaction(transaction);
        System.out.println("Deposit Saved ");
    }

}



