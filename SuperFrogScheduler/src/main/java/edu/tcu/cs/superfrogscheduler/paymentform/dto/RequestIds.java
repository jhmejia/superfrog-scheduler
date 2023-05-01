package edu.tcu.cs.superfrogscheduler.paymentform.dto;

import edu.tcu.cs.superfrogscheduler.domain.Period;

import java.util.List;

/**
 * A data transfer object used to encapsulate a list of selected request ids and the payment period.
 */
public class RequestIds {

    private List<Integer> requestIds;

    private Period paymentPeriod;


    public RequestIds() {

    }

    public RequestIds(List<Integer> requestIds, Period paymentPeriod) {
        this.requestIds = requestIds;
        this.paymentPeriod = paymentPeriod;
    }

    public List<Integer> getRequestIds() {
        return requestIds;
    }

    public void setRequestIds(List<Integer> requestIds) {
        this.requestIds = requestIds;
    }

    public Period getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(Period paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

}
