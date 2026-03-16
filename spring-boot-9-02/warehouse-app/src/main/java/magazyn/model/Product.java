package magazyn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    private String skuCode;

    private Producer brand;
    private String category;

    @ToString.Exclude
    @JsonIgnore
    private List<Warehouse> warehouses = new ArrayList<>(); // Inicjalizacja zapobiega null


    public Product(int id, String name, String skuCode, Producer brand, String category) {
        this.id = id;
        this.name = name;
        this.skuCode = skuCode;
        this.brand = brand;
        this.category = category;
    }
}