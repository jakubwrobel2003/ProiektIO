package magazyn.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import magazyn.model.Warehouse;
import magazyn.repository.WarehouseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
//@Primary
@Transactional
public class JpaWarehouseDao implements WarehouseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Warehouse> findAll() {
        return entityManager.createQuery("select w from Warehouse w", Warehouse.class)
                .getResultList();
    }

    @Override
    public Warehouse findById(int id) {
        return entityManager.find(Warehouse.class, id);
    }

    @Override
    public Warehouse save(Warehouse w) {
        if (entityManager.find(Warehouse.class, w.getId()) == null) {
            entityManager.persist(w);
            return w;
        } else {
            return entityManager.merge(w); // Aktualizacja istniejącego magazynu
        }
    }
}