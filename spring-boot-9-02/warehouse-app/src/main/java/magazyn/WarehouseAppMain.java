package magazyn;

import magazyn.repository.WarehouseDao;
import magazyn.repository.ProductDao;
import magazyn.repository.mem.MemWarehouseDao;
import magazyn.repository.mem.MemProductDao;
import magazyn.model.Warehouse;
import magazyn.service.WarehouseService;
import magazyn.service.impl.WarehouseServiceBean;

import java.util.List;

public class WarehouseAppMain {

    public static void main(String[] args) {
        System.out.println("Let's find warehouses!");

        // Przygotowanie repozytoriów (dostęp do danych w pamięci)
        WarehouseDao warehouseDao = new MemWarehouseDao();
        ProductDao productDao = new MemProductDao();

        // Przygotowanie serwisu - serwis działa jako fasada nad repozytoriami [cite: 314, 316]
        // Wstrzykujemy zależności (DAO) przez konstruktor
        WarehouseService service = new WarehouseServiceBean(warehouseDao, productDao);

        // Użycie serwisu do pobrania danych
        List<Warehouse> warehouses = service.getAllWarehouses();

        System.out.println(warehouses.size() + " warehouses found:");
        warehouses.forEach(System.out::println);
    }
}