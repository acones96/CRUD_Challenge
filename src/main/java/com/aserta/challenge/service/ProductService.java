package com.aserta.challenge.service;

import com.aserta.challenge.model.Product;
import com.aserta.challenge.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String addProduct(Product product) {
        productRepository.save(product);

        return "Product Added";
    }

    public String deleteProduct(int id) {
        productRepository.deleteById(id);

        return "Product Deleted";
    }

    public Product updateProduct(int id, Product product) {
        Product response = productRepository.findById(id).orElse(new Product());

        if (response.getId() != 0) {
            response.setNombre(product.getNombre());
            response.setPrecio(product.getPrecio());

            return productRepository.save(response);
        } else {
            return new Product();
        }
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(new Product());
    }
}
