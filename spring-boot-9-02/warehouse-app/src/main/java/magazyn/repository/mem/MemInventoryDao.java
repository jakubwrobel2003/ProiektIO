package magazyn.repository.mem;

import lombok.extern.slf4j.Slf4j;
import magazyn.model.Inventory;
import magazyn.repository.InventoryDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class MemInventoryDao implements InventoryDao {

    @Override
    public List<Inventory> findAll() {
        return SampleData.inventories;
    }

    @Override
    public List<Inventory> findByWarehouseId(int warehouseId) {
        return SampleData.inventories.stream()
                .filter(i -> i.getWarehouse().getId() == warehouseId)
                .toList();
    }

    @Override
    public List<Inventory> findByProductId(int productId) {
        return SampleData.inventories.stream()
                .filter(i -> i.getProduct().getId() == productId)
                .toList();
    }
    @Override
    public int getTotalQuantity(int productId) {
        return SampleData.inventories.stream()
                .filter(i -> i.getProduct().getId() == productId)
                .mapToInt(Inventory::getQuantity)
                .sum();
    }
}