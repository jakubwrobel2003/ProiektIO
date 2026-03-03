package magazyn.repository;

import magazyn.model.Warehouse;
import magazyn.model.Product;
import java.util.List;

public interface WarehouseDao {
    List<Warehouse> findAll();
    Warehouse findById(int id);
    List<Warehouse> findByProduct(Product p);
}