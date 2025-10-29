package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.dto.OrderDTO;
import org.genc.app.SneakoAplication.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO findOrderById(Long id);
    public Page<OrderDTO> getOrders(Pageable pageable);
    OrderDTO updateOrderStatus(Long orderId, String newStatus);


}