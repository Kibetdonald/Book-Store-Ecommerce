package Orderservice.com.Services;

import Orderservice.com.Models.Orders;
import Orderservice.com.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Orders> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Orders createOrder(Orders orders) {
        return orderRepository.save(orders);
    }
    public Orders updateOrder(Long id, Orders updatedOrder) {
        Optional<Orders> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            Orders order = existingOrder.get();
            order.setCustomerName(updatedOrder.getCustomerName());
            order.setAddress(updatedOrder.getAddress());
            order.setTotalAmount(updatedOrder.getTotalAmount());
            return orderRepository.save(order);
        }
        throw new IllegalArgumentException("Order not found with id: " + id);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

