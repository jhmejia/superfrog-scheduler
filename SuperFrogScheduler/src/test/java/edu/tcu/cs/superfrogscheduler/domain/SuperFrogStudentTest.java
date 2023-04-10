package edu.tcu.cs.superfrogscheduler.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SuperFrogStudentTest {

    @Test
    public void should_generate_payment_form_for_one_SuperFrog_student_with_different_event_types() {
        // Given
        SuperFrogStudent student = new SuperFrogStudent("Jane", "Smith", 1001); // First name, last name, and Id

        List<SuperFrogAppearanceRequest> requests = List.of(
                new SuperFrogAppearanceRequest(
                        5,                                       // The id of the request
                        EventType.TCU,                                   // The type of the event
                        "Event address 1",                               // Physical address of the event
                        1.4,                                             // Distance between TCU and the event address
                        LocalDate.of(2023, 4, 6),   // Event's date
                        LocalTime.of(13, 0),                  // Event's start time
                        LocalTime.of(15, 30),                 // Event's end time
                        RequestStatus.COMPLETED,                          // Event status
                        student),                                        // The SuperFrog Student who signed up for the event
                new SuperFrogAppearanceRequest(
                        6,
                        EventType.NONPROFIT,
                        "Event address 2",
                        2.0,
                        LocalDate.of(2023, 4, 9),
                        LocalTime.of(9, 0),
                        LocalTime.of(12, 0),
                        RequestStatus.COMPLETED,
                        student),
                new SuperFrogAppearanceRequest(
                        12,
                        EventType.PRIVATE,
                        "Event address 3",
                        99.0,
                        LocalDate.of(2023, 4, 16),
                        LocalTime.of(19, 30),
                        LocalTime.of(21, 30),
                        RequestStatus.COMPLETED,
                        student)
        );

        Period paymentPeriod = new Period(LocalDate.of(2023, 4, 1),
                LocalDate.of(2023, 4, 30));

        // When
        PaymentForm paymentForm = student.generatePaymentForm(requests, paymentPeriod);

        // Then
        assertThat(paymentForm).isNotNull();
        assertThat(paymentForm.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(paymentForm.getLastName()).isEqualTo(student.getLastName());
        assertThat(paymentForm.getStudentId()).isEqualTo(student.getId());
        assertThat(paymentForm.getPaymentPeriod().getBeginDate()).isEqualTo(paymentPeriod.getBeginDate());
        assertThat(paymentForm.getPaymentPeriod().getEndDate()).isEqualTo(paymentPeriod.getEndDate());

        /**
         * First event (TCU related event):                   2.5 hrs * $100/hr = $250
         * Second event (Public Schools or Non-Profit event): 3 hrs * $100/hr = $300
         * Third event (Private or Residential event):        2 hrs * $175/hr = $350
         *
         * Some events may be far away. The first 2 miles are free of charge, then $0.75/mile.
         * In this test case, the first two events are close by campus, that is, <= 2 miles.
         * So we only need to consider the third event:
         * Transportation fee: (99 - 2) * $0.75/mile = $72.75
         *
         * Total: $972.75
         */
        assertThat(paymentForm.getAmount()).isEqualByComparingTo(new BigDecimal(972.75));
    }

    @Test
    public void should_generate_payment_form_for_one_SuperFrog_student_with_same_event_types() {
        // Given
        SuperFrogStudent student = new SuperFrogStudent("Jane", "Smith", 1001); // First name, last name, and ID

        List<SuperFrogAppearanceRequest> requests = List.of(
                new SuperFrogAppearanceRequest(
                        5,
                        EventType.PRIVATE,
                        "Event address 1",
                        50.0,
                        LocalDate.of(2023, 4, 6),
                        LocalTime.of(13, 0),
                        LocalTime.of(15, 30),
                        RequestStatus.COMPLETED,
                        student),
                new SuperFrogAppearanceRequest(
                        6,
                        EventType.PRIVATE,
                        "Event address 2",
                        50.0,
                        LocalDate.of(2023, 4, 9),
                        LocalTime.of(9, 0),
                        LocalTime.of(12, 0),
                        RequestStatus.COMPLETED,
                        student),
                new SuperFrogAppearanceRequest(
                        12,
                        EventType.PRIVATE,
                        "Event address 3",
                        50.0,
                        LocalDate.of(2023, 4, 16),
                        LocalTime.of(19, 30),
                        LocalTime.of(21, 30),
                        RequestStatus.COMPLETED,
                        student)
        );

        Period paymentPeriod = new Period(LocalDate.of(2023, 4, 1),
                LocalDate.of(2023, 4, 30));

        // When
        PaymentForm paymentForm = student.generatePaymentForm(requests, paymentPeriod);

        // Then
        assertThat(paymentForm).isNotNull();
        assertThat(paymentForm.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(paymentForm.getLastName()).isEqualTo(student.getLastName());
        assertThat(paymentForm.getStudentId()).isEqualTo(student.getId());
        assertThat(paymentForm.getPaymentPeriod().getBeginDate()).isEqualTo(paymentPeriod.getBeginDate());
        assertThat(paymentForm.getPaymentPeriod().getEndDate()).isEqualTo(paymentPeriod.getEndDate());
        assertThat(paymentForm.getAmount()).isEqualByComparingTo(new BigDecimal(1420.5));
    }

    @Test
    public void should_generate_payment_form_for_one_SuperFrog_student_with_zero_requests() {
        // Given
        SuperFrogStudent student = new SuperFrogStudent("Jane", "Smith", 1001); // First name, last name, and ID

        List<SuperFrogAppearanceRequest> requests = List.of();

        Period paymentPeriod = new Period(LocalDate.of(2023, 4, 1),
                LocalDate.of(2023, 4, 30));

        // When
        PaymentForm paymentForm = student.generatePaymentForm(requests, paymentPeriod);

        // Then
        assertThat(paymentForm).isNotNull();
        assertThat(paymentForm.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(paymentForm.getLastName()).isEqualTo(student.getLastName());
        assertThat(paymentForm.getStudentId()).isEqualTo(student.getId());
        assertThat(paymentForm.getPaymentPeriod().getBeginDate()).isEqualTo(paymentPeriod.getBeginDate());
        assertThat(paymentForm.getPaymentPeriod().getEndDate()).isEqualTo(paymentPeriod.getEndDate());
        assertThat(paymentForm.getAmount()).isEqualByComparingTo(new BigDecimal(0));
    }

    @Test
    public void should_generate_payment_form_for_one_SuperFrog_student_no_transportation_fee() {
        // Given
        SuperFrogStudent student = new SuperFrogStudent("Jane", "Smith", 1001); // First name, last name, and ID

        List<SuperFrogAppearanceRequest> requests = List.of(
                new SuperFrogAppearanceRequest(
                        5,
                        EventType.TCU,
                        "Event address 1",
                        2.0,
                        LocalDate.of(2023, 4, 6),
                        LocalTime.of(13, 0),
                        LocalTime.of(15, 30),
                        RequestStatus.COMPLETED,
                        student),
                new SuperFrogAppearanceRequest(
                        6,
                        EventType.NONPROFIT,
                        "Event address 2",
                        0.9,
                        LocalDate.of(2023, 4, 9),
                        LocalTime.of(9, 0),
                        LocalTime.of(12, 0),
                        RequestStatus.COMPLETED,
                        student),
                new SuperFrogAppearanceRequest(
                        12,
                        EventType.PRIVATE,
                        "Event address 3",
                        1.5,
                        LocalDate.of(2023, 4, 16),
                        LocalTime.of(19, 30),
                        LocalTime.of(21, 30),
                        RequestStatus.COMPLETED,
                        student)
        );

        Period paymentPeriod = new Period(LocalDate.of(2023, 4, 1),
                LocalDate.of(2023, 4, 30));

        // When
        PaymentForm paymentForm = student.generatePaymentForm(requests, paymentPeriod);

        // Then
        assertThat(paymentForm).isNotNull();
        assertThat(paymentForm.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(paymentForm.getLastName()).isEqualTo(student.getLastName());
        assertThat(paymentForm.getStudentId()).isEqualTo(student.getId());
        assertThat(paymentForm.getPaymentPeriod().getBeginDate()).isEqualTo(paymentPeriod.getBeginDate());
        assertThat(paymentForm.getPaymentPeriod().getEndDate()).isEqualTo(paymentPeriod.getEndDate());
        assertThat(paymentForm.getAmount()).isEqualByComparingTo(new BigDecimal(900));
    }

}
