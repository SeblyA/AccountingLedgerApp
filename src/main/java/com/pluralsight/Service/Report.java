package com.pluralsight.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


import static com.pluralsight.Service.LedgerScreen.ledgerScreen;
//import java.time.LocalDate;

public class Report {


    public static void displayReportsMenu(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {
        boolean running = true;
        while (true) {


            //Allows user to search Reports by date
            System.out.println("""
                    Search Reports
                    1)Month To Date
                    2)Previous Month
                    3)Year To Date
                    4)Previous Year
                    5)Search by Vendor
                    6)Custom Search
                    O) Back to ledger
                    H) Back to home page
                    """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    displayMonthToDate(transactions);
                    break;
                case "2":
                    displayPreviousMonth(transactions);
                    break;
                case "3":
                    displayYearToDate(transactions);
                    break;
                case "4":
                    displayPreviousYear();
                    break;
                case "5":
                    displaySearchByVendor(transactions, scanner);
                    break;
                case "6":
                    customSearch(transactions, scanner);
                    break;
                case"O":
                    ledgerScreen(scanner, transactions, fileHandler);
                   break;
                case"H":
                    System.out.println("""
                                    A)All
                                    P)Payment
                                    L)Ledger
                                    X) Exit
                                    """); // Back to homepage
                    break;

                default:
                        System.out.println("Invalid choice. Please Try again.");


            }
        }
    }

    // prints current month report
    public static void displayMonthToDate(ArrayList<Transaction> transaction) {
        // LocalDate
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate today = LocalDate.now();
        FileHandler fileHandler = new FileHandler();
        ArrayList<Transaction> transactions = fileHandler.loadFile();

        boolean found = false;
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-12s %-10s %-20s  %-15s %-10s%n"," Date", "Time", "Description", "vendor", "Amount");
        System.out.println("-------------------------------------------------------------------------------");

        for (Transaction t : transactions) {
            LocalDate transactionDate = t.getDate();
            if ((transactionDate.isEqual(firstDayOfMonth) || transactionDate.isAfter(firstDayOfMonth)) &&
                    (transactionDate.isEqual(today) || transactionDate.isBefore(today))) {
                found = true; // check if the report found
                System.out.printf("%-12s %-10s %-20s %-15s $%.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }
        }
        if (!found) {
            System.out.println("Search not found");
        }

    }
// print Previous Month
    public static void displayPreviousMonth(ArrayList<Transaction> transaction) {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Transaction> transactions = fileHandler.loadFile();
        LocalDate today = LocalDate.now();
        LocalDate startOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfLastMonth = today.withDayOfMonth(1).minusDays(1);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-12s %-10s %-20s  %-15s %-10s%n"," Date", "Time", "Description", "vendor", "Amount");
        System.out.println("-------------------------------------------------------------------------------");


        for (Transaction t : transactions) {
            LocalDate transactionDate = t.getDate();

            if (!transactionDate.isBefore(startOfLastMonth) &&
                    !transactionDate.isAfter(endOfLastMonth)) {

                System.out.printf("%-12s %-10s %-20s %-15s $%.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }
        }

    }
//Prints Annual reports
    public static void displayYearToDate(ArrayList<Transaction> transaction) {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Transaction> transactions = fileHandler.loadFile();
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = LocalDate.now().minusYears(1);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-12s %-10s %-20s  %-15s %-10s%n"," Date", "Time", "Description", "vendor", "Amount");
        System.out.println("-------------------------------------------------------------------------------");

        for (Transaction t : transactions) {
            LocalDate transactionDate = t.getDate();

            System.out.printf("%-12s %-10s %-20s %-15s $%.2f%n",
                    t.getDate(),
                    t.getTime(),
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount());
        }


    }

    //prints Previous Year
    public static int displayPreviousYear() {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Transaction> transactions = fileHandler.loadFile();

        LocalDate today = LocalDate.now();
        LocalDate startOfLastYear = today.minusYears(1).withDayOfYear(1);
        LocalDate endOfLastYear = today.withDayOfYear(1).minusDays(1);
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-12s %-10s %-20s  %-15s %-10s%n"," Date", "Time", "Description", "vendor", "Amount");
        System.out.println("-------------------------------------------------------------------------------");

        for (Transaction t : transactions) {
            LocalDate transactionDate = t.getDate();
            if (!transactionDate.isBefore(startOfLastYear) &&
                    !transactionDate.isAfter(endOfLastYear)) {

                System.out.printf("%-12s %-10s %-20s %-15s $%.2f%n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }


        }
        return 0;
    }

 // search by Vendor
    public static void displaySearchByVendor(ArrayList<Transaction> transactions, Scanner scanner) {
        System.out.println(" Enter Vendor Name: ");
        String Vendor = scanner.nextLine();
        System.out.println("Reports by Vendor Name " + Vendor);
        for (Transaction t : transactions) {
            if (t != null && t.getVendor().toLowerCase().contains(Vendor.toLowerCase())) {
                System.out.println(t);
            }
        }
    }
// allows user to search reports  by date
    public static void customSearch(ArrayList<Transaction> transactions, Scanner scanner) {
        System.out.print("Enter start date yyyy-MM-dd : ");
        String start = scanner.nextLine();

        System.out.print("Enter end date yyyy-MM-dd : ");
        String end = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine().toLowerCase();

        System.out.print("Enter vendor : ");
        String vendor = scanner.nextLine().toLowerCase();

        for (Transaction t : transactions) {

            LocalDate startDate =null;
            LocalDate endDate = null;
            if (!start.isEmpty()) {
                startDate = LocalDate.parse(start);
            }
            if (!end.isEmpty()) {
                endDate = LocalDate.parse(end);
            }
            boolean isMatch = true;

            if (startDate != null && t.getDate().isBefore(startDate)) {
                isMatch = false;
            }
            if (endDate != null && t.getDate().isAfter(endDate)) {
                isMatch = false;
            }
            if (!description.isEmpty() &&
                    !t.getDescription().toLowerCase().contains(description)) {
                isMatch = false;
            }
            if (!vendor.isEmpty() &&
                    !t.getVendor().toLowerCase().contains(vendor)) {
                isMatch = false;
            }
            if (isMatch) {
                System.out.println(t);
            }

        }
    }
}




