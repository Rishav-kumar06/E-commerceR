/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.dao;

import com.myapp.models.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static List<Product> products = new ArrayList<>();

    static {
        // Sample products for testing
        products.add(new Product(1, "Sample Product 1", 49.99, "https://via.placeholder.com/150"));
        products.add(new Product(2, "Sample Product 2", 79.99, "https://via.placeholder.com/150"));
        products.add(new Product(3, "Sample Product 3", 99.99, "https://via.placeholder.com/150"));
    }

    // get all products
    public List<Product> getAllProducts() {
        return products;
    }

    // get product by ID
    public Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
