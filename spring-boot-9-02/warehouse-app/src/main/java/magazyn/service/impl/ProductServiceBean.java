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
    // USUNIĘTO: inventoryDao

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productDao.findById(id);
    }

    @Override
    public Product addProduct(Product p, int warehouseId) {
        log.info("Adding product: {} to warehouse ID: {}", p.getName(), warehouseId);

        Product savedProduct = productDao.add(p);
        Warehouse warehouse = warehouseDao.findById(warehouseId);

        if (warehouse != null) {
            // Logika bezpośredniego powiązania list
            savedProduct.getWarehouses().add(warehouse);
            warehouse.getProducts().add(savedProduct);
            log.info("Product-Warehouse link established");
        }
        return savedProduct;
    }

    @Override
    public int getTotalQuantity(int productId) {
        log.info("Calculating how many warehouses have product ID: {}", productId);
        Product p = productDao.findById(productId);
        // Zwracamy rozmiar listy magazynów, w których jest produkt
        return (p != null) ? p.getWarehouses().size() : 0;
    }
    @Override
    public List<Product> getProductsByProducer(int producerId) {
        // Serwis tylko deleguje zadanie do DAO
        return productDao.findByProducerId(producerId);
    }
    @Override
    public Product addProduct(Product p) {
        return productDao.add(p);
    }

    // Pozostałe metody (Producenci, SKU) pozostają bez zmian
    @Override
    public List<Producer> getAllProducers() { return producerDao.findAll(); }
    @Override
    public Producer getProducerById(int id) { return producerDao.findById(id); }
    @Override
    public Producer addProducer(Producer p) { return producerDao.add(p); }
    @Override
    public Product getProductbySkuCode(String skuCode) { return productDao.findBySkuCode(skuCode); }


}