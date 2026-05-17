package org.cms.com.repositories;

import org.cms.com.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByConference_Id(Long conferenceId);
    void deleteByConference_Id(Long conferenceId);
}

