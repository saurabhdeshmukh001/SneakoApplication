package org.genc.app.SneakoAplication.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {

    @Nullable
    private Long productID;

    @NotNull(message = "Image URL cant be null")
    private String imageUrl;

    @NotBlank(message = "Enter the Product name")
    private String productName;

    @NotNull(message = "Enter the Product description")
    private String description;

    @NotNull(message = "Enter the appropriate price")
    private BigDecimal price;

    @NotNull(message = "Enter the Stock quantity")
    private Long stockQuantity;

    @NotNull
    private  String CategoryName;

}
