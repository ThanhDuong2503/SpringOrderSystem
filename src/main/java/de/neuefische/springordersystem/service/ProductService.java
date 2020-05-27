package de.neuefische.springordersystem.service;


import de.neuefische.springordersystem.db.ProductDb;
import de.neuefische.springordersystem.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {

    public ProductService() {
    }

    private ProductDb productDb;

    @Autowired
    public ProductService(ProductDb productDb) {
        this.productDb = productDb;
    }

    public ArrayList<Product> listProducts() {
        return productDb.listProducts();
    }

    public Optional<Product> getProducts(String id) {
        return productDb.getProductById(id);
    }

    public ArrayList<Product> searchProductByName(String query) {
        ArrayList<Product> matchingProducts = new ArrayList<Product>();
        if (query == null) {
            return productDb.listProducts();
        } else {
            for (int i = 0; i < productDb.listProducts().size(); i++) {
                Product product = productDb.listProducts().get(i);
                if (product.getName().startsWith(query)) {
                    matchingProducts.add(product);
                }
            }
        }
        return matchingProducts;
    }
}
