package com.aserta.challenge.service;

import com.aserta.challenge.model.Product;
import com.aserta.challenge.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Mock
    Product mockProduct;

    private List<Product> products;
    private Product product;


    @BeforeEach
    void setUp() {
        product = new Product(1, "Laptop", 1200L);

        products = Arrays.asList(
                new Product(1, "Laptop", 1200L),
                new Product(2, "Desktop", 2000L),
                new Product(3, "Phone", 599L)
        );
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(products);

        List<Product> response = productService.getAllProducts();

        assertEquals(3, response.size());
        assertNotNull(response);

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testAddProduct() {
        String message = "Product Added";

        when(productRepository.save(mockProduct)).thenReturn(product);

        String response = productService.addProduct(mockProduct);

        assertEquals(message, response);
        assertNotNull(response);

        verify(productRepository, times(1)).save(mockProduct);
    }

    @Test
    void testDeleteProduct() {
        String message = "Product Deleted";

        String response = productService.deleteProduct(anyInt());

        assertNotNull(response);
        assertEquals(message, response);

        verify(productRepository, times(1)).deleteById(anyInt());
    }

    @Nested
    class testUpdateProduct {
        @Test
        void testUpdateProduct() {
            int id = 1;

            Product updatedProduct = new Product();
            updatedProduct.setNombre("Laptop Pro");
            updatedProduct.setPrecio(1500L);

            when(productRepository.findById(id)).thenReturn(Optional.of(product));
            when(productRepository.save(product)).thenReturn(product);

            Product result = productService.updateProduct(id, updatedProduct);

            assertNotNull(result);
            assertEquals(id, result.getId());
            assertEquals(updatedProduct.getNombre(), result.getNombre());
            assertEquals(updatedProduct.getPrecio(), result.getPrecio());

            verify(productRepository, times(1)).findById(id);
            verify(productRepository, times(1)).save(product);
        }

        @Test
        void testUpdateProductNotFound() {
            int id = 9;

            Product updatedProduct = new Product();
            updatedProduct.setNombre("Laptop Pro");
            updatedProduct.setPrecio(1500L);

            when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

            Product response = productService.updateProduct(id, updatedProduct);

            assertEquals(0, response.getId());
            assertNotNull(response);
        }
    }


    @Nested
    class testGetProductById {
        @Test
        void testGetProductById() {
            int id = 1;

            when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

            Product response = productService.getProductById(id);

            assertEquals(product, response);

            verify(productRepository, times(1)).findById(id);
        }

        @Test
        void testGetProductByIdNotFound() {
            int id = 9;

            when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

            Product response = productService.getProductById(id);

            assertNotNull(response);
            assertEquals(0, response.getId());

            verify(productRepository, times(1)).findById(anyInt());

        }
    }

}