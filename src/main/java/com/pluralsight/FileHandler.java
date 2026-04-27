package com.pluralsight;
import java.util.ArrayList;
import java.io.*;


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
                    String date = parts[0].trim();
                    String time = parts[1].trim();
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());
                    Transactions.add(new Transaction(date, time, description, vendor, amount));
                }
                }
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            return Transactions;


        }
    }

