package edu.tcu.cs.superfrogscheduler.superfrogstudent;

import edu.tcu.cs.superfrogscheduler.domain.*;
import edu.tcu.cs.superfrogscheduler.paymentform.PaymentForm;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.SuperFrogAppearanceRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A SuperFrogStudent is a TCU student who plays as the mascot. She is a part of the TCU Spirit Team.
 * She gets paid for each appearance in an event.
 */
@Entity
public class SuperFrogStudent implements Serializable {

    private String firstName;

    private String lastName;

    @Id
    private Integer id;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "student")
    private List<SuperFrogAppearanceRequest> requests = new ArrayList<>();



    public SuperFrogStudent() {
    }

    public SuperFrogStudent(String firstName, String lastName, Integer id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public PaymentForm generatePaymentForm(List<SuperFrogAppearanceRequest> requests, Period paymentPeriod) {
        /**
         * Group the given requests by their event type (TCU, NONPROFIT, and PRIVATE), then for each event type, calculate the number of hours
         * this SuperFrogStudent has worked. The result of this step is a Map<EventType, Double>.
         * For example:
         *  EventType.TCU -> 2.5 hrs
         *  EventType.NONPROFIT -> 3 hrs
         *  EventType.PRIVATE -> 2 hrs
         */
        Map<EventType, Double> eventTypeHoursMap = requests.stream().collect(Collectors.groupingBy(request -> request.getEventType(),
                Collectors.mapping(request -> request.getStartTime().until(request.getEndTime(), ChronoUnit.MINUTES) / 60.0,
                        Collectors.reducing(0.0, Double::sum))));

        BigDecimal totalAppearanceFee = new BigDecimal(0.0);

        // Calculate the total appearance fee by going over the map.
        for (Map.Entry<EventType, Double> entry : eventTypeHoursMap.entrySet()) {
            totalAppearanceFee = totalAppearanceFee
                    .add(BigDecimal.valueOf(entry.getKey().getHourlyRate())
                            .multiply(BigDecimal.valueOf(entry.getValue())));
        }

        // We also need to consider transportation fee.
        BigDecimal transportationFee = TransportationFeeCalculator.INSTANCE.calculateTransportationFee(requests);

        BigDecimal totalAmount = totalAppearanceFee.add(transportationFee);

        return new PaymentForm(this.firstName, this.lastName, this.id, paymentPeriod, totalAmount);
    }

    

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SuperFrogAppearanceRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<SuperFrogAppearanceRequest> requests) {
        this.requests = requests;
    }



    public void addAppearanceRequest(SuperFrogAppearanceRequest appearanceRequest) {
        appearanceRequest.setStudent(this);
        this.requests.add(appearanceRequest);
    }

    public void removeAllAppearanceRequests() {
        this.requests.stream().forEach(request -> request.setStudent(null));
        this.requests = null;
    }

    public void removeAppearanceRequest(SuperFrogAppearanceRequest requestToBeAssigned) {
        requestToBeAssigned.setStudent(null);
        this.requests.remove(requestToBeAssigned);
    }
}
