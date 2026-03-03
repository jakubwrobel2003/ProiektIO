package magazyn.repository.mem; // Zmieniono z vod

import magazyn.model.Warehouse;
import magazyn.model.Producer;
import magazyn.model.Product;

import java.util.ArrayList;
import java.util.List;

class SampleData {


    static List<Warehouse> warehouses = new ArrayList<>();
    static List<Producer> producers = new ArrayList<>();
    static List<Product> products = new ArrayList<>();

    static {

        Producer bosch = new Producer(1, "Bosch", "Niemcy");
        Producer makita = new Producer(2, "Makita", "Japonia");
        Producer dewalt = new Producer(3, "DeWalt", "USA");
        Producer festool = new Producer(4, "Festool", "Niemcy");


        // Pola: id, nazwa, skuCode (zamiast poster), producent, ilość (zamiast rating)
        Product wiertarka = new Product(1, "Wiertarka Udarowa", "SKU-B01", bosch, 50);
        Product szlifierka = new Product(2, "Szlifierka Kątowa", "SKU-B02", bosch, 30);
        Product wkretarka = new Product(3, "Wkrętarka AKU", "SKU-M01", makita, 100);
        Product wyrzynarka = new Product(4, "Wyrzynarka", "SKU-M02", makita, 40);
        Product mlot = new Product(5, "Młot Wyburzeniowy", "SKU-D01", dewalt, 15);
        Product pilarka = new Product(6, "Pilarka Tarczowa", "SKU-F01", festool, 10);

        // 3. Wiązanie produktów z producentami (Relacja 1 do wielu) [cite: 177, 181]
        bind(wiertarka, bosch);
        bind(szlifierka, bosch);
        bind(wkretarka, makita);
        bind(wyrzynarka, makita);
        bind(mlot, dewalt);
        bind(pilarka, festool);

        // 4. Tworzenie magazynów (odpowiednik Cinema) [cite: 101, 205-207]
        Warehouse centralny = new Warehouse(1, "Magazyn Centralny", "Warszawa, ul. Logistyczna 1");
        Warehouse polnoc = new Warehouse(2, "Filia Północ", "Gdańsk, ul. Portowa 5");
        Warehouse zachod = new Warehouse(3, "Centrum Poznań", "Poznań, ul. Przemysłowa 10");
        Warehouse rzeszow = new Warehouse(4, "Punkt Rzeszów", "Rzeszów, ul. Transportowa 2");

        // 5. Wiązanie magazynów z produktami (Relacja wiele do wielu)
        bind(centralny, wiertarka);
        bind(centralny, wkretarka);
        bind(polnoc, wiertarka);
        bind(polnoc, mlot);
        bind(zachod, szlifierka);
        bind(zachod, pilarka);
        bind(rzeszow, wkretarka);
        bind(rzeszow, wyrzynarka);

        // 6. Dodawanie do list statycznych
        products.add(wiertarka);
        products.add(szlifierka);
        products.add(wkretarka);
        products.add(wyrzynarka);
        products.add(mlot);
        products.add(pilarka);

        producers.add(bosch);
        producers.add(makita);
        producers.add(dewalt);
        producers.add(festool);

        warehouses.add(centralny);
        warehouses.add(polnoc);
        warehouses.add(zachod);
        warehouses.add(rzeszow);
    }

    // Metoda wiążąca Magazyn z Produktem (bidirectional)
    private static void bind(Warehouse w, Product p) {
        w.addProduct(p); // Zmieniono z addMovie
        p.addWarehouse(w); // Zmieniono z addCinema
    }

    // Metoda wiążąca Produkt z Producentem (bidirectional)
    private static void bind(Product p, Producer pr) {
        pr.addProduct(p); // Zmieniono z addMovie
        p.setProducer(pr); // Zmieniono z setDirector
    }
}