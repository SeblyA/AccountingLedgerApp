package com.pluralsight.Service;

import com.pluralsight.Service.FileHandler;
import com.pluralsight.Service.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static com.pluralsight.Service.Report.displayReportsMenu;


public class LedgerScreen {

public static void ledgerScreen(Scanner scanner, ArrayList<Transaction> transactions, FileHandler fileHandler) {
    boolean isRunning = true;

    while (isRunning) {
        System.out.println(""" 
                    Ledger Screen
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
            case "R":
                displayReportsMenu(scanner, transactions, fileHandler);
                break;


            default:
                System.out.println("Invalid option please choose A,D,P orR ");
        }
    }
}

public static void displayAll(ArrayList<Transaction> transactions) {
    for (Transaction t : transactions) {
        System.out.println(t);
    }
}

//Display deposits
public static void displayDeposits(ArrayList<Transaction> transaction) {
    for (Transaction t : transaction) {
        if (t.getAmount() > 0) { // checks deposits are positive
            System.out.println(t);
        }
    }
}

//Display payments
public static void displayPayments(ArrayList<Transaction> transaction) {
    for (Transaction t : transaction) {
        if (t.getAmount() < 0) {
            System.out.println(t);
        }
    }
}



}








