package edu.tcu.cs.superfrogscheduler.superfrog;

import edu.tcu.cs.superfrogscheduler.system.ObjectNotFoundException;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SuperfrogServiceTest {

    @Mock
    SuperfrogRepository superfrogRepository;

    @InjectMocks
    SuperfrogService superfrogService;



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {
        // Given. Arrange inputs and targets. Define the behavior of Mock object superfrogRepository
        /*
          "id": 0,
          "username": "",
          "firstName": "string",
          "lastName": "string",
          "email": "string",
          "password": "string",
          "active": true
         */
        Superfrog s = new Superfrog();
        s.setId(0);
        s.setUsername("johnnguyen");
        s.setFirstName("John");
        s.setLastName("Nguyen");
        s.setEmail("john.nguyen@tcu.edu");
        s.setPassword("1234567890");
        s.setActive(true);

        given(superfrogRepository.findById(0)).willReturn(Optional.of(s)); // Defines the behavior of mock object

        // When. Act on the target behavior. When steps should cover the method to be tested.
        Superfrog returnedSuperfrog = superfrogService.findById(0);

        // Then. Assert expected outcomes.
        assertThat(returnedSuperfrog.getId()).isEqualTo(s.getId());
        assertThat(returnedSuperfrog.getUsername()).isEqualTo(s.getUsername());
        assertThat(returnedSuperfrog.getFirstName()).isEqualTo(s.getFirstName());
        assertThat(returnedSuperfrog.getLastName()).isEqualTo(s.getLastName());
        assertThat(returnedSuperfrog.getEmail()).isEqualTo(s.getEmail());
        assertThat(returnedSuperfrog.getPassword()).isEqualTo(s.getPassword());
        assertThat(returnedSuperfrog.isActive()).isEqualTo(s.isActive());
        verify(superfrogRepository, times(1)).findById(0);
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        given(superfrogRepository.findById(Mockito.any(Integer.class))).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(()-> {
            Superfrog returnedSuperfrog = superfrogService.findById(0);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find superfrog with Id 0 :(");
        verify(superfrogRepository, times(1)).findById(0);

    }
}