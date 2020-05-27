package de.neuefische.springordersystem.db;

import de.neuefische.springordersystem.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDb {

    public ProductDb() {
    }

    private final ArrayList<Product> products = new ArrayList<>(List.of(
            new Product("1", "Hamburger"),
            new Product("2", "Cheeseburger"),
            new Product("3", "BigMac"),
            new Product("4", "Chickenburger"),
            new Product("5", "Chilliburger")
    ));


    public ProductDb(List<Product> initialProducts){
        this.products.addAll(initialProducts);
    }

    public List<Product> listProducts(){
        return products;
    }

    public Optional<Product> getProductById(String id){
        for (Product product : products) {
            if(product.getId().equals(id)){
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }

    public static void printProducts(ProductDb db) {
        List<Product> products = db.listProducts();
        System.out.println("Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
