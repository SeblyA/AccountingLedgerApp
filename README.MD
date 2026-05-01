# Accounting Ledger Application

## 📌 Overview

The Accounting Ledger Application is a Java-based console program that allows users to track financial transactions such as deposits and payments. The application stores transactions in a CSV file and allows users to view and filter financial records through a simple menu system.

## 🚀 Features

### 💰 Transactions

#### Users can record:

* Deposits (money coming in)

* Payments (money going out)


Each transaction includes:

 Date | Time | Description | Vendor | Amount

## 📖 Ledger View

Users can view all recorded transactions in a structured format.

## 📊 Reports Menu

The application provides multiple report options:


1) Month-To-Date

2) Previous Month

3) Year-To-Date

4) Previous Year

5) Search by Vendor

6) All Transactions

These reports help users analyze their financial activity over time.

💾 File Storage
Transactions are saved to a CSV file 

Data persists even after the program is closed
Users can reload and search past transactions

### 🖥️ How It Works

The program starts and loads existing transactions from the file
The user is prompted with a menu:

Add Deposit

Make Payment

View Ledger

Run Reports

User inputs are processed using Scanner
New transactions are:
Stored in memory
Saved to the CSV file

## ▶️ How to Run the Application

A Java IDE (IntelliJ, Eclipse) or terminal

### 🔧 Steps to Run

Clone or download the project
Open the project in your IDE
Locate the Main.java file
Run the program ▶️ using run button

### 📁 File Format

Transactions are stored in a CSV file using this format:

date|time|description|vendor|amount

Example:
2026-04-29|14:23:45|Deposit|Bank|2000.00