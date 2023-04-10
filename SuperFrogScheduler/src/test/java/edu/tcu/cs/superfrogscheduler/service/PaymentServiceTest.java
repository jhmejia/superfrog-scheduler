package edu.tcu.cs.superfrogscheduler.service;

import edu.tcu.cs.superfrogschedulertdd.domain.*;
import edu.tcu.cs.superfrogschedulertdd.repository.PaymentFormRepository;
import edu.tcu.cs.superfrogschedulertdd.repository.SuperFrogAppearanceRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

// For JUnit 5, need to use the @ExtendWith annotation.
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    SuperFrogAppearanceRequestRepository requestRepository;

    @Mock
    PaymentFormRepository paymentFormRepository;

    @InjectMocks
    PaymentService paymentService;


    @Test
    public void should_generate_payment_forms_for_SuperFrog_students() {
        // Given
        List<Integer> appearanceRequestIdList = List.of(5, 6, 12, 16, 17, 20, 22); // Assume the Spirit Director has selected 7 finished requests for April.

        SuperFrogStudent student1 = new SuperFrogStudent("Jane", "Smith", 1001); // First name, last name, and Id

        SuperFrogStudent student2 = new SuperFrogStudent("John", "Doe", 1004);

        SuperFrogStudent student3 = new SuperFrogStudent("Tim", "Johnson", 1012);

        List<SuperFrogAppearanceRequest> requests = List.of(
                new SuperFrogAppearanceRequest(
                        5,
                        EventType.TCU,                                   // The type of the event
                        "Event address 1",                               // Physical address of the event
                        1.4,                                             // Distance between TCU and the event address
                        LocalDate.of(2023, 4, 6),   // Event's date
                        LocalTime.of(13, 0),                  // Event's start time
                        LocalTime.of(15, 30),                 // Event's end time
                        RequestStatus.COMPLETED,                          // Event status
                        student1),                                       // The SuperFrog Student who signed up for the event
                new SuperFrogAppearanceRequest(
                        6,
                        EventType.NONPROFIT,
                        "Event address 2",
                        2.0,
                        LocalDate.of(2023, 4, 9),
                        LocalTime.of(9, 0),
                        LocalTime.of(12, 0),
                        RequestStatus.COMPLETED,
                        student1),
                new SuperFrogAppearanceRequest(
                        12,
                        EventType.PRIVATE,
                        "Event address 3",
                        99.0,
                        LocalDate.of(2023, 4, 16),
                        LocalTime.of(19, 30),
                        LocalTime.of(21, 30),
                        RequestStatus.COMPLETED,
                        student1),
                new SuperFrogAppearanceRequest(
                        16,
                        EventType.PRIVATE,
                        "Event address 4",
                        18.0,
                        LocalDate.of(2023, 4, 17),
                        LocalTime.of(11, 0),
                        LocalTime.of(12, 0),
                        RequestStatus.COMPLETED,
                        student2),
                new SuperFrogAppearanceRequest(
                        17,
                        EventType.NONPROFIT,
                        "Event address 5",
                        25.0,
                        LocalDate.of(2023, 4, 19),
                        LocalTime.of(14, 30),
                        LocalTime.of(15, 30),
                        RequestStatus.COMPLETED,
                        student2),
                new SuperFrogAppearanceRequest(
                        20,
                        EventType.PRIVATE,
                        "Event address 6",
                        50.0,
                        LocalDate.of(2023, 4, 22),
                        LocalTime.of(9, 30),
                        LocalTime.of(14, 30),
                        RequestStatus.COMPLETED,
                        student2),
                new SuperFrogAppearanceRequest(
                        22,
                        EventType.TCU,
                        "Event address 7",
                        0.6,
                        LocalDate.of(2023, 4, 26),
                        LocalTime.of(17, 0),
                        LocalTime.of(19, 0),
                        RequestStatus.COMPLETED,
                        student3)
        );

        given(this.requestRepository.findByRequestIdIn(appearanceRequestIdList)).willReturn(requests);

        given(this.paymentFormRepository.saveAll(anyList())).will(returnsFirstArg()); // Return the input argument.

        Period paymentPeriod = new Period(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 30));

        // When
        List<PaymentForm> paymentForms = this.paymentService.generatePaymentForms(appearanceRequestIdList, paymentPeriod);

        // Then
        assertThat(paymentForms).isNotNull();
        assertThat(paymentForms.size()).isEqualTo(3);
        verify(this.requestRepository, times(1)).findByRequestIdIn(appearanceRequestIdList);
        verify(this.paymentFormRepository, times(1)).saveAll(Mockito.any(List.class));
    }

    @Test
    public void should_generate_payment_forms_for_SuperFrog_students_with_empty_request_list() {
        // Given
        List<Integer> appearanceRequestIdList = List.of(); // Assume the Spirit Director has selected 0 completed requests for April.

        List<SuperFrogAppearanceRequest> requests = List.of();

        given(this.requestRepository.findByRequestIdIn(appearanceRequestIdList)).willReturn(requests);

        given(this.paymentFormRepository.saveAll(anyList())).will(returnsFirstArg()); // Return the input argument.

        Period paymentPeriod = new Period(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 30));

        // When
        List<PaymentForm> paymentForms = this.paymentService.generatePaymentForms(appearanceRequestIdList, paymentPeriod);

        // Then
        assertThat(paymentForms).isNotNull();
        assertThat(paymentForms.size()).isEqualTo(0);
        verify(this.requestRepository, times(1)).findByRequestIdIn(appearanceRequestIdList);
        verify(this.paymentFormRepository, times(1)).saveAll(Mockito.any(List.class));
    }

    @Test
    public void should_generate_payment_forms_for_SuperFrog_students_with_only_one_request() {
        // Given
        List<Integer> appearanceRequestIdList = List.of(5); // Assume the Spirit Director has selected only 1 completed request for April.

        SuperFrogStudent student1 = new SuperFrogStudent("Jane", "Smith", 1001); // First name, last name, and ID

        List<SuperFrogAppearanceRequest> requests = List.of(
                new SuperFrogAppearanceRequest(
                        5,
                        EventType.TCU,                                   // The type of the event
                        "Event address 1",                               // Physical address of the event
                        1.4,                                             // Distance between TCU and the event address
                        LocalDate.of(2023, 4, 6),   // Event's date
                        LocalTime.of(13, 0),                  // Event's start time
                        LocalTime.of(15, 30),                 // Event's end time
                        RequestStatus.COMPLETED,                          // Event status
                        student1)                                        // The SuperFrog Student who signed up for the event
        );

        given(this.requestRepository.findByRequestIdIn(appearanceRequestIdList)).willReturn(requests);

        given(this.paymentFormRepository.saveAll(anyList())).will(returnsFirstArg()); // Return the input argument.

        Period paymentPeriod = new Period(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 30));

        // When
        List<PaymentForm> paymentForms = this.paymentService.generatePaymentForms(appearanceRequestIdList, paymentPeriod);

        // Then
        assertThat(paymentForms).isNotNull();
        assertThat(paymentForms.size()).isEqualTo(1);
        verify(this.requestRepository, times(1)).findByRequestIdIn(appearanceRequestIdList);
        verify(this.paymentFormRepository, times(1)).saveAll(Mockito.any(List.class));
    }

}
