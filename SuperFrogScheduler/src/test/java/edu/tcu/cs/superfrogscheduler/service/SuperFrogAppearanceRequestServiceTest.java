package edu.tcu.cs.superfrogscheduler.service;

import edu.tcu.cs.superfrogscheduler.domain.EventType;
import edu.tcu.cs.superfrogscheduler.domain.RequestStatus;
import edu.tcu.cs.superfrogscheduler.domain.SuperFrogAppearanceRequest;
import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.repository.SuperFrogAppearanceRequestRepository;
import edu.tcu.cs.superfrogscheduler.repository.SuperFrogStudentRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SuperFrogAppearanceRequestServiceTest {
    @Mock
    SuperFrogAppearanceRequestRepository superFrogAppearanceRequestRepository;

    @Mock
    SuperFrogStudentRepository superFrogStudentRepository;

    @InjectMocks
    SuperFrogAppearanceRequestService superFrogAppearanceRequestService;

    List<SuperFrogAppearanceRequest> superFrogAppearanceRequests;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }


    @Test
    void testFindByIdSuccess() {
        // Given. Arrange inputs and targets. Define the behavior of Mock object superFrogStudentRepository

        SuperFrogStudent student1 = new SuperFrogStudent();
        student1.setId(1001);
        student1.setFirstName("Jane");
        student1.setLastName("Smith");
        SuperFrogAppearanceRequest request1 = new SuperFrogAppearanceRequest(
                5,
                EventType.TCU,
                "Event address 1",
                1.4,
                LocalDate.of(2023, 4, 6),
                LocalTime.of(13, 0),
                LocalTime.of(15, 30),
                RequestStatus.COMPLETED,
                student1);

        given(superFrogAppearanceRequestRepository.findById(5)).willReturn(Optional.of(request1));

        // When. Act on the target behavior. When steps should cover the method to be tested.
        SuperFrogAppearanceRequest returnedSuperFrogAppearance = superFrogAppearanceRequestService.findById(5);

        // Then. Assert expected outcomes.
        assertThat(returnedSuperFrogAppearance.getRequestId()).isEqualTo(request1.getRequestId());
        assertThat(returnedSuperFrogAppearance.getEventType()).isEqualTo(request1.getEventType());
        assertThat(returnedSuperFrogAppearance.getAddress()).isEqualTo(request1.getAddress());
        assertThat(returnedSuperFrogAppearance.getMileage()).isEqualTo(request1.getMileage());
        assertThat(returnedSuperFrogAppearance.getEventDate()).isEqualTo(request1.getEventDate());
        assertThat(returnedSuperFrogAppearance.getStartTime()).isEqualTo(request1.getStartTime());
        assertThat(returnedSuperFrogAppearance.getEndTime()).isEqualTo(request1.getEndTime());
        assertThat(returnedSuperFrogAppearance.getStatus()).isEqualTo(request1.getStatus());
        assertThat(returnedSuperFrogAppearance.getStudent()).isEqualTo(request1.getStudent());
        verify(superFrogAppearanceRequestRepository, times(1)).findById(5);
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        given(superFrogAppearanceRequestRepository.findById(Mockito.any(Integer.class))).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(()-> {
            SuperFrogAppearanceRequest returnedSuperFrogAppearance = superFrogAppearanceRequestService.findById(5);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find superfrogappearancerequest with Id 5 :(");
        verify(superFrogAppearanceRequestRepository, times(1)).findById(5);

    }
}
