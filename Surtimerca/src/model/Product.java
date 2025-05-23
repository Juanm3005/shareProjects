package model;

public class Product {

    private String id;
    private String name;
    private double price;
    private int unitsAvailable;

    public String formatToFile() {
        return id + " - " + name + " - " + price + " - " + unitsAvailable;
    }

    public Product(String name, double price, int unitsAvailable) {
        generateId();
        this.name = name;
        this.price = price;
        this.unitsAvailable = unitsAvailable;
    }

    public Product(String id, String name, double price, int unitsAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unitsAvailable = unitsAvailable;
    }

    private void generateId() {
        id = this.hashCode() + "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getUnitsAvailable() {
        return unitsAvailable;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", unitsAvailable=" + unitsAvailable + "]";
    }

}
