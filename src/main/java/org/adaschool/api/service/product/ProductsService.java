package org.adaschool.api.service.product;

import org.adaschool.api.repository.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsService {

    Product save(Product product);

    List<Product> all();

    Optional<Product> findById(String id);

    void deleteById(String id);
}
