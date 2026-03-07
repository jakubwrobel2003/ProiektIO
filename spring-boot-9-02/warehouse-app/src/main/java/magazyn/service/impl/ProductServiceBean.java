package magazyn.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.model.*;
import magazyn.repository.*;
import magazyn.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceBean implements ProductService {

    private final ProductDao productDao;
    private final ProducerDao producerDao;
    private final WarehouseDao warehouseDao;
    private final InventoryDao inventoryDao; // Dodajemy to wstrzyknięcie

    @Override
    public List<Product> getAllProducts() {
        log.info("Fetching all products from catalog");
        return productDao.findAll();
    }

    @Override
    public Product getProductById(int id) {
        log.info("Searching for product with ID: {}", id);
        return productDao.findById(id);
    }

    @Override
    public Product addProduct(Product p, int warehouseId, int amount) {
        log.info("Adding new product: {} to warehouse ID: {} with amount: {}", p.getName(), warehouseId, amount);

        Product savedProduct = productDao.add(p);

        Warehouse warehouse = warehouseDao.findById(warehouseId);

        if (warehouse != null && amount > 0) {
            Inventory newRecord = new Inventory(savedProduct, warehouse, amount);
            magazyn.repository.mem.SampleData.inventories.add(newRecord);
            log.info("Inventory successfully initialized for: {}", savedProduct.getName());
        } else {
            log.warn("Product added to catalog, but inventory not initialized (Warehouse not found or amount <= 0)");
        }

        return savedProduct;
    }

    @Override
    public List<Producer> getAllProducers() {
        log.info("Fetching all producers");
        return producerDao.findAll();
    }

    @Override
    public Producer getProducerById(int id) {
        log.info("Searching for producer with ID: {}", id);
        return producerDao.findById(id);
    }

    @Override
    public Producer addProducer(Producer p) {
        log.info("Adding new producer: {}", p.getName());
        return producerDao.add(p);
    }


    @Override
    public int getTotalQuantity(int productId) {
        log.info("Calculating total quantity for product ID: {}", productId);
        List<Inventory> productStocks = inventoryDao.findByProductId(productId);
        return inventoryDao.getTotalQuantity(productId);
    }
}