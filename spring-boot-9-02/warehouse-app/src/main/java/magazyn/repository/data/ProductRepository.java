package magazyn.repository.data;

import magazyn.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findBySkuCode(String skuCode);
    List<Product> findAllByBrandId(int brandId);
}