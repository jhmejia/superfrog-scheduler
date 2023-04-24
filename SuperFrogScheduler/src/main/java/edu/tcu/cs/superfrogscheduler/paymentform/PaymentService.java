package edu.tcu.cs.superfrogscheduler.paymentform;

import edu.tcu.cs.superfrogscheduler.domain.*;
import edu.tcu.cs.superfrogscheduler.paymentform.PaymentForm;
import edu.tcu.cs.superfrogscheduler.paymentform.PaymentFormRepository;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequestRepository;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private SuperFrogAppearanceRequestRepository requestRepository;

    private PaymentFormRepository paymentFormRepository;


    public PaymentService(SuperFrogAppearanceRequestRepository requestRepository, PaymentFormRepository paymentFormRepository) {
        this.requestRepository = requestRepository;
        this.paymentFormRepository = paymentFormRepository;
    }

    public List<PaymentForm> generatePaymentForms(List<Integer> appearanceRequestIdList, Period paymentPeriod) {
        List<SuperFrogAppearanceRequest> selectedRequests = this.requestRepository.findByRequestIdIn(appearanceRequestIdList);

        Map<SuperFrogStudent, List<SuperFrogAppearanceRequest>> studentRequestsMap = groupRequestsBySuperFrogStudent(selectedRequests);

        // For each SuperFrogStudent, generate a payment form, and then collect the payment forms into a list.
        List<PaymentForm> paymentForms = studentRequestsMap.entrySet().stream()
                .map(entry -> entry.getKey().generatePaymentForm(entry.getValue(), paymentPeriod))
                .collect(Collectors.toList());

        // Persist the generated payment forms and then return them.
        return this.paymentFormRepository.saveAll(paymentForms);
    }

    /**
     * Group the given requests by SuperFrogStudent who has finished this request.
     * The result is a Map<SuperFrogStudent, List<SuperFrogAppearanceRequest>>.
     * For example:
     *  Jane Smith -> request 5, request 6, request 12
     *  John Doe -> request 16, request 17, request 20
     *  Jane Smith -> request 22
     * @param selectedRequests A list of integer request ids.
     * @return A map that maps SuperFrogStudent to her requests
     */
    private Map<SuperFrogStudent, List<SuperFrogAppearanceRequest>> groupRequestsBySuperFrogStudent(List<SuperFrogAppearanceRequest> selectedRequests) {
        Map<SuperFrogStudent, List<SuperFrogAppearanceRequest>> studentRequestsMap = selectedRequests.stream()
                .collect(Collectors.groupingBy(SuperFrogAppearanceRequest::getStudent));
        return studentRequestsMap;
    }

}
