package model;

public class Transaction {
    //Declare Variable
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    //Constructor
    public Transcation(String date, String time, String description, String vendor, Double amount);
    this.date=date;
    this.time=time;
    this.description=description;
    this.vendor=vendor;
    this.amount=amount;

// getter
    public String getDate() {
        return date;
    }

    public String getTime() {
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
        return date + "|" + time"|" +description "|" +vendor "|" +amount;
    }
}
