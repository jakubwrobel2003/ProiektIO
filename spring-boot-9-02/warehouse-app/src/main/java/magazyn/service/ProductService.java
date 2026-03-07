package magazyn.service;

import magazyn.model.Producer;
import magazyn.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(int id);
    // Nowa wersja: dodaje produkt i opcjonalnie inicjuje stan w magazynie
    Product addProduct(Product p, int initialWarehouseId, int amount);

    List<Producer> getAllProducers();
    Producer getProducerById(int id);
    Producer addProducer(Producer p);

    int getTotalQuantity(int productId);
}