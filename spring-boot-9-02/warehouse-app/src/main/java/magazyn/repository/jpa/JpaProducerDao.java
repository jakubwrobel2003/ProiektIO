package magazyn.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import magazyn.model.Producer;
import magazyn.repository.ProducerDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
//@Primary
@Transactional
public class JpaProducerDao implements ProducerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Producer> findAll() {
        return entityManager.createQuery("select pr from Producer pr", Producer.class)
                .getResultList();
    }

    @Override
    public Producer findById(int id) {
        return entityManager.find(Producer.class, id);
    }

    @Override
    public Producer add(Producer p) {
        entityManager.persist(p);
        return p;
    }
}