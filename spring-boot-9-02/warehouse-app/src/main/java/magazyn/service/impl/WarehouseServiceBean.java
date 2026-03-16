package magazyn.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.model.Warehouse;
import magazyn.repository.WarehouseDao;
import magazyn.service.WarehouseService;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WarehouseServiceBean implements WarehouseService {

    private final WarehouseDao warehouseDao;
    // USUNIĘTO: inventoryDao

    @Override
    public Warehouse getWarehouseById(int id) {
        return warehouseDao.findById(id);
    }

    @Override
    public Warehouse addWarehouse(Warehouse w) {
        return warehouseDao.save(w);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseDao.findAll();
    }
}