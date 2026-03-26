package magazyn.repository.data;

import lombok.RequiredArgsConstructor;
import magazyn.model.Warehouse;
import magazyn.repository.WarehouseDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class DataWarehouseDao implements WarehouseDao {
    private final WarehouseRepository repository;

    @Override
    public List<Warehouse> findAll() { return repository.findAll(); }

    @Override
    public Warehouse findById(int id) { return repository.findById(id).orElse(null); }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Warehouse save(Warehouse w) { return repository.save(w); }
}