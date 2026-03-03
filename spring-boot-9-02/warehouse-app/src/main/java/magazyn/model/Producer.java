package magazyn.model; // Zmieniono z vod.model [cite: 99]

import java.util.ArrayList;
import java.util.List;

public class Producer {

    private int id;
    private String name;
    private String country;
    private List<Product> products = new ArrayList<>(); // Relacja 1 do wielu [cite: 177]

    public Producer(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Producer() {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    public void addProduct(Product p) {
        this.products.add(p);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}