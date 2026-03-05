package magazyn.model; // Zmieniono z vod

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private int id;
    private String name;
    private String location;
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Warehouse(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Warehouse() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Poprawione z getLogo/setLogo na getLocation/setLocation
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<Product> getProducts() { return products; }

    // Poprawiona nazwa metody i parametru (było addProducte(Product m))
    public void addProduct(Product p) {
        this.products.add(p);
    }

    @Override
    public String toString() {
        return "Warehouse{" + // Było Cinema
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}