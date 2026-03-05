package magazyn.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.model.Warehouse;
import magazyn.service.WarehouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WarehouseRest {
    private final WarehouseService warehouseService;
    @GetMapping("/warehouse")
    List<Warehouse> getWarehouse() {
        log.info("getWarehouse");
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        log.info("warehouses: {}", warehouses);
        return warehouses;
    }

}
