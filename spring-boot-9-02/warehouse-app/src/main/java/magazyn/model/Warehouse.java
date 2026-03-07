package magazyn.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // Dodajmy Lombok, żeby pozbyć się ręcznych getterów i setterów
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {
    private int id;
    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    private String location;

    // LISTA PRODUCTS USUNIĘTA
}