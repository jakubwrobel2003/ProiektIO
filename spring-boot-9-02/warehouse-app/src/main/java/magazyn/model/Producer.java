package magazyn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producer {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String country;

    @ToString.Exclude // Bardzo ważne: zapobiega pętli w metodzie toString()
    @JsonIgnore       // Zapobiega pętli przy generowaniu JSON w API
    private List<Product> products = new ArrayList<>();

    // Konstruktor bez listy dla wygody w SampleData
    public Producer(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public void addProduct(Product p) {
        this.products.add(p);
    }
}