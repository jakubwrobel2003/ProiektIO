package magazyn.web.rest.dto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.web.rest.dto.ProductValidator;
import magazyn.web.rest.WarehoueseValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class WarehouseAdvice {

    private final WarehoueseValidation warehouseValidator;
    private final ProductValidator productValidator; // Musisz dodać ten walidator

    // Rejestracja walidatora dla obiektu Warehouse (używanego w WarehouseRest)
    @InitBinder("warehouse")
    void initBinderForWarehouse(WebDataBinder binder) {
        log.info("Inicjalizacja walidatora dla Warehouse");
        binder.addValidators(warehouseValidator);
    }

    // Rejestracja walidatora dla obiektu ProductDTO (używanego w ProductRest)
    // UWAGA: Nazwa w InitBinder musi pasować do nazwy typu (z małej litery) lub nazwy parametru
    @InitBinder("productDTO")
    public void initBinderForProduct(WebDataBinder binder) {
        log.info("Inicjalizacja walidatora dla ProductDTO");
        binder.addValidators(productValidator); // Używamy poprawnego walidatora
    }

    // Przechwytywanie błędu dla Twojego testu phrase=foo
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegal(IllegalArgumentException e) {
        log.error("Złapano wyjątek: {}", e.getMessage());
        // Zwracamy status 418 (I_AM_A_TEAPOT) dla zabawy lub 400 (Bad Request)
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Obsłużony błąd: " + e.getMessage());
    }
}