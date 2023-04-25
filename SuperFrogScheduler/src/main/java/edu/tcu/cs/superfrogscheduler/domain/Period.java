package edu.tcu.cs.superfrogscheduler.domain;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Period {

    private LocalDate beginDate;

    private LocalDate endDate;


    public Period() {
    }

    public Period(LocalDate beginDate, LocalDate endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
