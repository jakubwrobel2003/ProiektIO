package magazyn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    private Product product;    // Referencja do obiektu produktu
    private Warehouse warehouse;// Referencja do obiektu magazynu
    private int quantity;         // Ilość konkretnego produktu w tym magazynie
    private LocalDateTime lastDelivery; // Data ostatniej dostawy/aktualizacji

    public Inventory(Product product, Warehouse warehouse, int quantity) {
        this.product = product;
        this.warehouse = warehouse;
        this.quantity = quantity;
        this.lastDelivery = LocalDateTime.now();
    }


}