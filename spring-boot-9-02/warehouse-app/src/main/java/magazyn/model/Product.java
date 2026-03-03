package magazyn.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int id;
    private String name;      // odpowiednik title [cite: 103]
    private String skuCode;   // odpowiednik poster [cite: 103]
    private Producer producer; // odpowiednik director [cite: 103]
    private int quantity;     // odpowiednik rating (zmieniono na int dla towarów) [cite: 103]
    private List<Warehouse> warehouses = new ArrayList<>(); // odpowiednik cinemas [cite: 103]

    // Relacja wiele do wielu - bidirectional 

    public Product(int id, String name, String skuCode, Producer producer, int quantity) {
        this.id = id;
        this.name = name;
        this.skuCode = skuCode;
        this.producer = producer;
        this.quantity = quantity;
    }

    public Product() { // bezparametrowy wymagany dla kontenera [cite: 103]
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public void addWarehouse(Warehouse w) {
        this.warehouses.add(w);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", producer=" + producer +
                ", quantity=" + quantity +
                ", skuCode='" + skuCode + '\'' +
                '}';
    }
}