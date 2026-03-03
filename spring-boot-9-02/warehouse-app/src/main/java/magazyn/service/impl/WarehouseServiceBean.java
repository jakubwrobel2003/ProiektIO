package magazyn.service.impl;

import magazyn.model.Warehouse;
import magazyn.model.Product;
import magazyn.repository.WarehouseDao;
import magazyn.repository.ProductDao;
import magazyn.service.WarehouseService;

import java.util.List;
import java.util.logging.Logger;

public class WarehouseServiceBean implements WarehouseService {

    private static final Logger log = Logger.getLogger(WarehouseService.class.getName());

    private WarehouseDao warehouseDao;
    private ProductDao productDao;

    public WarehouseServiceBean(WarehouseDao warehouseDao, ProductDao productDao) {
        log.info("creating warehouse service bean");
        this.warehouseDao = warehouseDao;
        this.productDao = productDao;
    }

    @Override
    public Warehouse getWarehouseById(int id) {
        log.info("searching warehouse by id " + id);
        return warehouseDao.findById(id);
    }

    @Override
    public List<Product> getProductsInWarehouse(Warehouse w) {
        log.info("searching products stored in warehouse " + w.getId());
        return productDao.findByWarehouse(w);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        log.info("searching all warehouses");
        return warehouseDao.findAll();
    }

    @Override
    public List<Warehouse> getWarehousesByProduct(Product p) {
        log.info("searching warehouses for product " + p.getId());
        return warehouseDao.findByProduct(p);
    }
}