package com.pluralsight.Service;

import java.util.ArrayList;
import java.io.*;
import java.util.Comparator;


public class FileHandler {
    public ArrayList<Transaction>loadFile() {
        ArrayList<Transaction> Transactions = new ArrayList<>();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("Transaction.csv");
            if (input == null) {
                throw new FileNotFoundException("Resource not found");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String  date= parts[0].trim();
                    String time= parts[1].trim();
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim()); // converts text to number
                    Transactions.add(new Transaction(date, time, description, vendor, amount));
                }
                }
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        // Sort newest reports first
        Transactions.sort(Comparator .comparing(Transaction::getDate, Comparator.reverseOrder()) .thenComparing(Transaction::getTime, Comparator.reverseOrder()) );
            return Transactions;


        }

        public void saveTransaction(Transaction transaction){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("Transaction.csv", true));

                writer.write(transaction.getDate() + "|" + transaction.getTime() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount());
                writer.newLine();

            writer.close();
        }catch(IOException e){
        System.out.println("Error Saving Transaction");
        e.printStackTrace();

        }
    }

}