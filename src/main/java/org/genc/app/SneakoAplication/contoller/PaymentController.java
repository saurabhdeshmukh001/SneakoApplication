package org.genc.app.SneakoAplication.contoller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.genc.app.SneakoAplication.domain.entity.PaymentDTO;
import org.genc.app.SneakoAplication.dto.CategoryDTO;
import org.genc.app.SneakoAplication.service.api.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;
    @PostMapping
    public ResponseEntity<PaymentDTO> createCategory(@Valid @RequestBody PaymentDTO paymentDTO){
        return  new ResponseEntity<>(paymentService.createPayment(paymentDTO), HttpStatus.CREATED);
    }
}
