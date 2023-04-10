package edu.tcu.cs.superfrogscheduler.repository;

import edu.tcu.cs.superfrogscheduler.domain.PaymentForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentFormRepository extends JpaRepository<PaymentForm, Integer> {
}
