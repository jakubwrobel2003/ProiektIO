package magazyn.service;

import magazyn.model.Warehouse;
import magazyn.model.Product;
import java.util.List;

public interface WarehouseService {
    // API zwraca nam informacje o magazynach
    Warehouse getWarehouseById(int id);

    List<Warehouse> getAllWarehouses();

    List<Warehouse> getWarehousesByProduct(Product p);

    List<Product> getProductsInWarehouse(Warehouse w);
}