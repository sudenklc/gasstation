package Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Fuel.*;

public class Transaction {
    public String date;
    public Fuel fuel;
    public float amount;

    public List<Transaction> transactions = new ArrayList<>();

    public Transaction(String date, Fuel fuel, float amount) {
        this.date = date;
        this.fuel = fuel;
        this.amount = amount;
    }

    public Transaction() {

    }


    public static void saveTransactionsToFile(String file, List<Transaction> transactions) {
        String fileName = file;
        try (FileWriter writer = new FileWriter(fileName, true)) {
            for (Transaction t : transactions) {
                writer.write(t.date + "," + t.fuel.getType() + "," +
                        t.amount + "," + t.fuel.getLitrePrice() + "\n");
            }

        } catch (IOException ex) {
            throw new RuntimeException("error saving the transactions to file");
        }

    }

    {

    }

    public void readTransactionsFromFile(String fileName) {
        Scanner reader = null;
        try {
            reader = new Scanner(Paths.get(fileName));




            while (reader.hasNextLine()) {
                String[] info = reader.nextLine().split(",");

                Fuel fuel = new Fuel();
                fuel.setType(info[1]);
                fuel.setLitrePrice(Float.parseFloat(info[2]));

                Transaction t = new Transaction(info[0], fuel, Float.parseFloat(info[2]));
                t.date = info[0];
                t.fuel.setType(info[1]);
                t.amount = Float.parseFloat(info[2]);
                t.fuel.setLitrePrice(Float.parseFloat(info[3]));
                transactions.add(t);

            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
        public static void updateTransactionsToFile(String fileName, List<Transaction> transactions){

            try {
                FileWriter writer = new FileWriter(fileName);
                for (Transaction t : transactions) {
                    writer.write(t.date + "," + t.fuel + "," +
                            t.amount + "," + "\n");
                }

            } catch (IOException e) {
                System.out.println("Error updating transactions: " + e.getMessage());
            }

            for (int j = 0; j < transactions.size() && transactions.get(j) != null; j++) {
                Transaction t = transactions.get(j);
                System.out.println("Date: " + t.date + ", Type: " + t.fuel.getType() + ", Amount: " + t.amount + ", Litre Price: " + t.fuel.getLitrePrice());
            }
        }

    }

