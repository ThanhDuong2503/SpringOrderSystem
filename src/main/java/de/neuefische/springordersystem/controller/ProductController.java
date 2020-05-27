package de.neuefische.springordersystem.controller;

import de.neuefische.springordersystem.db.ProductDb;
import de.neuefische.springordersystem.model.Product;
import de.neuefische.springordersystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ArrayList<Product> listProducts() {
        return productService.listProducts();
    }

    @GetMapping("{id}")
    public Optional<Product> getProducts(@PathVariable String id) {
        return productService.getProducts(id);
    }

    @GetMapping("search")
    public ArrayList<Product> searchProductByName(@RequestParam(name = "q", required = false) String query) {
        return productService.searchProductByName(query);
    }
}
