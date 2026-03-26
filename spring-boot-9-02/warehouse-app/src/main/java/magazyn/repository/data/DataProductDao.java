package magazyn.repository.data;

import lombok.RequiredArgsConstructor;
import magazyn.model.Product;
import magazyn.repository.ProductDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataProductDao implements ProductDao {
    private final ProductRepository repository;

    @Override
    public List<Product> findAll() { return repository.findAll(); }

    @Override
    public Product findById(int id) { return repository.findById(id).orElse(null); }
    @Override
    @Transactional(propagation = Propagation.MANDATORY) // Wymaga transakcji z serwisu [cite: 679, 686]
    public Product add(Product p) {
        return repository.save(p);
    }

    @Override
    public Product findBySkuCode(String skuCode) { return repository.findBySkuCode(skuCode); }

    @Override
    public List<Product> findByProducerId(int producerId) { return repository.findAllByBrandId(producerId); }
}