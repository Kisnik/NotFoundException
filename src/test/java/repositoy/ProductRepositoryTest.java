package repositoy;

import Domain.Product;
import Exceptions.NotFoundException;
import manager.ProductManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private Product[] products;
    private ProductRepository productRepository = new ProductRepository();
    private ProductManager productManager = new ProductManager(productRepository);

    @Test
    public void shouldRemoveByNotExistId() {
        products = new Product[2];
        products[0] = new Product(1, "name", 10);
        products[1] = new Product(2, "name2", 20);

        productRepository.save(products[0]);
        productRepository.save(products[1]);


        assertThrows(NotFoundException.class, () -> productRepository.removeById(3));

    }

    @Test
    public void shouldRemoveByExistId() {
        products = new Product[3];
        products[0] = new Product(1, "name", 10);
        products[1] = new Product(2, "name2", 20);
        products[2] = new Product(3, "name3", 30);

        productRepository.save(products[0]);
        productRepository.save(products[1]);
        productRepository.save(products[2]);

        productRepository.removeById(1);

        Product[] actual = productRepository.findAll();
        Product[] expected = {products[1], products[2]};

        assertArrayEquals(expected, actual);


    }
}