package magazyn.repository.data;

import lombok.RequiredArgsConstructor;
import magazyn.model.Producer;
import magazyn.repository.ProducerDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataProducerDao implements ProducerDao {
    private final ProducerRepository repository;

    @Override
    public List<Producer> findAll() { return repository.findAll(); }

    @Override
    public Producer findById(int id) { return repository.findById(id).orElse(null); }

    @Override
    public Producer add(Producer p) { return repository.save(p); }
}