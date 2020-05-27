package de.neuefische.springordersystem.db;

import de.neuefische.springordersystem.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDb {

    public OrderDb() {
    }

    private final ArrayList<Order> orders = new ArrayList<>();

    public ArrayList<Order> listOrders() {
        return orders;
    }

    public void addOrder(Order newOrder) {
        this.orders.add(newOrder);
    }
}
