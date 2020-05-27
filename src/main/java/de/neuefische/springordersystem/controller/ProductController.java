package de.neuefische.springordersystem.controller;

import de.neuefische.springordersystem.db.ProductDb;
import de.neuefische.springordersystem.model.Product;
import de.neuefische.springordersystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ArrayList<Product> listProducts(){
        return productService.listProducts();
    }

    @GetMapping("{id}")
    public Optional<Product> getProducts(@PathVariable String id) {
        return productService.getProducts(id);
    }


}
