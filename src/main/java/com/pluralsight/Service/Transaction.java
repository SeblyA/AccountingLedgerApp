package com.pluralsight.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Transaction {
    //Declare Variable
    private  LocalDate date;
    private LocalTime time;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
    public String toString (){
         return String.format("%s| %s| %s| %s |$%.2f",date,time,description,vendor,amount);
}


    }

