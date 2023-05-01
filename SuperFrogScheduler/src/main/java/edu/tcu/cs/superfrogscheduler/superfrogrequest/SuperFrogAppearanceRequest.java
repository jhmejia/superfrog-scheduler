package edu.tcu.cs.superfrogscheduler.superfrogrequest;

import edu.tcu.cs.superfrogscheduler.domain.EventType;
import edu.tcu.cs.superfrogscheduler.superfrogstudent.SuperFrogStudent;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A customer can request a SuperFrog appearance by submitting a SuperFrogAppearanceRequest.
 * A SuperFrogAppearanceRequest includes event-related information.
 */
@Entity
public class SuperFrogAppearanceRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer requestId;

    private EventType eventType;

    private String address;

    private Double mileage;

    private LocalDate eventDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private RequestStatus status;

    private String contactFirstName;

    private String contactLastName;

    private String phoneNumber;

    private String email;

    private String title;

    private String nameOfOrg;

    private String description;

    private String specialInstructions;

    private String outsideOrgs;

    private String expenses;

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameOfOrg() {
        return nameOfOrg;
    }

    public void setNameOfOrg(String nameOfOrg) {
        this.nameOfOrg = nameOfOrg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getOutsideOrgs() {
        return outsideOrgs;
    }

    public void setOutsideOrgs(String outsideOrgs) {
        this.outsideOrgs = outsideOrgs;
    }

    public String getExpenses() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses = expenses;
    }

    @ManyToOne
    private SuperFrogStudent student;


    public SuperFrogAppearanceRequest() {
    }

    public SuperFrogAppearanceRequest(Integer requestId, EventType eventType, String address, Double mileage, LocalDate eventDate, LocalTime startTime, LocalTime endTime, RequestStatus status, String contactFirstName, String contactLastName, String phoneNumber, String email, String title, String nameOfOrg, String description, String specialInstructions, String outsideOrgs, String expenses, SuperFrogStudent student) {
        this.requestId = requestId;
        this.eventType = eventType;
        this.address = address;
        this.mileage = mileage;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.title = title;
        this.nameOfOrg = nameOfOrg;
        this.description = description;
        this.specialInstructions = specialInstructions;
        this.outsideOrgs = outsideOrgs;
        this.expenses = expenses;
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
