package magazyn.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import magazyn.model.Product;
import magazyn.repository.ProductDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
//@Primary // Oznacza tę implementację jako podstawową
@Transactional // Wymagane do operacji zapisu/modyfikacji
public class JpaProductDao implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager; // Obudowanie JPA EntityManagerem

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    @Override
    public Product findById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product add(Product p) {
        entityManager.persist(p); // Utrwalanie obiektu w bazie
        return p;
    }

    @Override
    public Product findBySkuCode(String skuCode) {
        return entityManager.createQuery("select p from Product p where p.skuCode = :sku", Product.class)
                .setParameter("sku", skuCode)
                .getSingleResult();
    }

    @Override
    public List<Product> findByProducerId(int producerId) {
        return entityManager.createQuery("select p from Product p where p.brand.id = :prodId", Product.class)
                .setParameter("prodId", producerId)
                .getResultList();
    }
}