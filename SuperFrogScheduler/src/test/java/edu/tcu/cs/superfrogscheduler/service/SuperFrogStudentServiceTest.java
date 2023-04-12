package edu.tcu.cs.superfrogscheduler.service;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.repository.SuperFrogStudentRepository;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import edu.tcu.cs.superfrogscheduler.utils.IdWorker;
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
    SuperFrogStudentRepository superFrogStudentRepository;

    @Mock
    IdWorker idWorker;

    @InjectMocks
    SuperFrogStudentService superFrogStudentService;


    List<SuperFrogStudent> superFrogStudents;


    @BeforeEach
    void setUp() {
        SuperFrogStudent s1 = new SuperFrogStudent();
        s1.setId(1001);
        s1.setFirstName("Jane");
        s1.setLastName("Smith");

        SuperFrogStudent s2 = new SuperFrogStudent();
        s2.setId(1004);
        s2.setFirstName("John");
        s2.setLastName("Doe");

        SuperFrogStudent s3 = new SuperFrogStudent();
        s3.setId(1012);
        s3.setFirstName("Tim");
        s3.setLastName("Johnson");

        this.superFrogStudents = new ArrayList<>();
        this.superFrogStudents.add(s1);
        this.superFrogStudents.add(s2);
        this.superFrogStudents.add(s3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByIdSuccess() {
        // Given. Arrange inputs and targets. Define the behavior of Mock object superFrogStudentRepository

        SuperFrogStudent s = new SuperFrogStudent();
        s.setId(1001);
        s.setFirstName("Jane");
        s.setLastName("Smith");
        given(superFrogStudentRepository.findById(1001)).willReturn(Optional.of(s)); // Defines the behavior of mock object

        // When. Act on the target behavior. When steps should cover the method to be tested.
        SuperFrogStudent returnedSuperfrog = superFrogStudentService.findById(1001);

        // Then. Assert expected outcomes.
        assertThat(returnedSuperfrog.getId()).isEqualTo(s.getId());
        assertThat(returnedSuperfrog.getFirstName()).isEqualTo(s.getFirstName());
        assertThat(returnedSuperfrog.getLastName()).isEqualTo(s.getLastName());
        verify(superFrogStudentRepository, times(1)).findById(1001);
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        given(superFrogStudentRepository.findById(Mockito.any(Integer.class))).willReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(()-> {
            SuperFrogStudent returnedSuperfrog = superFrogStudentService.findById(1001);
        });

        // Then
        assertThat(thrown)
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Could not find superfrog with Id 1001 :(");
        verify(superFrogStudentRepository, times(1)).findById(1001);

    }

    @Test
    void testFindAllSuccess() {
        // Given
        given(superFrogStudentRepository.findAll()).willReturn(this.superFrogStudents);

        // When
        List<SuperFrogStudent> actualSuperFrogStudents = superFrogStudentService.findAll();

        // Then
        assertThat(actualSuperFrogStudents.size()).isEqualTo(this.superFrogStudents.size());
        verify(superFrogStudentRepository, times(1)).findAll();

    }


    @Test
    void testSaveSuccess() {
        // Given
        SuperFrogStudent newSuperFrogStudent = new SuperFrogStudent();
        newSuperFrogStudent.setFirstName("New SuperFrog First");
        newSuperFrogStudent.setLastName("New SuperFrog Last");

        given(idWorker.nextId()).willReturn(1016L);
        given(superFrogStudentRepository.save(newSuperFrogStudent)).willReturn(newSuperFrogStudent);

        // When
        SuperFrogStudent savedSuperFrogStudent = superFrogStudentService.save(newSuperFrogStudent);

        // Then
        assertThat(savedSuperFrogStudent.getId()).isEqualTo(1016);
        assertThat(savedSuperFrogStudent.getFirstName()).isEqualTo(newSuperFrogStudent.getFirstName());
        assertThat(savedSuperFrogStudent.getLastName()).isEqualTo(newSuperFrogStudent.getLastName());
        verify(superFrogStudentRepository, times(1)).save(newSuperFrogStudent);

    }

    @Test
    void testUpdateSuccess(){
        //Given
        SuperFrogStudent oldFrog= new SuperFrogStudent();
        oldFrog.setId(1001);
        oldFrog.setFirstName("Jane");
        oldFrog.setLastName("Smith");

        SuperFrogStudend updatedFrog= new SuperFrogStudent();
        updatedFrog.setId(1001);
        updatedFrog.setFirstName("Jane");
        updatedFrog.setLastName("Blacksmith");

        given(SuperFrogStudentRepository.findById("1001")).willReturn(Optional.of(oldFrog));
        given(SuperFrogStudentRepository.save(oldFrog)).willReturn(oldFrog);
        //When
        SuperFrogStudent updateFrog = SuperFrogStudentService.update(superFrogId=1001, updatedFrog);
        //Then
        assertThat(updateFrog.getId()).isEqualTo(updatedFrog.getId());
        assertThat(updateFrog.getLastName()).isEqualTo(updatedFrog.getLastName());
        verify(superFrogStudentRepository, times(1)).findById(1001);
        verify(superFrogStudentRepository, times(1)).save(oldFrog);
    }

    @Test
    void testUpdateNotFound(){
        SuperFrogStudend updatedFrog= new SuperFrogStudent();
        updatedFrog.setId(1001);
        updatedFrog.setFirstName("Jane");
        updatedFrog.setLastName("Blacksmith");

        given(superFrogStudentRepository.findById(1001)).(Optional.empty());
    }

}