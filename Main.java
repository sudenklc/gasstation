
import Fuel.Fuel;
import Report.*;
import Transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {


    static List<Pump> pumps = new ArrayList<>();

    public static void main(String[] args) {


        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Pump pump = new Pump();
        Fuel fuel;
        new Fuel();
        Report report;
        report = new Report();
        Transaction transaction = new Transaction();

        System.out.printf("Hello and welcome! Who is using this system(manager,cashier,maintenance)?");
        Scanner sc = new Scanner(System.in);
        String user = sc.nextLine();

        if (user.equalsIgnoreCase("manager")) {
            System.out.println("Which operation do you want to perform? Enter a number for operations ");
            System.out.println("1.System configuration");
            System.out.println("2.inventory management ");
            System.out.println("3.reporting and analytics");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:

                    systemConfiguration(pump);
                    break;

                case 2:
                    inventoryManagement(pump);
                    break;

                case 3:
                    reportingAndAnalytics(transaction);
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;

            }
        } else if (user.equalsIgnoreCase("cashier")) {
            System.out.println("Which operation do you want to perform? Enter a number for operations");
            System.out.println("1.Fuel sales");
            System.out.println("2.Inventory tracking");
            System.out.println("3.Pump status monitoring");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    fuelSales(sc);
                    break;
                case 2:
                    inventoryTracking(pump);
                    break;
                case 3:
                    pumpStatusMonitoring(pump.name);
                    break;
            }

        } else if (user.equalsIgnoreCase("maintenance")) {
            System.out.println("Which operation dou you want to perform? Enter a number for operations");
            System.out.println("1.Pump maintenance");
            System.out.println("2.Breakdown management");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    pumpMaintenance(pump.name);
                    break;
                case 2:
                    breakdownManagement(pump.name);
                    break;
            }
        } else {
            System.out.println("Invalid input. Please enter manager, cashier, or maintenance.");
        }


    }

    private static void breakdownManagement(String name) {
        for (Pump pump : pumps) {
            if (pump.name.equals(name)) {
                Random random = new Random();
                float breakdownChance = 0.20f;
                if (random.nextFloat() <= breakdownChance) {
                    pump.mode = "out of order";
                    System.out.println(name + " has broken down and is now out of order.");
                }
            }
        }
    }

    private static boolean pumpMaintenance(String name) {
        for (Pump pump : pumps) {
            if (pump.name.equals(name)) {
                pump.mode = "out of order";
                System.out.println(name + " has broken up and is under maintenance.");
                return false;
            }
        }
        return true;
    }



    private static boolean pumpStatusMonitoring(String name) {
        for (Pump pump : pumps) {
            if (pump.name.equals(name)) {
                if (pump.mode.equals("out of order")) {
                    System.out.println("The pump is out of order");
                    return false;
                }
            }
        }

        return true;
    }

    private static void inventoryTracking(Pump pump) {
        pumps = Pump.loadPumpsFromFile("Pump.txt");
        System.out.println("Loaded pumps:");
        for (Pump p : pumps) {
            System.out.println("Name: " + pump.name);
            System.out.println("Type: " + pump.type);
            System.out.println("Capacity: " + pump.capacity);
            System.out.println("Mode: " + pump.mode);
            System.out.println("Tank level: " + pump.tankLevel);
            System.out.println("-------------------");
        }
    System.out.println(pump.tankLevel);
    }

    private static void fuelSales(Scanner sc) {

        System.out.println("Enter the date for transactions: ");
        String date = sc.nextLine();
        System.out.println("Enter the name of the pump 1-6");
        String pumpName = sc.nextLine();
        while (!pumpStatusMonitoring(pumpName)) {
            System.out.println("Enter the name of the pump 1-6");
            pumpName = sc.nextLine();
        }
        System.out.println("Enter the fuel type of the sale: ");
        String fuelType = sc.nextLine();
        System.out.println("Enter the amount of fuel: ");
        int amount = sc.nextInt();
        System.out.println("Enter the fuel price: ");
        double price = sc.nextDouble();
        System.out.println("Transactions:");
        breakdownManagement(pumpName);

        List<Transaction> transactions = new ArrayList<>();
        Fuel fuel = new Fuel(fuelType, price);
        Transaction transaction = new Transaction(date, fuel, amount);
        transactions.add(transaction);
        for (Transaction t : transactions) {
            if (t != null) {
                System.out.println(t);
               Transaction.saveTransactionsToFile("Transaction.txt", transactions);
               Transaction.updateTransactionsToFile("Transaction.txt", transactions);


            }
        }
    }
    private static void reportingAndAnalytics(Transaction transaction) {
    System.out.println(transaction.transactions);
    }

    private static void inventoryManagement(Pump pump) {
       System.out.println("pump name: " + pump.name);
        System.out.println("tank level: " + pump.tankLevel);
        System.out.println("----------------");
    }

    private static void systemConfiguration(Pump pump) {
        System.out.println("System configuration");
        System.out.println("1. Update pump configuration");
        System.out.println("2. Save pump configuration to file");
        System.out.println("3. Exit");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                pump.updatePumpConfiguration(pumps);
                break;
            case 2:
                pump.saveConfiguration(pumps);
                break;
            case 3:
                System.out.println("exit system configuration");
                return;
            default:
                System.out.println("Invalid choice");
                break;
        } systemConfiguration(pump);


    }
}
