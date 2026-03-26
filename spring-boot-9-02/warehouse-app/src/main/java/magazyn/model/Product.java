package magazyn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*; // Ważne: importy dla JPA [cite: 12432]
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Oznacza klasę jako encję bazodanową [cite: 12406, 12432]
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id // Określa klucz główny [cite: 12409, 12434]
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatyczne generowanie ID przez bazę [cite: 12409, 12434]
    private int id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Column(name = "sku_code") // Mapuje pole na konkretną nazwę kolumny w MySQL [cite: 12437]
    private String skuCode;

    @ManyToOne // Relacja: wiele produktów do jednego producenta [cite: 12521, 12523]
    @JoinColumn(name = "producer_id") // Nazwa kolumny klucza obcego w tabeli product
    private Producer brand;

    private String category;

    @ManyToMany(mappedBy = "products") // Relacja wiele-do-wielu, zmapowana w klasie Warehouse [cite: 12525, 12527]
    @ToString.Exclude
    @JsonIgnore
    private List<Warehouse> warehouses = new ArrayList<>();

    // Konstruktor używany w Twoich danych testowych (SampleData)
    public Product(int id, String name, String skuCode, Producer brand, String category) {
        this.id = id;
        this.name = name;
        this.skuCode = skuCode;
        this.brand = brand;
        this.category = category;
    }
}