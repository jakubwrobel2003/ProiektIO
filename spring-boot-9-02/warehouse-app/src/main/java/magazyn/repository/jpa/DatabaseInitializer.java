package magazyn.repository.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import magazyn.model.Producer;
import magazyn.model.Product;
import magazyn.model.Warehouse;
import magazyn.repository.ProducerDao;
import magazyn.repository.ProductDao;
import magazyn.repository.WarehouseDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final ProductDao productDao;
    private final ProducerDao producerDao;
    private final WarehouseDao warehouseDao;

    @Override
    @Transactional
    public void run(String... args) {
        log.info("Sprawdzanie stanu bazy danych...");

        // 1. Sprawdzamy, czy baza jest pusta, aby nie dublować danych przy każdym restarcie
        if (producerDao.findAll().isEmpty()) {
            log.info("Baza danych jest pusta. Rozpoczynanie ładowania danych testowych...");

            // 2. NAJPIERW DODAJEMY PRODUCENTÓW
            // Muszą zostać zapisani jako pierwsi, aby baza nadała im ID, do których odwołają się produkty
            Producer nike = producerDao.add(new Producer(0, "Nike", "USA"));
            Producer adidas = producerDao.add(new Producer(0, "Adidas", "Niemcy"));
            Producer patagonia = producerDao.add(new Producer(0, "Patagonia", "USA"));
            Producer levis = producerDao.add(new Producer(0, "Levi's", "USA"));

            // 3. TWORZYMY OBIEKTY PRODUKTÓW
            // Przypisujemy im już zapisanych producentów (nike, adidas itd.)
            Product tShirt = new Product(0, "Koszulka Dri-FIT", "TSH-N01", nike, "Sport");
            Product shorts = new Product(0, "Spodenki Run 5", "SHO-N02", nike, "Sport");
            Product hoodie = new Product(0, "Bluza Trefoil", "HOD-A01", adidas, "Casual");
            Product jacket = new Product(0, "Kurtka Torrentshell", "JAC-P01", patagonia, "Outdoor");
            Product jeans = new Product(0, "Jeansy 501 Original", "JNS-L01", levis, "Casual");

            // 4. TWORZYMY MAGAZYNY
            Warehouse centralny = new Warehouse(0, "Magazyn Główny", "Łódź, ul. Tekstylna 10");
            Warehouse polnoc = new Warehouse(0, "Outlet Gdańsk", "Gdańsk, ul. Bałtycka 5");

            // 5. BUDUJEMY RELACJE (Many-to-Many)
            // Wywołujemy Twoją metodę bind, która dodaje obiekty do wzajemnych list
            bind(tShirt, centralny);
            bind(tShirt, polnoc);
            bind(shorts, centralny);
            bind(hoodie, centralny);

            // 6. ZAPISUJEMY PRODUKTY
            // Teraz, gdy producenci są już w bazie, te operacje nie rzucą błędem Foreign Key
            productDao.add(tShirt);
            productDao.add(shorts);
            productDao.add(hoodie);
            productDao.add(jacket);
            productDao.add(jeans);

            // 7. ZAPISUJEMY MAGAZYNY
            // To wywołanie spowoduje, że Hibernate uzupełni tabelę łączącą 'product_warehouse'
            warehouseDao.save(centralny);
            warehouseDao.save(polnoc);

            log.info("Pomyślnie załadowano {} produktów do MySQL.", productDao.findAll().size());
        } else {
            log.info("Baza danych zawiera już dane. Pomijanie inicjalizacji.");
        }
    }

    private void bind(Product p, Warehouse w) {
        if (p.getWarehouses() == null) p.setWarehouses(new ArrayList<>());
        if (w.getProducts() == null) w.setProducts(new ArrayList<>());
        p.getWarehouses().add(w);
        w.getProducts().add(p);
    }
}