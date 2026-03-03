package magazyn.service.impl;

import magazyn.repository.ProducerDao; // Zmieniono z DirectorDao
import magazyn.repository.WarehouseDao; // Zmieniono z CinemaDao
import magazyn.repository.ProductDao; // Zmieniono z MovieDao
import magazyn.model.Warehouse;
import magazyn.model.Producer;
import magazyn.model.Product;
import magazyn.service.ProductService;

import java.util.List;
import java.util.logging.Logger;

public class ProductServiceBean implements ProductService {

    private static final Logger log = Logger.getLogger(ProductService.class.getName());

    private ProducerDao producerDao;
    private WarehouseDao warehouseDao;
    private ProductDao productDao;

    public ProductServiceBean(ProducerDao producerDao, WarehouseDao warehouseDao, ProductDao productDao) {
        this.producerDao = producerDao;
        this.warehouseDao = warehouseDao;
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        log.info("searching all products...");
        return productDao.findAll();
    }

    public List<Product> getProductsByProducer(Producer p) {
        log.info("searching products by producer " + p.getId());
        return productDao.findByProducer(p);
    }

    public Product getProductById(int id) {
        log.info("searching product by id " + id);
        return productDao.findById(id);
    }

    public List<Producer> getAllProducers() {
        log.info("searching all producers");
        return producerDao.findAll();
    }

    public Producer getProducerById(int id) {
        log.info("searching producer by id " + id);
        return producerDao.findById(id);
    }

    @Override
    public Product addProduct(Product p) {
        log.info("about to add product " + p);
        return productDao.add(p);
    }

    @Override
    public Producer addProducer(Producer p) {
        log.info("about to add producer " + p);
        return producerDao.add(p);
    }
}