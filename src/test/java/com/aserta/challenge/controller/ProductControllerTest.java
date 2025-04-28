package com.aserta.challenge.controller;

import com.aserta.challenge.model.Product;
import com.aserta.challenge.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Mock
    private Product mockProduct;

    private Product product;
    private List<Product> products;


    @BeforeEach
    void setUp() {
        product = new Product(1, "Laptop", 1500L);

        products = Arrays.asList(
                new Product(1, "Desktop", 1500L),
                new Product(2, "Laptop", 2000L),
                new Product(3, "Phone", 599L));
    }

    @Test
    void testAddProduct() {
        String message = "Product added";

        when(productService.addProduct(product)).thenReturn(message);

        ResponseEntity<String> response = productController.addProduct(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());

        verify(productService, times(1)).addProduct(product);
    }

    @Nested
    class testGetAllProducts {
        @Test
        void testGetAllProducts() {
            int productSize = 3;

            when(productService.getAllProducts()).thenReturn(products);

            ResponseEntity<List<Product>> response = productController.getAllProducts();

            assertEquals(products, response.getBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(productSize, response.getBody().size());

            verify(productService, times(1)).getAllProducts();
        }

        @Test
        void testGetAllProductsIsEmpty() {
            when(productService.getAllProducts()).thenReturn(new ArrayList<>());

            ResponseEntity<List<Product>> response = productController.getAllProducts();

            assertNull(response.getBody());
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

            verify(productService, times(1)).getAllProducts();
        }
    }

    @Nested
    class testGetProduct {
        @Test
        void testGetProduct() {
            int id = 1;

            when(productService.getProductById(id)).thenReturn(products.get(id));

            ResponseEntity<Product> response = productController.getProduct(id);

            assertEquals(products.get(id), response.getBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());

            verify(productService, times(1)).getProductById(id);
        }

        @Test
        void testGetProductIsEmpty() {
            when(productService.getProductById(anyInt())).thenReturn(mockProduct);

            ResponseEntity<Product> response = productController.getProduct(anyInt());

            assertNull(response.getBody());
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

            verify(productService, times(1)).getProductById(anyInt());
        }
    }


    @Nested
    class testUpdateProduct {
        @Test
        void testUpdateProduct() {
            int id = 1;

            when(productService.updateProduct(id, mockProduct)).thenReturn(product);

            ResponseEntity<Product> response = productController.updateProduct(id, mockProduct);

            assertEquals(product, response.getBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());

            verify(productService, times(1)).updateProduct(id, mockProduct);
        }

        @Test
        void testUpdateProductIsEmpty() {
            int id = 8;

            when(productService.updateProduct(id, mockProduct)).thenReturn(new Product());

            ResponseEntity<Product> response = productController.updateProduct(id, mockProduct);

            assertNull(response.getBody());
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

            verify(productService, times(1)).updateProduct(id, mockProduct);
        }
    }


    @Test
    void deleteProduct() {
        int id = 1;
        String message = "Product Deleted";

        when(productService.deleteProduct(id)).thenReturn(message);

        ResponseEntity<String> response = productController.deleteProduct(id);

        assertEquals(message, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(productService, times(1)).deleteProduct(id);
    }
}