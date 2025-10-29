package org.genc.app.SneakoAplication.domain.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    @Nullable
    private Long paymentId;

    private Long orderId;

    private String transactionId;

    private String paymentMethod;

    private String paymentDate;

}
