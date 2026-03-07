package magazyn.service;

import magazyn.model.Product;
import magazyn.model.Warehouse;
import java.util.List;

public interface WarehouseService {

    Warehouse getWarehouseById(int id);


    List<Warehouse> getAllWarehouses();




    Warehouse addWarehouse(Warehouse w);



}