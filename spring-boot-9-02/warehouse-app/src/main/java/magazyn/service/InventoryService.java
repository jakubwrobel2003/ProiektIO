package magazyn.service;

import magazyn.model.Inventory;
import java.util.List;

public interface InventoryService {
    List<Inventory> getInventoryForWarehouse(int warehouseId);
    List<Inventory> getInventoryForProduct(int productId);
}