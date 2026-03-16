package magazyn.repository.mem;

// USUNIĘTO: import magazyn.model.Inventory;
import magazyn.repository.ProductDao;
import magazyn.model.Product;
import org.springframework.stereotype.Repository;
import magazyn.model.Producer;
import java.util.List;

@Repository("ProductDao")
public class MemProductDao implements ProductDao {
    @Override
    public List<Product> findAll() {
        return SampleData.products;
    }

    @Override
    public Product findById(int id) {
        return SampleData.products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product add(Product p) {
        int max = SampleData.products.stream()
                .mapToInt(Product::getId)
                .max()
                .orElse(0);
        p.setId(++max);
        SampleData.products.add(p);
        return p;
    }

    @Override
    public Product findBySkuCode(String skuCode) {
        return SampleData.products.stream()
                .filter(p -> p.getSkuCode().equals(skuCode))
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<Product> findByProducerId(int producerId) {
           return SampleData.products.stream()
                .filter(p -> p.getBrand() != null && p.getBrand().getId() == producerId)
                .toList();
    }

    // Pamiętaj o implementacji getAllProducers, jeśli też chcesz ją mieć w DAO
    public List<Producer> findAllProducers() {
        return SampleData.producers;
    }
}