package magazyn.repository;

// USUNIĘTO: import io.micrometer.observation.ObservationFilter;
// USUNIĘTO: import magazyn.model.Warehouse;
// USUNIĘTO: import magazyn.model.Producer;
import magazyn.model.Product;
import java.util.List;

public interface ProductDao {
    List<Product> findAll();
    Product findById(int id);
    Product add(Product p);
    Product findBySkuCode(String skuCode);
    List<Product> findByProducerId(int producerId);
}