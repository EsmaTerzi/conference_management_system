package org.cms.com.services.impl;

import lombok.RequiredArgsConstructor;
import org.cms.com.domain.Conference;
import org.cms.com.domain.Payment;
import org.cms.com.models.dto.PaymentDto;
import org.cms.com.repositories.ConferenceRepository;
import org.cms.com.repositories.PaymentRepository;
import org.cms.com.services.IPaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final ConferenceRepository conferenceRepository;

    @Override
    public PaymentDto getPaymentByConferenceId(Long conferenceId) {
        List<Payment> payments = paymentRepository.findByConference_Id(conferenceId);
        if (payments != null && !payments.isEmpty()) {
            return toDto(payments.get(0));
        }
        return null;
    }

    @Override
    @Transactional
    public PaymentDto createOrUpdatePayment(Long conferenceId, PaymentDto paymentDto) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        paymentRepository.deleteByConference_Id(conferenceId);

        Payment payment = new Payment();
        payment.setBankName(paymentDto.getBankName());
        payment.setBankNameOptional(paymentDto.getBankNameOptional());
        payment.setIban(paymentDto.getIban());
        payment.setIbanOptional(paymentDto.getIbanOptional());
        payment.setDocumentEmail(paymentDto.getDocumentEmail());
        payment.setTextArea(paymentDto.getTextArea());
        payment.setPaymentFeeText(paymentDto.getPaymentFeeText());
        payment.setConference(conference);

        Payment saved = paymentRepository.save(payment);
        return toDto(saved);
    }

    @Override
    @Transactional
    public void deletePaymentByConferenceId(Long conferenceId) {
        paymentRepository.deleteByConference_Id(conferenceId);
    }

    private PaymentDto toDto(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setBankName(payment.getBankName());
        dto.setBankNameOptional(payment.getBankNameOptional());
        dto.setIban(payment.getIban());
        dto.setIbanOptional(payment.getIbanOptional());
        dto.setDocumentEmail(payment.getDocumentEmail());
        dto.setTextArea(payment.getTextArea());
        dto.setPaymentFeeText(payment.getPaymentFeeText());
        return dto;
    }
}

