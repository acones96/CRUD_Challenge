package com.aserta.challenge.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ProductTest {

    @Test
    void testSettersAndGetters() {
        Product product = new Product();

        product.setId(1);
        assertEquals(1, product.getId());

        product.setNombre("Laptop");
        assertEquals("Laptop", product.getNombre());

        product.setPrecio(1500L);
        assertEquals(1500L, product.getPrecio());
    }

    @Test
    void testProductAllConstructor() {
        Product product = new Product(1, "Laptop", 1500L);

        assertEquals(1, product.getId());
        assertEquals("Laptop", product.getNombre());
        assertEquals(1500L, product.getPrecio());
    }
}