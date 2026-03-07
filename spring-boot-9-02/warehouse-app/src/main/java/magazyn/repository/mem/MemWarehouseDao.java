package magazyn.repository.mem;

import magazyn.model.Inventory;
import magazyn.repository.WarehouseDao;
import magazyn.model.Warehouse;
import magazyn.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
@Repository("WarehouseDao")
public class MemWarehouseDao implements WarehouseDao {

    @Override
    public List<Warehouse> findAll() {
        return SampleData.warehouses; // [cite: 162]
    }

    @Override
    public Warehouse findById(int id) {
        return SampleData.warehouses.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null); // [cite: 164-165]
    }

    @Override
    public Warehouse save(Warehouse w) {
        if (w.getId() == 0) {
            int max = SampleData.warehouses.stream()
                    .mapToInt(Warehouse::getId)
                    .max()
                    .orElse(0); // [cite: 181]
            w.setId(++max);
            SampleData.warehouses.add(w);
        } else {
            SampleData.warehouses.removeIf(wh -> wh.getId() == w.getId());
            SampleData.warehouses.add(w);
        }
        return w;
    }
}