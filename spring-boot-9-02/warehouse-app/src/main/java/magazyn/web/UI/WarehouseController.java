package magazyn.web.UI;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.service.ProductService;
import magazyn.service.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Adnotacja definiująca klasę jako kontroler MVC
@RequiredArgsConstructor // Automatyczny konstruktor dla serwisów
@Slf4j // Umożliwia logowanie zdarzeń
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final ProductService productService;

    // Widok listy magazynów [cite: 14, 17, 18]
    @GetMapping("/view/warehouses")
    public String getWarehousesView(Model model) {
        log.info("About to display warehouses list"); //
        model.addAttribute("title", "Nasze Magazyny"); //
        model.addAttribute("warehouses", warehouseService.getAllWarehouses()); //
        return "warehousesView"; // Zwraca nazwę szablonu HTML
    }

    // Widok listy produktów z opcjonalnym filtrowaniem [cite: 14, 18, 22, 23]
    @GetMapping("/view/products")
    public String getProductsView(
            @RequestParam(required = false) Integer warehouseId,
            @RequestParam(required = false) Integer producerId,
            Model model) {

        log.info("About to display products list. Filtering - Warehouse: {}, Producer: {}", warehouseId, producerId);

        if (warehouseId != null) {
            model.addAttribute("title", "Produkty w magazynie: " + warehouseId);
            model.addAttribute("products", warehouseService.getWarehouseById(warehouseId).getProducts());
        } else if (producerId != null) {
            model.addAttribute("title", "Produkty producenta: " + producerId);
            model.addAttribute("products", productService.getProductsByProducer(producerId));
        } else {
            model.addAttribute("title", "Wszystkie Produkty");
            model.addAttribute("products", productService.getAllProducts());
        }

        return "productsView";
    }

    // Widok listy producentów [cite: 14, 23]
    @GetMapping("/view/producers")
    public String getProducersView(Model model) {
        log.info("About to display producers list");
        model.addAttribute("title", "Katalog Producentów");
        model.addAttribute("producers", productService.getAllProducers());
        return "producersView";
    }
}