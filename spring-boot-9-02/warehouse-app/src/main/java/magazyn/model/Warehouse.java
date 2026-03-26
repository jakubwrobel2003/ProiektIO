package magazyn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*; // Importy dla JPA
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Mapowanie na tabelę 'warehouse' [cite: 12]
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    private String location;

    @ManyToMany // Relacja wiele-do-wielu
    @JoinTable(
            name = "product_warehouse", // Nazwa tabeli pośredniczącej [cite: 14]
            joinColumns = @JoinColumn(name = "warehouse_id"), // Klucz do magazynu [cite: 14]
            inverseJoinColumns = @JoinColumn(name = "product_id") // Klucz do produktu [cite: 14]
    )
    @ToString.Exclude
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Warehouse(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}