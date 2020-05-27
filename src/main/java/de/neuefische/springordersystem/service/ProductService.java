package de.neuefische.springordersystem.service;


import de.neuefische.springordersystem.db.ProductDb;
import de.neuefische.springordersystem.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    public ProductService(){
    }

    private ProductDb productDb;

    @Autowired
    public ProductService(ProductDb productDb) {
        this.productDb = productDb;
    }

    public List<Product> listProducts(){
        return productDb.listProducts();
    }

    public Optional<Product> getProducts(String id) {
        return productDb.getProductById(id);
    }
}
