package magazyn.web.rest;
import java.util.Locale;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.model.Inventory;
import magazyn.model.Product;
import magazyn.model.Warehouse;
import magazyn.service.InventoryService;
import magazyn.service.ProductService;
import magazyn.service.WarehouseService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;
import java.util.List;

/**
 * REST controller for managing Warehouses and their Inventory.
 */
@RestController // Informs Spring that this is a REST controller
@RequiredArgsConstructor // Automatically injects final fields via constructor
@Slf4j // Enables SLF4J logging
@RequestMapping("/api") // Base path for all endpoints in this class
public class WarehouseRest {

    private final InventoryService inventoryService;
    private final WarehouseService warehouseService;
    private final ProductService productService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    /**
     * GET /warehouse : Get a list of all warehouses.
     * @param name optional filter by warehouse name.
     * @param customHeader optional custom header for tracking.
     * @return ResponseEntity with the list of warehouses.
     */
    @GetMapping("/warehouse")
    public ResponseEntity<List<Warehouse>> getAllWarehouses(
            @RequestParam(value= "name", required = false) String name,
            @RequestHeader(value = "value", required = false) String customHeader
    ) {
        log.info("REST request to get all Warehouses");
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    /**
     * GET /warehouse/{id} : Get a single warehouse by ID.
     * Returns 404 if not found or 400 if the ID is not a valid number.
     * @param id the ID of the warehouse.
     * @return ResponseEntity containing the warehouse or error status.
     */
    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable String id) {
        log.info("REST request to get Warehouse : {}", id);
        try {
            int warehouseId = Integer.parseInt(id);
            Warehouse warehouse = warehouseService.getWarehouseById(warehouseId);

            if (warehouse == null) {
                log.warn("Warehouse with id {} not found", id);
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(warehouse);
        } catch (NumberFormatException e) {
            log.error("Invalid warehouse id format: {}", id);
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * GET /warehouse/{id}/inventory : Get the list of product inventories in a specific warehouse.
     * @param id the ID of the warehouse.
     * @return ResponseEntity with the list of inventory records.
     */
    @GetMapping("/warehouse/{id}/inventory")
    public ResponseEntity<List<Inventory>> getWarehouseInventory(@PathVariable int id) {
        log.info("REST request to get inventory for Warehouse : {}", id);

        Warehouse warehouse = warehouseService.getWarehouseById(id);
        if (warehouse == null) {
            log.warn("Warehouse with id {} not found", id);
            return ResponseEntity.notFound().build();
        }

        // Utilizing the dedicated InventoryService
        List<Inventory> inventory = inventoryService.getInventoryForWarehouse(id);

        log.info("Found {} inventory records for warehouse {}", inventory.size(), id);
        return ResponseEntity.ok(inventory);
    }

    /**
     * GET /product/{id}/inventory : Get inventory status for a specific product across all warehouses.
     * @param id the ID of the product.
     * @return ResponseEntity with the list of inventory records.
     */
    @GetMapping("/product/{id}/inventory")
    public ResponseEntity<List<Inventory>> getProductInventory(@PathVariable int id) {
        log.info("REST request to get inventory for Product : {}", id);

        Product product = productService.getProductById(id);
        if (product == null) {
            log.warn("Product with id {} not found", id);
            return ResponseEntity.notFound().build();
        }

        // Fetching product stock levels from all locations
        List<Inventory> inventory = inventoryService.getInventoryForProduct(id);

        log.info("Found {} inventory records for product {}", inventory.size(), id);
        return ResponseEntity.ok(inventory);
    }

    /**
     * POST /warehouse : Add a new warehouse to the system.
     * @param w the warehouse object to add.
     * @return ResponseEntity with the created warehouse and 201 Created status.
     */
    @PostMapping("/warehouse")
    public ResponseEntity<?> addWarehouse(@Validated @RequestBody Warehouse w, Errors errors, HttpServletRequest request) {
        log.info("REST request to add Warehouse : {}", w);

        if (errors.hasErrors()) {
            // Ustawiamy lokalizację na polską, aby pobrać polskie komunikaty z messages_pl.properties
            Locale locale = localeResolver.resolveLocale(request);

            String errorMsg = errors.getAllErrors().stream()
                    // Zmieniamy 'oe' na 'e', aby pasowało do nazwy zmiennej w Twojej lambdzie
                    .map(e -> messageSource.getMessage(e.getCode(), new Object[0], locale))
                    // Łączymy wszystkie błędy w jeden ciąg tekstowy
                    .reduce("Błędy walidacji:\n", (acc, eMsg) -> acc + eMsg + "\n");

            return ResponseEntity.badRequest().body(errorMsg);
        }

        Warehouse added = warehouseService.addWarehouse(w);
        return ResponseEntity.status(HttpStatus.CREATED).body(added);
    }
}