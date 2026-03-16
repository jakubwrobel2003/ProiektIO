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
public class Warehouse {
    private int id;
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    private String location;

    @ToString.Exclude
    @JsonIgnore
    private List<Product> products = new ArrayList<>(); // Inicjalizacja zapobiega null

    // Konstruktor dla SampleData
    public Warehouse(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}