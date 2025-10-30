package org.genc.app.SneakoAplication.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.genc.app.SneakoAplication.domain.entity.Order;
import org.genc.app.SneakoAplication.domain.entity.OrderItem;
import org.genc.app.SneakoAplication.dto.OrderDTO;
import org.genc.app.SneakoAplication.dto.OrderItemDTO;
import org.genc.app.SneakoAplication.repo.OrderItemRepository;
import org.genc.app.SneakoAplication.repo.OrderRepository;
import org.genc.app.SneakoAplication.service.api.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        // 1️⃣ Save Order to generate ID
        Order order = Order.builder()
                .userId(orderDTO.getUserId())
                .shippingAddress(orderDTO.getShippingAddress())
                .orderStatus(orderDTO.getOrderStatus() != null ? orderDTO.getOrderStatus() : "PLACED")
                .orderDate(orderDTO.getOrderDate() != null ? orderDTO.getOrderDate() : LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        // 2️⃣ Validate and Save OrderItems
        if (orderDTO.getOrderItems() == null || orderDTO.getOrderItems().isEmpty()) {
            throw new RuntimeException("Order must contain at least one item.");
        }

        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(dto -> OrderItem.builder()
                        .order(savedOrder)
                        .productId(dto.getProductId())
                        .quantity(dto.getQuantity())
                        .unitPrice(dto.getUnitPrice())
                        .totalPrice(dto.getUnitPrice().multiply(BigDecimal.valueOf(dto.getQuantity())))
                        .size(dto.getSize())
                        .build())
                .collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);

        // 3️⃣ Recalculate order total
        BigDecimal orderTotal = orderItems.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        savedOrder.setTotalPrice(orderTotal);
        orderRepository.save(savedOrder);

        // 4️⃣ Reload items to get generated IDs
        List<OrderItem> savedItems = orderItemRepository.findByOrderOrderId(savedOrder.getOrderId());

        // 5️⃣ Construct DTO
        List<OrderItemDTO> itemDTOs = savedItems.stream()
                .map(item -> OrderItemDTO.builder()
                        .orderItemId(item.getOrderItemId())
                        .orderId(savedOrder.getOrderId())
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .totalPrice(item.getTotalPrice())
                        .size(item.getSize())
                        .build())
                .collect(Collectors.toList());

        return OrderDTO.builder()
                .orderId(savedOrder.getOrderId())
                .userId(savedOrder.getUserId())
                .shippingAddress(savedOrder.getShippingAddress())
                .orderStatus(savedOrder.getOrderStatus())
                .totalPrice(savedOrder.getTotalPrice())
                .orderDate(savedOrder.getOrderDate())
                .orderItems(itemDTOs)
                .build();
    }

    @Override
    public OrderDTO findOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream()
                .map(item -> OrderItemDTO.builder()
                        .orderItemId(item.getOrderItemId())
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .totalPrice(item.getTotalPrice())
                        .size(item.getSize())
                        .build())
                .collect(Collectors.toList());

        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .shippingAddress(order.getShippingAddress())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .orderDate(order.getOrderDate())
                .orderItems(itemDTOs)
                .build();
    }

    @Override
    public Page<OrderDTO> getOrders(Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return orderPage.map(this::mapProductEntityDTO);
    }

    public OrderDTO mapProductEntityDTO(Order orderObj) {
        List<OrderItemDTO> itemDTOs = orderObj.getOrderItems().stream()
                .map(item -> OrderItemDTO.builder()
                        .orderItemId(item.getOrderItemId())
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .totalPrice(item.getTotalPrice())
                        .size(item.getSize())
                        .build())
                .toList();

        return OrderDTO.builder()
                .orderId(orderObj.getOrderId())
                .userId(orderObj.getUserId())
                .shippingAddress(orderObj.getShippingAddress())
                .orderStatus(orderObj.getOrderStatus())
                .totalPrice(orderObj.getTotalPrice())
                .orderDate(orderObj.getOrderDate())
                .orderItems(itemDTOs)
                .build();
    }

    @Override
    @Transactional
    public OrderDTO updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);

        List<OrderItemDTO> itemDTOs = updatedOrder.getOrderItems().stream()
                .map(item -> OrderItemDTO.builder()
                        .orderItemId(item.getOrderItemId())
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .totalPrice(item.getTotalPrice())
                        .size(item.getSize())
                        .build())
                .toList();

        return OrderDTO.builder()
                .orderId(updatedOrder.getOrderId())
                .userId(updatedOrder.getUserId())
                .shippingAddress(updatedOrder.getShippingAddress())
                .orderStatus(updatedOrder.getOrderStatus())
                .totalPrice(updatedOrder.getTotalPrice())
                .orderDate(updatedOrder.getOrderDate())
                .orderItems(itemDTOs)
                .build();
    }

    @Override
    public Long calculateTotalRevenue() {
        List<Order> allOrders = orderRepository.findAll();

        BigDecimal totalRevenue = allOrders.stream()
                .map(Order::getTotalPrice)
                .filter(price -> price != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalRevenue.longValue();
    }

    @Override
    public Long totalOrders() {
        return orderRepository.count();
    }



}
