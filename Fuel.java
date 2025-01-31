package Fuel;

public class Fuel {
    private String type;
    private double litrePrice;

    public Fuel(String type, double litrePrice) {
        this.type = type;
        this.litrePrice = litrePrice;
    }

    public Fuel() {

    }

    public String getType() {
        return type;
    }

    public double getLitrePrice() {
        return litrePrice;
    }

    public void setLitrePrice( double litrePrice) {
        this.litrePrice = litrePrice;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void fuelInfo() {
        System.out.println("Fuel type: " + type + " - Price per litre: " + litrePrice + "euro");
    }
}

