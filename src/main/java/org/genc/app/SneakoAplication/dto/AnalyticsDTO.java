package org.genc.app.SneakoAplication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyticsDTO {


    Long totalRevenue;

    Long totalOrders;

    Long totalCustomers;

    Long totalProducts;
}

