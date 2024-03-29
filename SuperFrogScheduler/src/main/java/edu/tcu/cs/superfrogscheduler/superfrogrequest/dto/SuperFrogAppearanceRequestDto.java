package edu.tcu.cs.superfrogscheduler.superfrogrequest.dto;

import edu.tcu.cs.superfrogscheduler.domain.EventType;
import edu.tcu.cs.superfrogscheduler.superfrogrequest.RequestStatus;

import edu.tcu.cs.superfrogscheduler.superfrogstudent.dto.SuperFrogStudentDto;

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

        String contactFirstName,

        String contactLastName,

        String phoneNumber,

        String email,

        String title,

        String nameOfOrg,

        String description,

        String specialInstructions,

        String outsideOrgs,

        String expenses,
        SuperFrogStudentDto student) {
}
