package magazyn.config;

import lombok.RequiredArgsConstructor;
import magazyn.service.ProductService;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component // Musisz dodać tę adnotację, aby Spring widział ten komponent
@RequiredArgsConstructor // Automatycznie stworzy konstruktor dla pola final [cite: 69]
public class WarehouseContributor implements InfoContributor { // Brało tutaj znaku '{'

    private final ProductService productService;

    @Override
    public void contribute(Info.Builder builder) {
        // Dodaje do /actuator/info informację o liczbie produktów
        builder.withDetail("produkt_count", productService.getAllProducts().size());
    }
}