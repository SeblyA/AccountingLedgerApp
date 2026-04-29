package com.pluralsight.Service;
import com.pluralsight.Service.FileHandler;
import com.pluralsight.Service.Transaction;


import java.time.LocalDate;
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

    public static void deposit(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {
        System.out.print("Deposit Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // save to csv file and Automatically generates timestamp
        String date = java.time.LocalDate.now().toString();
        String time = java.time.LocalDate.now().toString();
       Transaction transaction = new Transaction(date, time, description, vendor, amount);
        Transaction t= new Transaction();
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
        amount = -Math.abs(amount);


        String date = java.time.LocalDate.now().toString();
        String time = java.time.LocalDate.now().toString();
     //Transaction transaction = new Transaction(localDate, localTime, description, vendor, amount);
        Transaction transaction = new Transaction();
        transactions.add(transaction);

        //Writes to csv
        fileHandler.saveTransaction(transaction);
        System.out.println("Deposit Saved ");
    }
    public static class LedgerScreen {
        public static void LedgerScreen(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {
            boolean isRunning = true;

            while (isRunning) {
                System.out.println("""
                    A)All
                    D)Deposits
                    P)Payments
                    R)Reports
                    Choose an option
                    """);
                String choice = scanner.nextLine().trim().toUpperCase();
                switch (choice) {
                    case "A":
                        displayAll(transactions);
                        break;
                    case "D":
                        displayDeposits(transactions);
                        break;
                    case "P":
                        displayPayments(transactions);
                        break;
                    case"R":
                       displayReportsMenu( scanner, transactions , fileHandler);
                        break;
                    default:
                        System.out.println("Invalid option please choose A,D,P orR ");
                }
            }
        }
        public static void displayAll (ArrayList < Transaction > transactions){
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
        //Display deposits
        public static void displayDeposits(ArrayList<Transaction>transaction){
            for (Transaction t: transaction){
                if (t.getAmount()>0) { // checks deposits are positive
                    System.out.println(t);
                }
            }
        }
        //Display payments
        public static void displayPayments(ArrayList<Transaction>transaction){
            for (Transaction t: transaction) {
                if (t.getAmount() < 0) {
                    System.out.println(t);
                }
            }
        }
    }
    public static void ledgerScreen(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("""
                    A)All
                    D)Deposits
                    P)Payments
                    R)Reports
                    Choose an option
                    """);
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "A":
                    displayAll(transactions);
                    break;
                case "D":
                    displayDeposits(transactions);
                    break;
                case "P":
                    displayPayments(transactions);
                    break;


                default:
                    System.out.println("Invalid option please choose A,D,P orR ");
            }
        }
    }
    public static void displayAll (ArrayList < Transaction > transactions){
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
    //Display deposits
    public static void displayDeposits(ArrayList<Transaction>transaction){
        for (Transaction t: transaction){
            if (t.getAmount()>0) { // checks deposits are positive
                System.out.println(t);
            }
        }
    }
    //Display payments
    public static void displayPayments(ArrayList<Transaction>transaction){
        for (Transaction t: transaction) {
            if (t.getAmount() < 0) {
                System.out.println(t);
            }
        }
    }
    public static void displayReportsMenu(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {


        //Allows user to search Reports by date
        System.out.println("""
                1)Month To Date
                2)Previous Month
                3)Year To Date
                4)Previous Year
                5)Search by Vendor
                O) Back to ledger
                H) Back to home page
                """);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                displayMonthToDate();
                break;
        }
    }

    // prints current month report
    public static void displayMonthToDate() {
        // LocalDate
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate today = LocalDate.now();
        FileHandler fileHandler = new FileHandler();
        ArrayList<Transaction> transactions = fileHandler.loadFile();
        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            if ((transactionDate.isEqual(firstDayOfMonth) || transactionDate.isAfter(firstDayOfMonth)) &&
                    (transactionDate.isEqual(today) || transactionDate.isBefore(today))) {

                System.out.println("------------------------------------------------------");
                System.out.printf("%-12s %-10s %-25 %10\n Date", "Time", "Description", "vendor", "Amount");
                System.out.println("------------------------------------------------------");

            }
        }

    }

    //prints Previous Year
    public static int displayPreviousYear() {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Transaction> transactions = fileHandler.loadFile();

        LocalDate today = LocalDate.now();
        int previousYearValue = today.minusYears(1).getYear();
        for (Transaction t : transactions) {
            LocalDate transactionDate = t.getDate();
            // check condition if transaction is in previous year
            if (transactionDate.getYear() == previousYearValue && transactionDate.getYear() != today.getYear()) {
//                System.out.println("Previous Year: " + t.getDate() + "|" + t.getLocalTime() + "|" + t.getdescription + "|" + t.getvendor() + "|" + t.getAmount());

            }
        }
        return 0;
    }
}
//public void searchByVendor(String vendor) {
//}




