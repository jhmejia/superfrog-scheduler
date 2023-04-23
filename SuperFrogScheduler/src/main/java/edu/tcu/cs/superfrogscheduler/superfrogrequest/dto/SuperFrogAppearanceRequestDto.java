package edu.tcu.cs.superfrogscheduler.superfrogrequest.dto;

import edu.tcu.cs.superfrogscheduler.domain.EventType;
import edu.tcu.cs.superfrogscheduler.domain.RequestStatus;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.superfrog.dto.SuperFrogStudentDto;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalTime;

public record SuperFrogAppearanceRequestDto(
        Integer requestId,

        EventType eventType,
        String address,

        Double mileage,

        LocalDate eventDate,

        LocalTime startTime,

        LocalTime endTime,
        RequestStatus status,
        SuperFrogStudentDto student) {
}
