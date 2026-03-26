package magazyn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*; // Importy dla JPA
import jakarta.validation.constraints.NotNull;
import lombok.*;
import magazyn.model.Product;
import java.util.ArrayList;
import java.util.List;

@Entity // Definiuje klasę jako tabelę w bazie danych [cite: 12]
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producer {

    @Id // Klucz główny [cite: 12]
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatyczna inkrymentacja ID [cite: 1, 12]
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String country;

    @OneToMany(mappedBy = "brand") // Relacja jeden-do-wielu z produktami [cite: 11, 13]
    @ToString.Exclude
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    // Konstruktor dla danych testowych
    public Producer(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public void addProduct(Product p) {
        this.products.add(p);
    }
}