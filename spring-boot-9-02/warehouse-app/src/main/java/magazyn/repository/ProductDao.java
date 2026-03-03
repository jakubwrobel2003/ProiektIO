package magazyn.repository;

import magazyn.model.Warehouse;
import magazyn.model.Producer;
import magazyn.model.Product;
import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findById(int id);
    List<Product> findByProducer(Producer p);
    List<Product> findByWarehouse(Warehouse w);
    Product add(Product p);
}