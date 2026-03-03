package magazyn.repository;

import magazyn.model.Producer;
import java.util.List;

public interface ProducerDao {
    List<Producer> findAll();
    Producer findById(int id);
    Producer add(Producer p);
}