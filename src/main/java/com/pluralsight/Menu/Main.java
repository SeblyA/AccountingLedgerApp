package com.pluralsight.Menu;

import com.pluralsight.Service.FileHandler;
import com.pluralsight.Service.Transaction;


import java.util.Scanner;
import java.util.ArrayList;

import static com.pluralsight.Service.LedgerScreen.ledgerScreen;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        String choice;
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        FileHandler fileHandler = new FileHandler();
        ArrayList<Transaction> transactions = fileHandler.loadFile();

//Display Home Screen options

        while (running) {
            System.out.println("Home Screen");
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
                    payment(scanner, transactions, fileHandler);
                    break;
                case "L":
                    ledgerScreen(scanner, transactions, fileHandler);
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
// Deposit users Input
    public static void deposit(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {
        System.out.print("Deposit Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // save to csv file and Automatically generates timestamp
        String date = java.time.LocalDate.now().toString();
        String time = java.time.LocalTime.now().withNano(0).toString();
        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        //Writes to csv
        fileHandler.saveTransaction(transaction);
        System.out.println("payment saved ");
    }

    // save to csv file and Automatically generates timestamp
    public static void payment(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {
        System.out.print("Payment Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -Math.abs(amount);// Shows payments as negative

        String date = java.time.LocalDate.now().toString();
        String time = java.time.LocalTime.now().withNano(0).toString();

        Transaction transaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(transaction);

        //Writes to csv
        fileHandler.saveTransaction(transaction);
        System.out.println("Deposit Saved ");
    }
}
















