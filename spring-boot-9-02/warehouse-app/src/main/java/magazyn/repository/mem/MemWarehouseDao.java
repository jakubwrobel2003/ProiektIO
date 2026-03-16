package magazyn.repository.mem;

// USUNIĘTO: import magazyn.model.Inventory;
import magazyn.repository.WarehouseDao;
import magazyn.model.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("WarehouseDao")
public class MemWarehouseDao implements WarehouseDao {
    @Override
    public List<Warehouse> findAll() { return SampleData.warehouses; }

    @Override
    public Warehouse findById(int id) {
        return SampleData.warehouses.stream()
                .filter(w -> w.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public Warehouse save(Warehouse w) {
        if (w.getId() == 0) {
            int maxId = SampleData.warehouses.stream().mapToInt(Warehouse::getId).max().orElse(0);
            w.setId(maxId + 1);
            SampleData.warehouses.add(w);
        } else {
            SampleData.warehouses.removeIf(existing -> existing.getId() == w.getId());
            SampleData.warehouses.add(w);
        }
        return w;
    }
}