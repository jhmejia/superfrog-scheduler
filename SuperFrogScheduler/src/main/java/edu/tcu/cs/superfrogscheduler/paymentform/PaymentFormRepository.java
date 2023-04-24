package edu.tcu.cs.superfrogscheduler.paymentform;

import edu.tcu.cs.superfrogscheduler.paymentform.PaymentForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentFormRepository extends JpaRepository<PaymentForm, Integer> {
}
