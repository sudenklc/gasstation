import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Pump {

    public String name;
    public String type;
    public float capacity;
    public String mode;
    public float tankLevel;


    public Pump(String name, String type, float capacity, String mode, float tankLevel) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.mode = mode;
        this.tankLevel = tankLevel;
    }


    public Pump() {}


    public static List<Pump> loadPumpsFromFile(String fileName) {
        List<Pump> pumps = new ArrayList<>();
        try (Scanner reader = new Scanner(Paths.get(fileName))) {

            if (reader.hasNextLine()) {
                reader.nextLine();
            }
            while (reader.hasNextLine()) {
                String[] info = reader.nextLine().split(",");
                if (info.length == 5) {
                    String name = info[0];
                    String type = info[1];
                    float capacity = Float.parseFloat(info[2]);
                    String mode = info[3];
                    float tankLevel = Float.parseFloat(info[4]);

                    Pump pump = new Pump(name, type, capacity, mode, tankLevel);
                    pumps.add(pump);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return pumps;
    }


    public static void saveConfiguration(List<Pump> pumps) {
        try (FileWriter writer = new FileWriter("Pump.txt")) {
            writer.write("Name,Type,Capacity,Mode,TankLevel\n");
            for (Pump p : pumps) {
                if (p != null) {
                    writer.write(p.name + "," + p.type + "," + p.capacity + "," + p.mode + "," + p.tankLevel + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error saving pump configuration to file", e);
        }
    }


    public void updatePumpConfiguration(List<Pump> pumps) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < pumps.size(); i++) {
            System.out.println("Configuring pump " + (i + 1) + ":");
            System.out.println("Enter pump name: ");
            String name = sc.nextLine();
            System.out.println("Enter pump type: ");
            String type = sc.nextLine();
            System.out.println("Enter pump capacity: ");
            float capacity = Float.parseFloat(sc.nextLine());
            System.out.println("Enter pump mode: ");
            String mode = sc.nextLine();
            System.out.println("Enter pump tank level: ");
            float tankLevel = Float.parseFloat(sc.nextLine());

            pumps.set(i, new Pump(name, type, capacity, mode, tankLevel));
            System.out.println("Pump " + (i + 1) + " configuration updated.");
        }
    }
}
