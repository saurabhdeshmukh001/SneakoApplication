package org.genc.app.SneakoAplication.service.api;

import org.genc.app.SneakoAplication.domain.entity.PaymentDTO;
import org.genc.app.SneakoAplication.dto.CategoryDTO;

public interface PaymentService {
    public PaymentDTO createPayment(PaymentDTO paymentDTO);
}
