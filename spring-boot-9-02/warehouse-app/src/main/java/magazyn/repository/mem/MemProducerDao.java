package magazyn.repository.mem;

import magazyn.repository.ProducerDao;
import magazyn.model.Producer;

import java.util.List;

public class MemProducerDao implements ProducerDao {
    @Override
    public List<Producer> findAll() {
        return SampleData.producers; // [cite: 181]
    }

    @Override
    public Producer findById(int id) {
        return SampleData.producers.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null); // [cite: 164-165]
    }

    @Override
    public Producer add(Producer p) {
        int max = SampleData.producers.stream()
                .mapToInt(Producer::getId)
                .max()
                .orElse(0); // [cite: 181]
        p.setId(++max);
        SampleData.producers.add(p);
        return p;
    }
}