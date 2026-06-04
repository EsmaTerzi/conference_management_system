package org.cms.com.services;

import org.cms.com.models.dto.PaymentDto;

public interface IPaymentService {
    PaymentDto getPaymentByConferenceId(Long conferenceId);
    PaymentDto createOrUpdatePayment(Long conferenceId, PaymentDto paymentDto);
    void deletePaymentByConferenceId(Long conferenceId);
}

