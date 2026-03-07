package magazyn.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.model.Warehouse;
import magazyn.model.Product;
import magazyn.model.Inventory;
import magazyn.repository.WarehouseDao;
import magazyn.repository.ProductDao;
import magazyn.repository.InventoryDao; // Nowe wstrzyknięcie
import magazyn.service.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WarehouseServiceBean implements WarehouseService {

    private final WarehouseDao warehouseDao;
    private final InventoryDao inventoryDao;

    @Override
    public Warehouse getWarehouseById(int id) {
        log.info("Searching warehouse by id: {}", id);
        return warehouseDao.findById(id);
    }


    @Override
    public Warehouse addWarehouse(Warehouse w) {
        log.info("Saving new warehouse to system: {}", w.getName());
        return warehouseDao.save(w);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        log.info("Fetching all warehouses");
        return warehouseDao.findAll();
    }


}