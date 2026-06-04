package org.cms.com.controllers;

import lombok.RequiredArgsConstructor;
import org.cms.com.models.dto.PaymentDto;
import org.cms.com.services.IPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class PaymentController {

    private final IPaymentService paymentService;

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<PaymentDto> getByConferenceId(@PathVariable Long conferenceId) {
        PaymentDto payment = paymentService.getPaymentByConferenceId(conferenceId);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/conference/{conferenceId}")
    public ResponseEntity<PaymentDto> createOrUpdate(@PathVariable Long conferenceId, @RequestBody PaymentDto paymentDto) {
        PaymentDto updated = paymentService.createOrUpdatePayment(conferenceId, paymentDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/conference/{conferenceId}")
    public ResponseEntity<Void> deleteByConferenceId(@PathVariable Long conferenceId) {
        paymentService.deletePaymentByConferenceId(conferenceId);
        return ResponseEntity.noContent().build();
    }
}

