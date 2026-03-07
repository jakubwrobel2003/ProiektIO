package magazyn.repository;

import magazyn.model.Inventory;
import java.util.List;

public interface InventoryDao {
    List<Inventory> findAll();
    List<Inventory> findByWarehouseId(int warehouseId);
    List<Inventory> findByProductId(int productId);

    int getTotalQuantity(int productId);
}