package Orderservice.com.Controllers;

import Orderservice.com.Models.Orders;
import Orderservice.com.Services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderControllers orderControllers;

    @BeforeEach
    void setUp() {
        // Initialize mocks and setup test data if needed
    }

    @Test
    void testGetAllOrders() {
        // Prepare test data
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        List<Orders> expectedOrders = Arrays.asList(order1, order2);

        // Mock the service method
        when(orderService.getAllOrders()).thenReturn(expectedOrders);

        // Call the controller method
        ResponseEntity<List<Orders>> responseEntity = orderControllers.getAllOrders();

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedOrders, responseEntity.getBody());
    }
    @Test
    void testCreateOrder() {
        // Prepare test data
        Orders order = new Orders();
        order.setId(1L);
        order.setCustomerName("John Doe");
        order.setAddress("123 Main St");
        order.setTotalAmount(99.99);

        // Mock the service method
        when(orderService.createOrder(order)).thenReturn(order);

        // Call the controller method
        ResponseEntity<Orders> responseEntity = orderControllers.createOrder(order);

        // Verify the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(order, responseEntity.getBody());
    }


    @Test
    void testGetOrderById() {
        // Prepare test data
        Long orderId = 1L;
        Orders expectedOrder = new Orders();
        expectedOrder.setId(orderId);

        // Mock the service method
        when(orderService.getOrderById(orderId)).thenReturn(Optional.of(expectedOrder));

        // Call the controller method
        ResponseEntity<Orders> responseEntity = orderControllers.getOrderById(orderId);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedOrder, responseEntity.getBody());
    }

}
