package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.dto.OrderDTO;
import org.genc.app.SneakoAplication.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
   public OrderDTO createOrder(OrderDTO orderDTO);
  public   OrderDTO findOrderById(Long id);
    public Page<OrderDTO> getOrders(Pageable pageable);
    public OrderDTO updateOrderStatus(Long orderId, String newStatus);
    public Long calculateTotalRevenue();
    public Long totalOrders();



}