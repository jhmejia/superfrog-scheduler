package edu.tcu.cs.superfrogscheduler.superfrog;

import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SuperfrogServiceTest {

    @Mock
    SuperfrogRepository superfrogRepository;

    @InjectMocks
    SuperfrogService superfrogService;


    List<Superfrog> superfrogs;


    @BeforeEach
    void setUp() {
        Superfrog s1 = new Superfrog();
        s1.setId(1);
        s1.setUsername("superfrog1");
        s1.setFirstName("John");
        s1.setLastName("Mejyen");
        s1.setEmail("example@gmail.com");
        s1.setPassword("password");
        s1.setActive(true);

        Superfrog s2 = new Superfrog();
        s2.setId(2);
        s2.setUsername("superfrog2");
        s2.setFirstName("Joey");
        s2.setLastName("Quinn");
        s2.setEmail("iamacat@gmail.com");
        s2.setPassword("password2");
        s2.setActive(true);

        Superfrog s3 = new Superfrog();
        s3.setId(3);
        s3.setUsername("superfrog3");
        s3.setFirstName("Ethan");
        s3.setLastName("Edinboro");
        s3.setEmail("jo@gmail.com");
        s3.setPassword("password3");
        s3.setActive(false);

        this.superfrogs = new ArrayList<>();
        this.superfrogs.add(s1);
        this.superfrogs.add(s2);
        this.superfrogs.add(s3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {
        // Given. Arrange inputs and targets. Define the behavior of Mock object superfrogRepository
        /*
            "id": 1,
            "username": "superfrog1",
            "firstName": "John",
            "lastName": "Mejyen",
            "email": "example@gmail.com",
            "password": "password",
            "active": true
         */
        Superfrog s = new Superfrog();
        s.setId(1);
        s.setUsername("superfrog1");
        s.setFirstName("John");
        s.setLastName("Mejyen");
        s.setEmail("example@gmail.com");
        s.setPassword("password");
        s.setActive(true);

        given(superfrogRepository.findById(1)).willReturn(Optional.of(s)); // Defines the behavior of mock object

        // When. Act on the target behavior. When steps should cover the method to be tested.
        Superfrog returnedSuperfrog = superfrogService.findById(1);

        // Then. Assert expected outcomes.
        assertThat(returnedSuperfrog.getId()).isEqualTo(s.getId());
        assertThat(returnedSuperfrog.getUsername()).isEqualTo(s.getUsername());
        assertThat(returnedSuperfrog.getFirstName()).isEqualTo(s.getFirstName());
        assertThat(returnedSuperfrog.getLastName()).isEqualTo(s.getLastName());
        assertThat(returnedSuperfrog.getEmail()).isEqualTo(s.getEmail());
        assertThat(returnedSuperfrog.getPassword()).isEqualTo(s.getPassword());
        assertThat(returnedSuperfrog.isActive()).isEqualTo(s.isActive());
        verify(superfrogRepository, times(1)).findById(1);
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        given(superfrogRepository.findById(Mockito.any(Integer.class))).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(()-> {
            Superfrog returnedSuperfrog = superfrogService.findById(1);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find superfrog with Id 1 :(");
        verify(superfrogRepository, times(1)).findById(1);

    }

    @Test
    void testFindAllSuccess() {
        // Given
        given(superfrogRepository.findAll()).willReturn(this.superfrogs);

        // When
        List<Superfrog> actualSuperfrogs = superfrogService.findAll();

        // Then
        assertThat(actualSuperfrogs.size()).isEqualTo(this.superfrogs.size());
        verify(superfrogRepository, times(1)).findAll();
    }
}