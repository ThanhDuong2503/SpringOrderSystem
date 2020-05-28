package de.neuefische.springordersystem.Controller;

import de.neuefische.springordersystem.controller.OrderController;
import de.neuefische.springordersystem.controller.ProductController;
import de.neuefische.springordersystem.db.OrderDb;
import de.neuefische.springordersystem.model.Order;
import de.neuefische.springordersystem.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class OrderControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // resettet alle Daten vor jedem Test, damit sie sich nicht gegenseitig beeinflussen
    @Autowired
    private OrderDb orderDb;
    @BeforeEach
    public void resetData(){
        orderDb.clearDb();
    }

    @Test
    public void listOrdersShouldReturnAllOrders() {

        // GIVEN

        // WHEN
        ResponseEntity<OrderController[]> response = restTemplate.getForEntity("http://localhost:" + port + "/orders", OrderController[].class);
        HttpStatus statusCode = response.getStatusCode();
        OrderController[] orderControllers = response.getBody();

        // THEN
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(0, orderControllers.length);
    }

    @Test
    public void orderProductsShouldReturnOrderedProducts() {
        // POST Process ; platziert order
        ArrayList<String> productId = new ArrayList<>();
        ResponseEntity<Order>postResponse = restTemplate.postForEntity("http://localhost:" + port + "/orders", productId, Order.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        // WHEN Get Process; ruft geordete Order auf
        ResponseEntity<Order[]> response = restTemplate.getForEntity("http://localhost:" + port + "/orders", Order[].class);
        HttpStatus statusCode = response.getStatusCode();
        Order[] orders = response.getBody();

        // THEN
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(1,orders.length);
    }

    @Test
    public void listProductsShouldReturnAllProducts() {

        // GIVEN

        // WHEN
        ResponseEntity<Product[]> response = restTemplate.getForEntity("http://localhost:" + port + "/products", Product[].class);
        HttpStatus statusCode = response.getStatusCode();
        Product[] products = response.getBody();

        // THEN
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(5, products.length);
    }
}