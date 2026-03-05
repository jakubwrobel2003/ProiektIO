package magazyn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Adnotacja informuje Springa, że to klasa konfiguracyjna [cite: 1297]
public class WarehouseConfig {

    @Bean // Metoda tworzy beana o identyfikatorze "appName" [cite: 1300, 1302]
    public String appName() {
        return "System Zarządzania Magazynem v1.1";
    }

    @Bean
    public String warehouseContact() {
        return "Kontakt: biuro@magazyn-centralny.pl";
    }
}