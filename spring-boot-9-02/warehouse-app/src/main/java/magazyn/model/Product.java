package magazyn.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generuje: Gettery, Settery, toString, equals, hashCode
@NoArgsConstructor // Wymagany pusty konstruktor
@AllArgsConstructor // Konstruktor ze wszystkimi polami
public class Product {
    private int id;
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    private String skuCode;
    @NotNull
    private Producer brand;
    private String category;
}