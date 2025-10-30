package org.genc.app.SneakoAplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateOrderDTO {
    private Long orderId;
    private Long userId;
    private String shippingAddress;
    private BigDecimal totalPrice;
    private String orderStatus;
    private LocalDateTime orderDate;
}
