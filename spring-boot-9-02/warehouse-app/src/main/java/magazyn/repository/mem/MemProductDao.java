package magazyn.repository.mem;

import magazyn.repository.ProductDao;
import magazyn.model.Warehouse;
import magazyn.model.Producer;
import magazyn.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class MemProductDao implements ProductDao {
    @Override
    public List<Product> findAll() {
        return SampleData.products; // [cite: 162]
    }

    @Override
    public Product findById(int id) {
        return SampleData.products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null); // [cite: 164-165, 173]
    }

    @Override
    public List<Product> findByProducer(Producer p) {
        return SampleData.products.stream()
                .filter(prod -> prod.getProducer().equals(p))
                .collect(Collectors.toList()); // [cite: 174-175]
    }

    @Override
    public List<Product> findByWarehouse(Warehouse w) {
        return SampleData.products.stream()
                .filter(p -> p.getWarehouses().contains(w))
                .collect(Collectors.toList()); // [cite: 175]
    }

    @Override
    public Product add(Product p) {
        int max = SampleData.products.stream()
                .mapToInt(Product::getId)
                .max()
                .orElse(0); // [cite: 181]
        p.setId(++max);
        SampleData.products.add(p);
        return p;
    }
}