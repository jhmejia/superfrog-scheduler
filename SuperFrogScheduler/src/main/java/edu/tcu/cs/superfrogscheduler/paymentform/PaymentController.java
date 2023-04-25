package edu.tcu.cs.superfrogscheduler.paymentform;

import edu.tcu.cs.superfrogscheduler.paymentform.dto.RequestIds;
import edu.tcu.cs.superfrogscheduler.domain.Period;
import edu.tcu.cs.superfrogscheduler.system.Result;
import edu.tcu.cs.superfrogscheduler.system.HttpStatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    private PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/paymentform")
    public Result generatePaymentForms(@RequestBody RequestIds requestIds) {
        List<Integer> selectedIds = requestIds.getRequestIds();

        Period paymentPeriod = requestIds.getPaymentPeriod();

        List<PaymentForm> paymentForms = this.paymentService.generatePaymentForms(selectedIds, paymentPeriod);

        return new Result(true, HttpStatusCode.SUCCESS, "Payment forms are generated successfully.", paymentForms);
    }

}
