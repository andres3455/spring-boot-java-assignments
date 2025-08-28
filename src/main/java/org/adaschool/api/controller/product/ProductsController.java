package org.adaschool.api.controller.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/*
By Andrés Rodriguez 27/08/2025
 */

@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    /**
     * POST /v1/products - Crear un nuevo producto
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productsService.save(product);
        URI createdProductUri = URI.create("/v1/products/" + createdProduct.getId());
        return ResponseEntity.created(createdProductUri).body(createdProduct);
    }

    /**
     * GET /v1/products - Obtener todos los productos
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productsService.all());
    }

    /**
     * GET /v1/products/{id} - Buscar producto por ID
     * Lanza ProductNotFoundException si no se encuentra
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id) {
        Optional<Product> product = productsService.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    /**
     * PUT /v1/products/{id} - Actualizar producto existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        Optional<Product> existingProduct = productsService.findById(id);
        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();

            if (product.getName() != null) {
                productToUpdate.setName(product.getName());
            }
            if (product.getDescription() != null) {
                productToUpdate.setDescription(product.getDescription());
            }
            if (product.getCategory() != null) {
                productToUpdate.setCategory(product.getCategory());
            }
            if (product.getPrice() != 0) {
                productToUpdate.setPrice(product.getPrice());
            }

            Product updatedProduct = productsService.save(productToUpdate);
            return ResponseEntity.ok(updatedProduct);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    /**
     * DELETE /v1/products/{id} - Eliminar producto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        Optional<Product> existingProduct = productsService.findById(id);
        if (existingProduct.isPresent()) {
            productsService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            throw new ProductNotFoundException(id);
        }
    }
}
