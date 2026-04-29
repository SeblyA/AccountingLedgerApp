package com.pluralsight.Service;

import java.util.Scanner;
import java.time.LocalDate;





public class Report {
    public static void displayReportsMenu() {
        Scanner scanner = new Scanner(System.in);
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
        switch (choice){
        case "1":
        displayMonthToDate();
    }
    }
    // prints current month report
public static void displayMonthToDate() {
    LocalDate currentDate = LocalDate.now();
    LocalDate startOfTheCurrentMonth = currentDate.withDayOfMonth(1);//search from the first day of the month
    System.out.println("Reports from " + startOfTheCurrentMonth  + "to" + currentDate);

}
//prints Previous Year
public static int displayPreviousYear(){
        LocalDate today=LocalDate.now();
        int previousYearValue=today.minusYears(1).getYear();
       for (Transaction t: transactions){
           LocalDate transactionDate = t.getDate();
           // chceck condition if transaction is in previous year
           if (transactionDate.getYear()==previousYearValue && transactionDate.getYear() !=today.getYear()){
               System.out.println("Previous Year: " +t.getDate() +"|"+ t.getTime()+"|"+t.getDescription +"|"+ t.getVendor()+ "|" +t.getAmount());

           }
       }
}
}

