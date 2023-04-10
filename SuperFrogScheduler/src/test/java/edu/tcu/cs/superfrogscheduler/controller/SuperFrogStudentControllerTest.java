package edu.tcu.cs.superfrogscheduler.controller;

import edu.tcu.cs.superfrogscheduler.domain.SuperFrogStudent;
import edu.tcu.cs.superfrogscheduler.service.SuperFrogStudentService;
import edu.tcu.cs.superfrogscheduler.system.HttpStatusCode;
import edu.tcu.cs.superfrogscheduler.system.exception.ObjectNotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class SuperfrogControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SuperFrogStudentService superFrogStudentService;

    List<SuperFrogStudent> superFrogStudents;




    @BeforeEach
    void setUp() {
        this.superFrogStudents = new ArrayList<>();
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
        this.superFrogStudents.add(s1);
        this.superFrogStudents.add(s2);
        this.superFrogStudents.add(s3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindSuperFrogStudentByIdSuccess() throws Exception {
        // Given
        given(this.superFrogStudentService.findById(1001)).willReturn(this.superFrogStudents.get(0));

        // When and then
        this.mockMvc.perform(get("/api/superfrogstudents/1001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(HttpStatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find One Success"))
                .andExpect(jsonPath("$.data.id").value(1001))
                .andExpect(jsonPath("$.data.firstName").value("Jane"));

    }

    @Test
    void testFindSuperFrogStudentByIdNotFound() throws Exception {
        // Given
        given(this.superFrogStudentService.findById(1001)).willThrow(new ObjectNotFoundException("superfrog student", 1001));

        // When and then
        this.mockMvc.perform(get("/api/superfrogstudents/1001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(false))
                .andExpect(jsonPath("$.code").value(HttpStatusCode.NOT_FOUND))
                .andExpect(jsonPath("$.message").value("Could not find superfrog student with Id 1001 :("))
                .andExpect(jsonPath("$.data").isEmpty());

    }

    @Test
    void testFindAllSuperFrogStudents() throws Exception {
        // Given
        given(this.superFrogStudentService.findAll()).willReturn(this.superFrogStudents);

        // When and Then
        this.mockMvc.perform(get("/api/superfrogstudents").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag").value(true))
                .andExpect(jsonPath("$.code").value(HttpStatusCode.SUCCESS))
                .andExpect(jsonPath("$.message").value("Find All Success"))
                .andExpect(jsonPath("$.data", Matchers.hasSize(this.superFrogStudents.size())))
                .andExpect(jsonPath("$.data[0].id").value(1001))
                .andExpect(jsonPath("$.data[0].firstName").value("Jane"))
                .andExpect(jsonPath("$.data[1].id").value(1004))
                .andExpect(jsonPath("$.data[1].firstName").value("John"))
                .andExpect(jsonPath("$.data[2].id").value(1012))
                .andExpect(jsonPath("$.data[2].firstName").value("Tim"));
    }


}