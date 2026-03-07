package magazyn.service.impl;

import lombok.RequiredArgsConstructor;
import magazyn.model.Inventory;
import magazyn.repository.InventoryDao;
import magazyn.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceBean implements InventoryService {

    private final InventoryDao inventoryDao;

    @Override
    public List<Inventory> getInventoryForWarehouse(int warehouseId) {
        return inventoryDao.findByWarehouseId(warehouseId);
    }

    @Override
    public List<Inventory> getInventoryForProduct(int productId) {
        return inventoryDao.findByProductId(productId);
    }
}