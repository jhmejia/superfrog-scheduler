package edu.tcu.cs.superfrogscheduler.superfrogrequest.dto;

import edu.tcu.cs.superfrogscheduler.domain.EventType;
import edu.tcu.cs.superfrogscheduler.domain.RequestStatus;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalTime;

public record SuperFrogAppearanceRequestDto(
        Integer requestId,

        @NotEmpty(message = "Event Type is required") EventType eventType,
        @NotEmpty(message = "Address is required") String address,

        @NotEmpty(message = "Mileage is required") Double mileage,

        @NotEmpty(message = "Event Date is required") LocalDate eventDate,

        @NotEmpty(message = "Start Time is required") LocalTime startTime,

        @NotEmpty(message = "End Time is required") LocalTime endTime,
        RequestStatus status,
        SuperFrogStudent student) {
}
