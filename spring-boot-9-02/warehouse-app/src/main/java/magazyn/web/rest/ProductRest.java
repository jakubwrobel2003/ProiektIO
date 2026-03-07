package magazyn.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.model.Product;
import magazyn.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Products.
 * Uses Lombok for constructor injection and logging.
 */
@RestController // Informs Spring that this is a REST controller
@RequestMapping("/api") // Base path for all endpoints in this class
@RequiredArgsConstructor // Automatically injects 'final' fields via constructor
@Slf4j // Enables SLF4J logging
public class ProductRest {

    private final ProductService productService;

    /**
     * GET /product : Get a list of all products (catalog).
     * @return ResponseEntity with the list of all products.
     */
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("REST request to get all Products");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * GET /product/{id}/total-quantity : Get the total quantity of a specific product across all warehouses.
     * Utilizes summation logic from the service based on InventoryDao.
     * @param id the ID of the product.
     * @return ResponseEntity containing the total quantity or 404 if not found.
     */
    @GetMapping("/product/{id}/total-quantity")
    public ResponseEntity<Integer> getTotalQuantity(@PathVariable int id) {
        log.info("REST request to get total quantity for Product : {}", id);

        // First, verify if the product exists in the catalog
        if (productService.getProductById(id) == null) {
            log.warn("Product with id {} not found", id);
            return ResponseEntity.notFound().build();
        }

        // Retrieve total quantity aggregated from all warehouse locations
        int total = productService.getTotalQuantity(id);
        log.info("Total quantity for product {} is {}", id, total);
        return ResponseEntity.ok(total);
    }
}