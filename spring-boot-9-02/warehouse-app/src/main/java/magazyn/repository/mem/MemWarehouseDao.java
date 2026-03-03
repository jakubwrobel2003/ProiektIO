package magazyn.repository.mem;

import magazyn.repository.WarehouseDao;
import magazyn.model.Warehouse;
import magazyn.model.Product;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Warehouse> findByProduct(Product p) {
        return SampleData.warehouses.stream()
                .filter(w -> w.getProducts().contains(p))
                .collect(Collectors.toList()); // [cite: 175]
    }
}