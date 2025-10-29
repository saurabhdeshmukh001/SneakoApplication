package org.genc.app.SneakoAplication.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.genc.app.SneakoAplication.domain.entity.Category;
import org.genc.app.SneakoAplication.domain.entity.Payment;
import org.genc.app.SneakoAplication.domain.entity.PaymentDTO;
import org.genc.app.SneakoAplication.dto.CategoryDTO;
import org.genc.app.SneakoAplication.repo.PaymentRepository;
import org.genc.app.SneakoAplication.service.api.PaymentService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment paymentEntity=new Payment();
        paymentEntity.setPaymentId(paymentDTO.getPaymentId());
        paymentEntity.setOrderId(paymentDTO.getOrderId());
        paymentEntity.setPaymentDate(paymentDTO.getPaymentDate());
        paymentEntity.setPaymentMethod(paymentDTO.getPaymentMethod());
        paymentEntity.setTransactionId(paymentDTO.getTransactionId());

        Payment paymentObj=paymentRepository.save(paymentEntity);
        log.info("New Category creted with the :{}",paymentObj.getPaymentId());
        PaymentDTO responseDTO=new PaymentDTO(paymentObj.getPaymentId(),paymentObj.getOrderId(),
                paymentObj.getTransactionId(),paymentObj.getPaymentMethod(),paymentObj.getPaymentDate());
        return responseDTO;
    }
}
