package edu.tcu.cs.superfrogscheduler.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A customer can request a SuperFrog appearance by submitting a SuperFrogAppearanceRequest.
 * A SuperFrogAppearanceRequest includes event-related information.
 */
@Entity
public class SuperFrogAppearanceRequest {

    @Id
    private Integer requestId;

    private EventType eventType;

    private String address;

    private Double mileage;

    private LocalDate eventDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private RequestStatus status;

    @ManyToOne
    private SuperFrogStudent student;


    public SuperFrogAppearanceRequest() {
    }

    public SuperFrogAppearanceRequest(Integer requestId, EventType eventType, String address, Double mileage, LocalDate eventDate, LocalTime startTime, LocalTime endTime, RequestStatus status, SuperFrogStudent student) {
        this.requestId = requestId;
        this.eventType = eventType;
        this.address = address;
        this.mileage = mileage;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.student = student;
    }

    /**
     * This method returns the extra mileage over the provided freeMileage.
     * If the distance is no greater than the provided freeMileage, the method returns 0.0.
     * Otherwise, it returns the extra mileage.
     *
     * @param freeMileage for example, the current free mileage is 2 miles.
     * @return extra mileage
     */
    public Double getMileageOver(Double freeMileage) {
        return this.mileage.compareTo(freeMileage) <= 0 ? 0.0 : this.mileage - freeMileage;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Double getMileage() {
        return mileage;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public SuperFrogStudent getStudent() {
        return student;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setStudent(SuperFrogStudent student) {
        this.student = student;
    }
}
