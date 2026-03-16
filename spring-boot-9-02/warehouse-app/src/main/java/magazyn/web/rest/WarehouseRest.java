package magazyn.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.model.Product;
import magazyn.model.Warehouse;
import magazyn.service.WarehouseService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class WarehouseRest {

    private final WarehouseService warehouseService;
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final WarehoueseValidation validation;

    @InitBinder
    void initBinder(org.springframework.web.bind.WebDataBinder binder) {
        binder.addValidators(validation);
    }

    // HTTP 1. Pobierz wszystkie magazyny
    @GetMapping("/warehouse")
    public ResponseEntity<List<Warehouse>> getAllWarehouses(
            @RequestParam(value = "phrase", required = false) String phrase,
            @RequestHeader(value = "custom-header", required = false) String customHeader
    ) {
        log.info("About to retrieve warehouses list");
        log.info("Phrase param: {}", phrase);
        log.info("Custom header: {}", customHeader);

        // Przykład wyzwalania wyjątku dla WarehouseAdvice (jak na obrazku)
        if (phrase != null && phrase.equals("foo")) {
            log.error("Invalid phrase provided: foo");
            throw new IllegalArgumentException("Foo!");
        }

        List<Warehouse> warehouses = warehouseService.getAllWarehouses();

        // Filtrowanie po frazie (opcjonalnie, jeśli chcesz wyszukiwać)
        if (phrase != null && !phrase.isEmpty()) {
            warehouses = warehouses.stream()
                    .filter(w -> w.getName().toLowerCase().contains(phrase.toLowerCase()))
                    .toList();
        }

        log.info("{} warehouses found", warehouses.size());
        return ResponseEntity.ok(warehouses);
    }

    // HTTP 2. Pobierz magazyn po ID
    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Warehouse> getWarehouse(@PathVariable int id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        if (warehouse == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(warehouse);
    }

    // HTTP 3. Sprawdź produkty w magazynie
    @GetMapping("/warehouse/{id}/inventory")
    public ResponseEntity<List<Product>> getWarehouseInventory(@PathVariable int id) {
        Warehouse warehouse = warehouseService.getWarehouseById(id);
        if (warehouse == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(warehouse.getProducts());
    }

    // HTTP 5. Dodaj nowy magazyn
    @PostMapping("/warehouse")
    public ResponseEntity<?> addWarehouse(@Validated @RequestBody Warehouse w, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);
            String errorMsg = errors.getAllErrors().stream()
                    .map(e -> messageSource.getMessage(e.getCode(), new Object[0], locale))
                    .reduce("Błędy walidacji:\n", (acc, eMsg) -> acc + eMsg + "\n");
            return ResponseEntity.badRequest().body(errorMsg);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseService.addWarehouse(w));
    }
}