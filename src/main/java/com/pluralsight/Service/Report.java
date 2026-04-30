package com.pluralsight.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
//import java.time.LocalDate;

public class Report {


    public static void displayReportsMenu(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {
        boolean running = true;
        while (true) {


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

    public static void displayPreviousMonth(ArrayList<Transaction> transaction) {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Transaction> transactions = fileHandler.loadFile();
        LocalDate today = LocalDate.now();
        LocalDate startOfLastMonth = today.minusMonths(1).withDayOfMonth(1);
        LocalDate endOfLastMonth = today.withDayOfMonth(1).minusDays(1);

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

    public static void displayYearToDate(ArrayList<Transaction> transaction) {
        FileHandler fileHandler = new FileHandler();
        ArrayList<Transaction> transactions = fileHandler.loadFile();
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = LocalDate.now().minusYears(1);
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

}
//public void searchByVendor(String vendor) {
//public static void printHeader() {
//       System.out.println("------------------------------------------------------------------------------------");
//       System.out.printf("%-12s %-10s %-20s  %-15s %-10s%n", " Date", "Time", "Description", "vendor", "Amount");
//       System.out.println("------------------------------------------------------------------------------------");
//}

