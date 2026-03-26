package magazyn;



import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j; // Opcjonalne: do logowania [cite: 273]
import magazyn.model.Warehouse;
import magazyn.repository.ProducerDao;
import magazyn.repository.ProductDao;
import magazyn.repository.WarehouseDao;
import magazyn.service.WarehouseService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component // 1. Oznacz klasę jako komponent zarządzany przez kontener [cite: 757]
@Slf4j      // 2. Dodaj wsparcie dla logów (adnotacja z Lombok) [cite: 273]
public class WarehouseComponent {

    // 3. Zadeklaruj pole jako finalne, aby ustanowić powiązanie na stałe [cite: 672, 702]
    private final WarehouseService warehouseService;

    // 4. Wstrzyknij usługę przez konstruktor (zalecana strategia) [cite: 571, 692]
    public WarehouseComponent(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    // 5. Użyj @PostConstruct dla logiki wykonującej się po zainicjalizowaniu obiektu [cite: 703, 706]
    @PostConstruct
    public void init() {
        log.info("Komponent magazynu został zainicjalizowany."); // [cite: 295, 762]

        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        log.info("{} magazynów znaleziono:", warehouses.size()); // [cite: 575, 769]

        warehouses.forEach(w -> log.info("Magazyn: {}", w)); // [cite: 576, 806]
    }
}
