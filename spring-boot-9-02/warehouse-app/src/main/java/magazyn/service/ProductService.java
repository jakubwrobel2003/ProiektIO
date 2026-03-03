package magazyn.service;

import magazyn.model.Producer;
import magazyn.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByProducer(Producer p);

    Product getProductById(int id);

    Product addProduct(Product p);

    List<Producer> getAllProducers();

    Producer getProducerById(int id);

    Producer addProducer(Producer p);
}