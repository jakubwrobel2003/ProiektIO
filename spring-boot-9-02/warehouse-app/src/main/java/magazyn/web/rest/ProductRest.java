package magazyn.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.model.Product;
import magazyn.model.Warehouse;
import magazyn.service.ProductService;

import magazyn.web.rest.dto.ProductDTO;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ProductRest {

    private final ProductService productService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    // HTTP 7. Pobierz wszystkie produkty
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // HTTP 8. Pobierz konkretny produkt
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product p = productService.getProductById(id);
        return (p != null) ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    // HTTP 9. Pobierz produkt po SKU
    @GetMapping("/product/sku/{skuCode}")
    public ResponseEntity<Product> getProductBySku(@PathVariable String skuCode) {
        Product p = productService.getProductbySkuCode(skuCode);
        return (p != null) ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    // HTTP 4. Sprawdź w jakich magazynach znajduje się produkt
    @GetMapping("/product/{id}/inventory")
    public ResponseEntity<List<Warehouse>> getProductInventory(@PathVariable int id) {
        Product p = productService.getProductById(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p.getWarehouses());
    }

    // HTTP 6. Pobierz łączną liczbę magazynów (total quantity)
    @GetMapping("/product/{id}/total-quantity")
    public ResponseEntity<Integer> getTotalQuantity(@PathVariable int id) {
        if (productService.getProductById(id) == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productService.getTotalQuantity(id));
    }

    // HTTP 10. Dodaj produkt
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(
            @Validated @RequestBody ProductDTO productDTO, // Nazwa musi pasować do @InitBinder("productDTO")
            Errors errors,
            HttpServletRequest request) {

        log.info("REST request to add product: {}", productDTO);

        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMsg = errors.getAllErrors().stream()
                    .map(e -> messageSource.getMessage(e.getCode(), new Object[0], locale))
                    .reduce("Błędy walidacji:\n", (acc, eMsg) -> acc + eMsg + "\n");
            return ResponseEntity.badRequest().body(errorMsg);
        }

        Product created = productService.addProduct(new Product(0, productDTO.getName(), productDTO.getSkuCode(), productDTO.getBrand(), productDTO.getCategory()));
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    private String formatErrors(Errors errors, HttpServletRequest request) {
        Locale locale = localeResolver.resolveLocale(request);
        return errors.getAllErrors().stream()
                .map(e -> messageSource.getMessage(e.getCode(), new Object[0], locale))
                .reduce("Błędy walidacji:\n", (acc, eMsg) -> acc + eMsg + "\n");
    }
}

    // HTTP 11. Przypisz produkt do magazynu
