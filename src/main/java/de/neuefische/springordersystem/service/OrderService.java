package de.neuefische.springordersystem.service;

import de.neuefische.springordersystem.db.OrderDb;
import de.neuefische.springordersystem.db.ProductDb;
import de.neuefische.springordersystem.model.Order;
import de.neuefische.springordersystem.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    public OrderService() {
    }

    private OrderDb orderDb;
    private ProductDb productDb;

    @Autowired
    public OrderService(OrderDb orderDb, ProductDb productDb) {
        this.orderDb = orderDb;
        this.productDb = productDb;
    }

    public ArrayList<Order> listOrders() {
        return orderDb.listOrders();
    }

    public Order orderProducts(ArrayList<String> productIdsToOrder) {
        ArrayList<Product> products = new ArrayList<>();

        for (String productId : productIdsToOrder) {
            Optional<Product> optionalProduct = productDb.getProductById(productId);
            if (optionalProduct.isPresent()) {
                products.add(optionalProduct.get());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + productId + " not found");
            }
        }
        String uuid = UUID.randomUUID().toString();
        Order newOrder = new Order(uuid, products);
        orderDb.addOrder(newOrder);
        return newOrder;
    }
}
