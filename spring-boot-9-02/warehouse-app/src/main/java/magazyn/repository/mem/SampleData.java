package magazyn.repository.mem;

import magazyn.model.Inventory;
import magazyn.model.Warehouse;
import magazyn.model.Producer;
import magazyn.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SampleData {

    public static List<Warehouse> warehouses = new ArrayList<>();
    public static List<Producer> producers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<Inventory> inventories = new ArrayList<>();

    static {
        // 1. Producenci (Marki odzieżowe)
        Producer nike = new Producer(1, "Nike", "USA");
        Producer adidas = new Producer(2, "Adidas", "Niemcy");
        Producer patagonia = new Producer(3, "Patagonia", "USA");
        Producer levis = new Producer(4, "Levi's", "USA");

        // 2. Produkty (Ubrania)
        Product tShirt = new Product(1, "Koszulka Dri-FIT", "TSH-N01", nike, "Sport");
        Product shorts = new Product(2, "Spodenki Run 5", "SHO-N02", nike, "Sport");
        Product hoodie = new Product(3, "Bluza Trefoil", "HOD-A01", adidas, "Casual");
        Product jacket = new Product(4, "Kurtka Torrentshell", "JAC-P01", patagonia, "Outdoor");
        Product jeans = new Product(5, "Jeansy 501 Original", "JNS-L01", levis, "Casual");
        Product beanie = new Product(6, "Czapka Zimowa", "CAP-A02", adidas, "Akcesoria");

        // 3. Wiązanie produktów z markami (Używamy setBrand z Lomboka)
        bind(tShirt, nike);
        bind(shorts, nike);
        bind(hoodie, adidas);
        bind(beanie, adidas);
        bind(jacket, patagonia);
        bind(jeans, levis);

        // 4. Magazyny (Lokalizacje)
        Warehouse centralny = new Warehouse(1, "Magazyn Główny", "Łódź, ul. Tekstylna 10");
        Warehouse polnoc = new Warehouse(2, "Outlet Gdańsk", "Gdańsk, ul. Bałtycka 5");
        Warehouse zachod = new Warehouse(3, "Centrum Wrocław", "Wrocław, ul. Modna 22");
        Warehouse rzeszow = new Warehouse(4, "Punkt Południe", "Rzeszów, ul. Rynkowa 1");

        // 5. Wiązanie Magazyn + Produkt + Ilość przez Inventory
        bind(tShirt, centralny, 100);
        bind(tShirt, polnoc, 50);
        bind(shorts, centralny, 80);
        bind(jacket, zachod, 20);
        bind(jeans, rzeszow, 60);
        bind(hoodie, centralny, 40);

        // 6. Dodawanie do list statycznych
        products.addAll(List.of(tShirt, shorts, hoodie, jacket, jeans, beanie));
        producers.addAll(List.of(nike, adidas, patagonia, levis));
        warehouses.addAll(List.of(centralny, polnoc, zachod, rzeszow));
    }

    /**
     * Kluczowa metoda wiążąca Magazyn z Produktem poprzez Inventory.
     * To tutaj zapisujemy ilość towaru w konkretnym miejscu.
     */
    private static void bind(Product p, Warehouse w, int amount) {
        Inventory item = new Inventory(p, w, amount);
        inventories.add(item);

    }

    /**
     * Metoda wiążąca Produkt z Producentem (Marką).
     */
    private static void bind(Product p, Producer pr) {
        pr.addProduct(p); // Dodaje do List<Product> w klasie Producer
        p.setBrand(pr);   // Ustawia pole brand w klasie Product (Lombok)
    }
}