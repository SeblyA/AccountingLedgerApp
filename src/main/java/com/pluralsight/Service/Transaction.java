package com.pluralsight.Service;
import java.time.*;

public class Transaction {
    //Declare Variable
    private  LocalDate date;
    private  LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    //Constructor


    public Transaction() {
    }



    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

// getter


    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                '}';
    }
}
