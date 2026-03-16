package magazyn.service;

import magazyn.model.Producer;
import magazyn.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(int id);

    // ZMIANA: Usunięto 'int amount', dodajemy tylko powiązanie z magazynem
    Product addProduct(Product p, int initialWarehouseId);
    Product addProduct(Product p);
    List<Product> getProductsByProducer(int producerId);
    List<Producer> getAllProducers();
    Producer getProducerById(int id);
    Producer addProducer(Producer p);
    Product getProductbySkuCode(String skuCode);

    // ZMIANA: Teraz zwraca liczbę magazynów, w których produkt występuje
    int getTotalQuantity(int productId);
}