package Report;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Report {
    public String date;
    public String type;
    public float totalSales;
    public float totalCosts;

    public void run() {
        Scanner reader = null;
        String[] fields = { "Date: ", "Type: ", "Total Sales: ", "Total Costs: " };
        Report[] reports = new Report[100];
        try {
            reader = new Scanner(Paths.get("Report.txt"));
            int i = 0;
            while (reader.hasNextLine() && i < reports.length) {
                String[] info = reader.nextLine().split(",");
                Report r = new Report();
                r.date = info[0];
                r.type = info[1];
                r.totalSales = Float.parseFloat(info[2]);
                r.totalCosts = Float.parseFloat(info[3]);
                reports[i] = r;
                i++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }


        for (int j = 0; j < reports.length && reports[j] != null; j++) {
            Report r = reports[j];
            System.out.println("Date: " + r.date + ", Type: " + r.type +
                    ", Total Sales: " + r.totalSales +
                    ", Total Costs: " + r.totalCosts);
        }
    }
}
